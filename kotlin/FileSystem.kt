package kotlin

import java.util.HashMap

class FileSystem {
    private var root: Folder = Folder()

    fun createPath(path: String, value: Int): Boolean {
        if (pathExists(path)) {
            return false
        }
        val lastIndex = path.lastIndexOf("/")
        val result = pathExists(path.substring(0, lastIndex))
        if (result) {
            addPath(path, value)
        }
        return result
    }

    private fun addPath(path: String, value: Int) {
        root.addSubfolder(path.split("/").toTypedArray(), 0, value)
    }

    fun pathExists(path: String): Boolean {
        if (path.isEmpty()) {
            return true
        }
        var folder: Folder = root
        for (folderName in path.split("/")) {
            if (folder.subfolders.containsKey(folderName)) {
                folder = folder.subfolders[folderName]!!
            } else {
                return false
            }
        }
        return true
    }
}

class Folder {
    var name: String? = null
    var value = 0
    var subfolders: HashMap<String, Folder> = HashMap()

    constructor() {}
    constructor(name: String) {
        this.name = name
    }

    fun addSubfolder(path: Array<String>, index: Int, value: Int) {
        val subfolderToBeCreated = path[index]
        if (!subfolders.containsKey(subfolderToBeCreated)) {
            subfolders[subfolderToBeCreated] = Folder(subfolderToBeCreated)
        }
        if (index < path.size - 1) {
            val subfolder = subfolders[subfolderToBeCreated]!!
            subfolder.addSubfolder(path, index + 1, value)
        }
        if (index == path.size - 1) {
            subfolders[subfolderToBeCreated]!!.value = value
        }
    }
}
