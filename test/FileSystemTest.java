package test;

import javaprac.FileSystem;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;



public class FileSystemTest {


    @ParameterizedTest(name = "Creating path: {0}, Value: {1}")
    @MethodSource("createPathProvider")
    void testCreatePath(String path, int value, boolean expectedResult) {
        FileSystem fileSystem = new FileSystem();
        boolean result = fileSystem.createPath(path, value);
        assertEquals(expectedResult, result);
    }

    static Stream<Object[]> createPathProvider() {
        return Stream.of(
                // Test cases: Path, Value, Expected Result
                new Object[]{"/folder1/folder2/folder3", 10, true}, // Path doesn't exist
                new Object[]{"/folder1", 5, true}, // Path doesn't exist
                new Object[]{"/folder1", 10, false}, // Path already exists
                new Object[]{"/folder1/folder2", 8, true}, // Path doesn't exist
                new Object[]{"/folder1/folder2", 6, false} // Path already exists
        );
    }
}
