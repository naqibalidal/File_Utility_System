package ca.dal.csci5308.assignment1;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


class FileHandlingTest {

    private FileHandling fileHandling = new FileHandling();

    @Test
    void getFileInfoTest(){
        String filename = "dummyfile.txt";
        assertTrue(fileHandling.getFileInfo(filename));
    }

    @Test
    void createFileTest() throws IOException {
        String fileName = "newFile.txt";
        assertTrue(FileHandling.createFile(fileName));
    }

    @Test
    void deleteFileTest(){
        String fileName = "nonExistingFileToBeDeleted.txt";
        assertFalse(FileHandling.deleteFile(fileName));
    }

    @Test
    void readFileTest(){
        String fileName = "nonExistingFile.txt";
        assertFalse(FileHandling.readFile(fileName));
    }

    @Test
    void renameOrMoveFileTest(){
        String oldFileName = "nonExisitingFile1.txt";
        String newFileName = "newFile.txt";
        assertFalse(FileHandling.renameOrMoveFile(oldFileName, newFileName));
    }
}