package ca.dal.csci5308.assignment1;

import java.io.File;

public class FolderHandling {
    private static DatabaseService db;
    public FolderHandling(){
        db = new DatabaseService();
    }
    public static boolean createFolder(String folderName) {
        File folder1 = new File(folderName);
        try {
            if(folder1.mkdir()){
                db.createFolder(folderName);
                System.out.println("folder created with in path: " + folder1.getAbsolutePath() );
                return true;
            }
            else{
                System.out.println("folder already exists");
                return false;
            }
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    public static boolean deleteFolder(String folderName) {
        try{
            File folder1 = new File(folderName);
            if(folder1.listFiles().length == 0){
                folder1.delete();
                System.out.println("folder deleted with in path: " + folder1.getAbsolutePath() );
                return true;
            }
            else{
                for(File file : folder1.listFiles()){
                    if(file.isDirectory()){
                        deleteFolder(file.getAbsolutePath());
                    }
                    file.delete();
                    System.out.println("folder deleted with in path: " + folder1.getAbsolutePath() );
                }
                db.deleteFolder(folderName);
                folder1.delete();
                return true;
            }
        }
        catch(Exception e){
            System.out.println("folder does not exist");
            return false;
        }
    }

    public static boolean renameOrMoveFolder(String oldFolderName, String newFolderName) {
        File file1 = new File(oldFolderName);
        File file2 = new File(newFolderName);
        if(file1.exists()){
            if(!(file2.exists())){
                try{
                    db.moveFolder(oldFolderName, newFolderName);
                    if(file1.renameTo(file2)){
                        System.out.printf("Folder renamed with in path: %1$s", file2.getAbsolutePath() );
                        System.out.println();
                        return true;
                    }
                }
                catch(Exception e){
                    System.out.println(e);
                    return false;
                }
            }
            else{
                System.out.println("Folder with same name already exist");
                return false;
            }
        }
        else{
            System.out.println("Folder does not exist");
            //return false;
        }
        return false;
    }

    public static boolean listFolderContent(String folderName) {
        File folder1 = new File(folderName);
        try{
            if(folder1.listFiles().length == 0){
                System.out.println("folder is empty");
            }
            else{
                System.out.println("Contents in the path: "+folder1.getAbsolutePath());
                for(File file : folder1.listFiles()){
                    System.out.println(file.getName());
                }
            }
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    public static boolean isFolderExists(String folderPath) {
        db.folderExists(folderPath);
        File folder = new File(folderPath);
        return folder.exists() && folder.isDirectory();
    }

    public static void listFolderHierarchy(String folderName) {
        File folder1 = new File(folderName);
        try{
            for(File file : folder1.listFiles()){
                if(file.isDirectory()){
                    System.out.println("Folder: " + file.getAbsolutePath());
                    listFolderHierarchy(file.getAbsolutePath());
                }
                else{
                    System.out.println("File: " + file.getAbsolutePath());
                }
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
