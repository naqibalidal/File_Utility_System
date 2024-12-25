package ca.dal.csci5308.assignment1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class FolderHandlingTest {

    private FolderHandling folderHandling = new FolderHandling();

    @Test
    void createFolderTest() {
        String folder = "folder1";
        assertTrue(folderHandling.createFolder(folder));
    }

    @Test
    void deleteFolderTest() {
        String folder = "nonExistingFolder";
        assertFalse(folderHandling.deleteFolder(folder));
    }

    @Test
    void renameOrMoveFolderTest(){
        String oldFileName = "nonExisitingFolder";
        String newFileName = "newFolder";
        assertFalse(folderHandling.renameOrMoveFolder(oldFileName, newFileName));
    }

    @Test
    void listFolderContentTest() {
        String folder = "nonExistingFolder";
        assertFalse(folderHandling.listFolderContent(folder));
    }

    @Test
    void isFolderExistsTest() {
        String folder = "nonExistingFolder";
        assertFalse(folderHandling.isFolderExists(folder));
    }
}