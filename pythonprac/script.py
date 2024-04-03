from filesystem import FileSystem

def main():
    # Create an instance of FileSystem
    file_system = FileSystem()

    # Create paths
    file_system.createPath("/folder1/folder2/folder3", 10)
    file_system.createPath("/folder1/folder4", 20)
    file_system.createPath("/folder5", 30)

    # Get values at specific paths
    print(file_system.get("/folder1/folder2/folder3"))  # Output: 10
    print(file_system.get("/folder1/folder4"))         # Output: 20
    print(file_system.get("/folder5"))                 # Output: 30

    # Attempt to create an existing path
    print(file_system.createPath("/folder1/folder2/folder3", 40))  # Output: False

if __name__ == "__main__":
    main()
