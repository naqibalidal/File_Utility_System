package ca.dal.csci5308.assignment1;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 3 classes 9 methods at least
public class Assigment1 {
    private static DatabaseService db = new DatabaseService();
    private static Tracker tr = new Tracker(db);
    private static Date date = new Date();
    public static void main(String[] args) throws InterruptedException, IOException {
        boolean exit = false;
        Map<String, Date> logs;
        Scanner sc = new Scanner(System.in);
        tr.connectDB();
        while(!exit){
            System.out.println("Choose an option:");
            System.out.println("1. File handling");
            System.out.println("2. Folder handling");
            System.out.println("3. Check activity logs");
            System.out.println("4. Exit");
            String option = sc.next();
            System.out.println(" ");

            if(option.equals("1")){
                System.out.println(":::::::::::::::File handling:::::::::::::::");
                fileHandlingMenu();
            }
            else if(option.equals("2")){
                System.out.println(":::::::::::::::Folder handling:::::::::::::::");
                folderHandlingMenu();
            }
            else if(option.equals("3")){
                System.out.println(":::::::::::::::Check activity logs:::::::::::::::");
                if(tr.getLog().isEmpty()){
                    System.out.println("No logs found");
                    System.out.println("Return again after doing some activity eg: file creation, folder deletion etc.");
                }
                else{
                    logs = tr.getLog();
                    for (Map.Entry<String, Date> entry : logs.entrySet())
                        System.out.println(entry.getKey() + " " + entry.getValue());
                }
            }
            else if(option.equals("4")){
                exit = true;
            }
            else{
                System.out.println("Invalid option!");
            }
            System.out.println(" ");
        }
        tr.disconnectDB();
    }

    public static void fileHandlingMenu() {
        boolean exit = false;
        while (!exit) {
        System.out.println(" ");
        System.out.println("Choose an option:");
        System.out.println("1. File Create");
        System.out.println("2. File Delete");
        System.out.println("3. File Move");
        System.out.println("4. File Rename");
        System.out.println("5. Get File info");
        System.out.println("6. Read a file");
        System.out.println("7. Go back");
        Scanner sc = new Scanner(System.in);
        String option = sc.nextLine();
        if (option.equals("1")) {
            UIFileCreate();
        } else if (option.equals("2")) {
            UIFileDelete();
        } else if (option.equals("3")) {
            UIFileMove();
        } else if (option.equals("4")) {
            UIFileRename();
        } else if (option.equals("5")) {
            UIFileGetInfo();
        } else if (option.equals("6")) {
            UIFileRead();
        } else if (option.equals("7")) {
            exit = true;
        } else {
            System.out.println("Invalid option!");
        }
            System.out.println(" ");
        }
    }

    public static void folderHandlingMenu(){
        boolean exit = false;
        while (!exit) {
            System.out.println(" ");
            System.out.println("Choose an option:");
            System.out.println("1. Folder Create");
            System.out.println("2. Folder Delete");
            System.out.println("3. Folder Move");
            System.out.println("4. Folder Rename");
            System.out.println("5. List folder contents");
            System.out.println("6. Go back");
            System.out.println(" ");
            Scanner sc = new Scanner(System.in);
            String option = sc.nextLine();
            if (option.equals("1")) {
                UIFolderCreate();
            }
            else if (option.equals("2")) {
                UIFolderDelete();
            }
            else if (option.equals("3")) {
                UIFolderMove();
            }
            else if (option.equals("4")) {
                UIFolderRename();
            }
            else if (option.equals("5")) {
                UIFolderListContent();
            }
            else if (option.equals("6")) {
                exit = true;
            }
            else{
                System.out.println("Invalid option!");
            }
        }
    }
    // clear UI
    public static void ClearUI(){
        try {
            final String os = System.getProperty("os.name");
            System.out.println(os);
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            System.out.println("something went wrong :(");
        }
    }
    // File UI's
    public static void UIFileCreate(){
        System.out.println("Input file name to be created: ");
        Scanner sc = new Scanner(System.in);
        String path1 = sc.nextLine();

        FileHandling fh = new FileHandling();

        if((tr.validatePath(path1))){
            if(fh.createFile(path1) ){
                File f = new File(path1);
                tr.addLog("File created in path: "+f.getAbsolutePath(), new Date(date.getTime()));
            }
        }
    }

    public static void UIFileDelete(){
        System.out.println("Input file name to be deleted: ");
        Scanner sc = new Scanner(System.in);
        String path1 = sc.nextLine();

        FileHandling fh = new FileHandling();

        if((tr.validatePath(path1))){
            if(fh.deleteFile(path1)){
                File f = new File(path1);
                tr.addLog("File Deleted from path: "+f.getAbsolutePath(), new Date(date.getTime()));
            }
        }
    }

