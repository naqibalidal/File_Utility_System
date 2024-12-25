package ca.dal.csci5308.assignment1;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DatabaseService implements IDatabase{

    private final Map<String, Date> activityLogs;
    private boolean isConnected;

    public DatabaseService() {
        activityLogs = new HashMap<String, Date >();
        isConnected = false;
    }

    @Override
    public boolean folderExists(String folderPath) {
        return true;
    }

    @Override
    public boolean createFolder(String folderPath) {
        return true;
    }

    @Override
    public boolean moveFolder(String folderPath, String newFolderPath) {
        return true;
    }

    @Override
    public boolean fileExists(String filePath) {
        return true;
    }

    @Override
    public boolean createFile(String filePath) {
        return true;
    }

    @Override
    public boolean renameFile(String oldFilePath, String newFilePath) {
        return true;
    }

    @Override
    public boolean moveFile(String sourceFilePath, String destinationFilePath) {
        return true;
    }

    @Override
    public boolean deleteFile(String path){
        return true;
    }

    @Override
    public boolean deleteFolder(String folderPath) {
        return true;
    }

    @Override
    public void addActivityLog(String path, Date timeStamp) {
        activityLogs.put(path, timeStamp);
    }

    @Override
    public Map<String, Date> getActivityLogs() {
        return activityLogs;
    }

    @Override
    public boolean connect() {
        isConnected = true;
        return true;
    }

    @Override
    public boolean disconnect() {
        isConnected = false;
        return true;
    }
}
