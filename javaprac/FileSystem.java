package javaprac;
/*

LLD Problem :
Problem Statement:
You are asked to design a file system that allows you to create new paths and associate them with different values.

The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters.
For example, "/documents" and "/documents/pictures" are valid paths while an empty string "" and "/" are not.

bool createPath(string path, int value) Creates a new path and associates a value to it if possible and returns true. Returns false if the path already exists or its parent path doesn't exist.

int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.

Example 1:
java.FileSystem fileSystem = new java.FileSystem();

fileSystem.createPath("/a", 1); // return true

fileSystem.get("/a"); // return 1

Example 2:

java.FileSystem fileSystem = new java.FileSystem();

fileSystem.createPath("/users", 100); // return true

fileSystem.createPath("/users/desktop", 2); // return true

fileSystem.get("/users/desktop"); // return 2

fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.

fileSystem.get("/c"); // return -1 because this path doesn't exist.
* */

import java.util.HashMap;

/**
 *
 */
public class FileSystem {
    Folder root;

    public FileSystem(){
        root = new Folder();
    }

   public boolean createPath(String path, int value) {
      if(pathExists(path)) {
        return false;
      }
      int lastIndex = path.lastIndexOf("/");
      boolean result = pathExists(path.substring(0,lastIndex));
      if (result) {
          addPath(path,value);
      }
      return result;
    }

    private void addPath(String path, int value) {
        root.addSubfolder(path.split("/"), 0, value);
    }

    public boolean pathExists(String path) {
        if (path.isEmpty()){
            return true;
        }
        Folder folder = root;
        for (String folderName:path.split("/")) {
            if (folder.subfolders.containsKey(folderName)) {
                folder = folder.subfolders.get(folderName);
            } else {
                return false;
            }
        }
        return true;
    }
}

class Folder {
    public String name;
    public int value; // applies to to the folder that marks end of a path
    public HashMap<String, Folder> subfolders = new HashMap<>();

    public Folder() {
    }

    public Folder(String name) {
        this.name = name;
    }

    public void addSubfolder(String[] path, int index, int value) {
        String subfolderToBeCreated = path[index];
        if (!subfolders.containsKey(subfolderToBeCreated)) {
            subfolders.put(subfolderToBeCreated, new Folder(subfolderToBeCreated));
        }
        if (index < path.length - 1) {
            Folder subfolder = subfolders.get(subfolderToBeCreated);
            subfolder.addSubfolder(path, index + 1, value);
        }
        if (index == path.length - 1) {
            subfolders.get(subfolderToBeCreated).value = value;
        }
    }


}