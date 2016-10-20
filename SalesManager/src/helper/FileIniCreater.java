package helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileIniCreater {
	private static final String END_DATA = "#\r\n";
	
	public static boolean load(String filePath, String formatData){
		if (!new File(filePath).exists()){
			return write(filePath, formatData);
		}
		return true;
	}
	
	public static boolean write(String filePath, String formatData){
		boolean success = false;
		File f = new File(filePath);
		try {
			if (!f.exists())
				f.createNewFile();
			FileWriter fw = new FileWriter(f.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(formatData);
			bw.close();
			success = true;
		} catch (Exception e) {
			success = false;
		}
		return success;
	}
	
	public static boolean setValue(String filePath, String key, String value){
		boolean success = false;
		try {
			Path path = Paths.get(filePath);
			String content = new String(Files.readAllBytes(path));
			int from = content.indexOf(key);
			int to = content.indexOf(END_DATA, from);
			content = content.replace(content.substring(from, to), String.format("%1$s=%2$s", key, value));
			Files.write(path, content.getBytes());
			success = true;
		} catch (IOException e) {
			success = false;
		}
		return success;
	}
	
	public static String getValue(String filePath, String key){
		String value = null;
		try {
			Path path = Paths.get(filePath);
			String content = new String(Files.readAllBytes(path));
			int from = content.indexOf(key);
			int to = content.indexOf(END_DATA, from);
			value = content.substring(from + key.length() + 1, to);
		} catch (IOException e) {
			value = null;
		}
		return value;
	}
}
