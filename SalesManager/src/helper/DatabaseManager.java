package helper;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;

public class DatabaseManager {  
	public static void Backupdbtosql() {
	    try {

	        /*NOTE: Getting path to the Jar file being executed*/
	        /*NOTE: YourImplementingClass-> replace with the class executing the code*/
	        CodeSource codeSource = application.Main.class.getProtectionDomain().getCodeSource();
	        File jarFile = new File(codeSource.getLocation().toURI().getPath());
	        String jarDir = jarFile.getParentFile().getPath();


	        /*NOTE: Creating Database Constraints*/
	        String dbName = "qlbh";
	        String dbUser = "root";
	        String dbPass = "";

	        /*NOTE: Creating Path Constraints for folder saving*/
	        /*NOTE: Here the backup folder is created for saving inside it*/
	        String folderPath = jarDir + "\\backup";

	        /*NOTE: Creating Folder if it does not exist*/
	        File f1 = new File(folderPath);
	        f1.mkdir();

	        /*NOTE: Creating Path Constraints for backup saving*/
	        /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
	         String savePath = "\"" + jarDir + "\\backup\\" + "backup.sql\"";

	        /*NOTE: Used to create a cmd command*/
	        String executeCmd = "C:\\wamp64\\bin\\mysql\\mysql5.7.14\\bin\\mysqldump -u " + dbUser + " -p" + dbPass + " --database " + dbName + " -r " + savePath;

	        /*NOTE: Executing the command here*/
	        Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
	        int processComplete = runtimeProcess.waitFor();

	        /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
	        if (processComplete == 0) {
	            System.out.println("Backup Complete");
	        } else {
	            System.out.println("Backup Failure");
	        }

	    } catch (URISyntaxException | IOException | InterruptedException ex) {
	        //JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
	    	MessageBox.Show("Error at Backuprestore" + ex.getMessage(), null);
	    }
	}
	
	public static void Restoredbfromsql(String s) {
        try {
            /*NOTE: String s is the mysql file name including the .sql in its name*/
            /*NOTE: Getting path to the Jar file being executed*/
            /*NOTE: YourImplementingClass-> replace with the class executing the code*/
            CodeSource codeSource = application.Main.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();

            /*NOTE: Creating Database Constraints*/
             String dbName = "jdbc:mysql://localhost:3306/qlbh";
             String dbUser = "root";
             String dbPass = "myUltraSecretDbPasword";

            /*NOTE: Creating Path Constraints for restoring*/
            String restorePath = jarDir + "\\backup" + "\\" + s;

            /*NOTE: Used to create a cmd command*/
            /*NOTE: Do not create a single large string, this will cause buffer locking, use string array*/
            String[] executeCmd = new String[]{"mysql", dbName, "-u" + dbUser, "-p" + dbPass, "-e", " source " + restorePath};

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                //JOptionPane.showMessageDialog(null, "Successfully restored from SQL : " + s);
            	MessageBox.Show("Successfully restored from SQL : " + s, null);
            } else {
                //JOptionPane.showMessageDialog(null, "Error at restoring");
            	MessageBox.Show("Error at restoring", null);
            }


        } catch (URISyntaxException | IOException | InterruptedException | HeadlessException ex) {
            //JOptionPane.showMessageDialog(null, "Error at Restoredbfromsql" + ex.getMessage());
        	MessageBox.Show("Error at Restoredbfromsql" + ex.getMessage(), null);
        }

    }
}