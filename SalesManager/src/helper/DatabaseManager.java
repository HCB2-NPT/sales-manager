package helper;

import java.io.File;

import config.AppConfig;

public class DatabaseManager {
	private static final String mysqldump = AppConfig.MYSQLDUMP;
	private static final String mysql = AppConfig.MYSQL;
	private static final String dbName = AppConfig.DATABASE_NAME;
	private static final String userName = AppConfig.DATABASE_USERNAME;
	private static final String passWord = AppConfig.DATABASE_PASSWORD;
	
	public static Process Backupdbtosql() {
		Process p = null;
	    try{
	        String folderPath = JavaAppProjectURL.FOLDER_TARGET + "\\db_backup\\";
	    	String file = folderPath + String.valueOf(System.currentTimeMillis()) + ".sql";
	    	
	    	File f = new File(folderPath);
	    	f.mkdir();
	    	
	    	ProcessBuilder b;
	    	if (passWord == null || passWord.equals(""))
	    		b = new ProcessBuilder("cmd.exe", "/c", mysqldump, "-u"+userName, dbName, ">", file);
	    	else
	    		b = new ProcessBuilder("cmd.exe", "/c", mysqldump, "-u"+userName, "-p"+passWord, dbName, ">", file);
	    	p = b.start();
	    }catch (Exception e){
	    	System.out.println("Backup db fail!");
	    }
	    return p;
	}
	
	public static Process Restoredbfromsql(String file) {
		Process p = null;
	    try{
	    	ProcessBuilder b;
	    	if (passWord == null || passWord.equals(""))
	    		b = new ProcessBuilder("cmd.exe", "/c", mysql, "-u"+userName, dbName, "<", file);
	    	else
	    		b = new ProcessBuilder("cmd.exe", "/c", mysql, "-u"+userName, "-p"+passWord, dbName,  "<", file);
	    	p = b.start();
	    }catch (Exception e){
	    	System.out.println("Backup db fail!\n" + e.getMessage());
	    }
	    return p;
    }
}