    public static void UIFileRename(){
        System.out.println("Input original file name with extension");
        Scanner sc = new Scanner(System.in);
        String path1 = sc.nextLine();

        System.out.println("Input new file name with extension");
        String path2 = sc.nextLine();

        FileHandling fh = new FileHandling();
        if((tr.validatePath(path1)) && tr.validatePath(path2)){
            if(fh.renameOrMoveFile(path1, path2)){
                File f = new File(path1);
                tr.addLog("File renamed in path: "+f.getAbsolutePath(), new Date(date.getTime()));
            }
        }
    }

    public static void UIFileMove(){
        System.out.println("Input the source location of file to be moved: ");
        Scanner sc = new Scanner(System.in);
        String path1 = sc.nextLine();

        System.out.println("Input the destination: ");
        String path2 = sc.nextLine();

        FileHandling fh = new FileHandling();
        if((tr.validatePath(path1))&&tr.validatePath(path2)){
            if(fh.renameOrMoveFile(path1, path2) ){
                File f = new File(path1);
                tr.addLog("File moved from path: "+f.getAbsolutePath(), new Date(date.getTime()));
            }
        }

    }

    public static void UIFileRead(){
        System.out.println("Input file name to be read: ");
        Scanner sc = new Scanner(System.in);
        String path1 = sc.nextLine();

        FileHandling fh = new FileHandling();
        if(fh.readFile(path1)){
            File f = new File(path1);
            tr.addLog("Read file in path: "+f.getAbsolutePath(), new Date(date.getTime()));
        }
    }

    public static void UIFileGetInfo(){
        System.out.println("Input file name to get information of: ");
        Scanner sc = new Scanner(System.in);
        String path1 = sc.nextLine();

        FileHandling fh = new FileHandling();
        if(fh.getFileInfo(path1)){
            File f = new File(path1);
            tr.addLog("File info displayed from path: "+f.getAbsolutePath(), new Date(date.getTime()));
        }
    }

    //Folder UI's
    public static void UIFolderCreate(){
        System.out.println("Input folder name to be created: ");
        Scanner sc = new Scanner(System.in);
        String path1 = sc.nextLine();

        FolderHandling fo = new FolderHandling();
        if((tr.validatePath(path1))){
            if(fo.createFolder(path1) ){
                File fo1 = new File(path1);
                tr.addLog("Folder created in path: "+fo1.getAbsolutePath(), new Date(date.getTime()));
            }
        }
    }

    public static void UIFolderDelete(){
        System.out.println("Input folder name to be deleted: ");
        Scanner sc = new Scanner(System.in);
        String path1 = sc.nextLine();

        FolderHandling fo = new FolderHandling();

        if((tr.validatePath(path1))){
            if(fo.deleteFolder(path1)){
                File fo1 = new File(path1);
                tr.addLog("Folder created in path: "+fo1.getAbsolutePath(), new Date(date.getTime()));
            }
        }
    }

    public static void UIFolderListContent(){
        System.out.println("Input folder name to list its content: ");
        Scanner sc = new Scanner(System.in);
        String path1 = sc.nextLine();
        FolderHandling fh = new FolderHandling();

        if(fh.listFolderContent(path1)) {
            File fo1 = new File(path1);
            tr.addLog("Folder contents listed in path: " + fo1.getAbsolutePath(), new Date(date.getTime()));
        }
    }

    public static void UIFolderRename(){
        System.out.println("Input folder name to be renamed: ");
        Scanner sc = new Scanner(System.in);
        String path1 = sc.nextLine();

        System.out.println("Input new name for existing folder");
        String path2 = sc.nextLine();
        FolderHandling fh = new FolderHandling();

        if(tr.validatePath(path1) && tr.validatePath(path2)) {
            if (fh.renameOrMoveFolder(path1, path2)) {
                File fo1 = new File(path1);
                tr.addLog("Folder renamed in path: " + fo1.getAbsolutePath(), new Date(date.getTime()));
            }
        }
    }

    public static void UIFolderMove(){
        System.out.println("Input folder name to be moved: ");
        Scanner sc = new Scanner(System.in);
        String path1 = sc.nextLine();

        System.out.println("Input full path of new location for the folder: ");
        String path2 = sc.nextLine();
        FolderHandling fh = new FolderHandling();
        if((tr.validatePath(path1)) && tr.validatePath(path2)){
            if(fh.renameOrMoveFolder(path1, path2)) {
                File fo1 = new File(path1);
                tr.addLog("Folder moved from path: " + fo1.getAbsolutePath(), new Date(date.getTime()));
            }
        }
    }
}


