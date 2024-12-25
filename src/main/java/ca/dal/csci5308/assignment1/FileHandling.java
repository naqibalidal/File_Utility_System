package ca.dal.csci5308.assignment1;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Scanner;

public class FileHandling {
    private static DatabaseService db;
    public FileHandling() {
        this.db = new DatabaseService();
    }
    public static boolean getFileInfo(String fileName) {
        File file = new File(fileName);
        try{
            if(file.exists()){
                System.out.println("File Name: " + file.getName());
                System.out.println("Full Path: "+ file.getAbsolutePath());
                System.out.println("is Readable? "+ file.canRead());
                String size = converBytes(file.length());
                System.out.println("File size: " + size);
                Date d = new Date(file.lastModified());
                System.out.println("Last Modified: " + d);
                return true;
            }
            else{
                System.out.println("File does not exist");
                return false;
            }
        }
        catch(Exception e){
            System.out.println("file not found");
            return false;
        }
    }
    public static boolean createFile(String fileName) {
        // works for current directory and absolute paths
        db.createFile(fileName);
        File file1 = new File(fileName);
        try{
            if(file1.createNewFile()){
                System.out.println("file created with in path: " + file1.getAbsolutePath() );
                return true;
            }
            else{
                System.out.println("file already exists");
                return false;
            }
        }
        catch(IOException e){
            System.out.println(e);
            return false;
        }
    }

    public static boolean deleteFile(String fileName) {

        File file = new File(fileName);
        if(file.delete()){
            db.deleteFile(fileName);
            System.out.println("file deleted");
            return true;
        }
        else{
            System.out.println("file does not exist");
            return false;
        }
    }

    public static boolean renameOrMoveFile(String oldFileName, String newFileName) {
        File file1 = new File(oldFileName);
        File file2 = new File(newFileName);
        if(file1.exists() && db.fileExists(oldFileName)){
            if(!(file2.exists())){
                try{
                    if(file1.renameTo(file2) && db.moveFile(oldFileName, newFileName)){
                        db.renameFile(oldFileName, newFileName);
                        System.out.printf("File renamed with in path: %1$s", file2.getAbsolutePath() );
                        return true;
                    }
                }
                catch(Exception e){
                    System.out.println("File could not be renamed");
                    e.printStackTrace();
                    return false;
                }
            }
            else{
                System.out.println("File with the same name already exists");
                return false;
            }
        }
        else{
            System.out.println("File does not exist");
            return false;
        }
        return false;
    }

    public static boolean readFile(String fileName) {
        try{
            File file1 = new File(fileName);
            Scanner filereader = new Scanner(file1);
            while(filereader.hasNextLine()){
                System.out.println(filereader.nextLine());
            }
            return true;
        }
        catch(Exception e){
            System.out.println("An error occurred while reading the file.");
            System.out.println(e.getMessage());
            return false;
        }
    }

    private static String converBytes(long size){

        DecimalFormat formatter = new DecimalFormat("0.00");

        float KB = 1024;
        float MB = KB * 1024;
        float GB = MB * 1024;
        float TB = GB * 1024;

        if(size < KB){
            return size + " B";
        }
        else if(size < MB){
            return formatter.format(size/KB) + " KB";
        }
        else if(size < GB){
            return formatter.format(size/MB) + " MB";
        }
        else if(size < TB){
            return formatter.format(size/GB) + " GB";
        }
        else{
            return formatter.format(size/TB) + " TB";
        }
    }

}
