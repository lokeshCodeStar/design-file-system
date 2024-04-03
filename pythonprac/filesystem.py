class FileSystem:
    def __init__(self):

        self.root = None

        self.root = Folder(name=None)

    def createPath(self, path, value):
        if self._pathExists(path):
            return False
        lastIndex = path.rfind("/")
        result = self._pathExists(path[0:lastIndex])
        if result:
            self._addPath(path, value)
        return result

    def get(self, path):
        if not self._pathExists(path):
            return -1
        folder = self.root
        for foldername in path.split("/"):
            folder = folder.subfolders.get(foldername)
            if folder is None:
                return -1
        return folder.value

    def _addPath(self, path, value):
        self.root.addSubfolder(path.split("/"), 0, value)

    def _pathExists(self, path):
        if not path:
            return True
        folder = self.root
        for folderName in path.split("/"):
            if folderName in folder.subfolders:
                folder = folder.subfolders[folderName]
            else:
                return False
        return True


class Folder:

    def _initialize_instance_fields(self):

        self.name = None
        self.value = 0
        self.public = None
        self.subfolders = {}

    def __init__(self, name):
        self._initialize_instance_fields()

        self.name = name

    def addSubfolder(self, path, index, value):
        subfolder_to_be_created = path[index]
        if subfolder_to_be_created not in self.subfolders:
            self.subfolders[subfolder_to_be_created] = Folder(subfolder_to_be_created)
        if index < len(path) - 1:
            subfolder = self.subfolders[subfolder_to_be_created]
            subfolder.addSubfolder(path, index + 1, value)
        else:
            self.subfolders[subfolder_to_be_created].value = value