package ca.dal.csci5308.assignment1;

import java.io.File;
import java.util.Date;
import java.util.Map;

//Class that handles connections with mock database
//Keep track of the activities performed by user
//Add entries to mock database
//Verify user input
public class Tracker {
     private static DatabaseService db;

     public Tracker(DatabaseService db) {
         //this.db = new DatabaseService();
         this.db = db;
     }

     public boolean validatePath(String path) {
         if (isNullPath(path)) {
             System.out.println("Folder/File name is null");
             return false;
         }
         else if(isNotValidPath(path)){
             System.out.println("Attempt detected to modify in program directory please select another directory");
             return false;
         }
         return true;
     }
     //Input checker
     private boolean isNotValidPath(String path){
         String applicationDirectory = new File("").getAbsolutePath();
         path = new File(path).getAbsolutePath();
         return path.startsWith(applicationDirectory);
     }
     private boolean isNullPath(String path){
         if(path == null){
            return true;
         }
         else{
             return false;
         }
     }

    //Add logs to database
    public void addLog(String path, Date timeStamp){
         db.addActivityLog(path, timeStamp);
         System.out.println("Log entry added to database");
    }

    //Retrieve logs from database
    public Map<String, Date> getLog(){
         return db.getActivityLogs();
     }

     //Initiate database connection
     public boolean connectDB() throws InterruptedException {
         System.out.println("Connecting to database......");
         Thread.sleep(5000);
         return db.connect();
     }

     //Close connection to database
     public boolean disconnectDB(){
         System.out.println("Disconnected from database.");
         return db.disconnect();
     }
}
