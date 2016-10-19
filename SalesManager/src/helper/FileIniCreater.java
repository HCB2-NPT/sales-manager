package helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import config.AppConfig;

public class FileIniCreater {
	private static final String FILE_NAME = AppConfig.FILE_INI;
	private static final String FILE_FORMAT = AppConfig.FILE_INI_FORMAT;
	
	public static boolean load(){
		boolean success = true;
		File f = new File(FILE_NAME);
		if (!f.exists()){
			success = write();
		}
		return success;
	}
	
	public static boolean write(){
		boolean success = false;
		File f = new File(FILE_NAME);
		try {
			if (!f.exists())
				f.createNewFile();
			FileWriter fw = new FileWriter(f.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(FILE_FORMAT);
			bw.close();
			success = true;
		} catch (Exception e) {
			success = false;
		}
		return success;
	}
	
	public static boolean setValue(String key, String value){
		boolean success = false;
		try {
			Path path = Paths.get(FILE_NAME);
			String content = new String(Files.readAllBytes(path));
			int from = content.indexOf(key);
			int to = content.indexOf("\r\n", from);
			content = content.replace(content.substring(from, to), String.format("%1$s=%2$s", key, value));
			Files.write(path, content.getBytes());
			success = true;
		} catch (IOException e) {
			success = false;
		}
		return success;
	}
	
	public static String getValue(String key){
		String value = null;
		try {
			Path path = Paths.get(FILE_NAME);
			String content = new String(Files.readAllBytes(path));
			int from = content.indexOf(key);
			int to = content.indexOf("\r\n", from);
			value = content.substring(from + key.length() + 1, to);
		} catch (IOException e) {
			value = null;
		}
		return value;
	}
}
