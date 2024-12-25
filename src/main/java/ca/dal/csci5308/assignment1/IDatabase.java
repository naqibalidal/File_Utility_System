package ca.dal.csci5308.assignment1;

import java.util.Date;
import java.util.Map;

public interface IDatabase {
    boolean folderExists(String folderPath);
    boolean createFolder(String folderPath);
    boolean moveFolder(String folderPath, String newFolderPath);
    boolean fileExists(String filePath);
    boolean deleteFile (String filePath);
    boolean createFile(String filePath);
    boolean renameFile(String oldFilePath, String newFilePath);
    boolean moveFile(String sourceFilePath, String destinationFilePath);
    boolean deleteFolder(String folderPath);
    void addActivityLog(String path, Date timeStamp);
    Map<String, Date> getActivityLogs();
    boolean connect();
    boolean disconnect();
}
