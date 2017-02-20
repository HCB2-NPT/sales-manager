package helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileIniCreater {
    private String FullText;
    private String FilePath;
    private FileIniCreater(String filepath, String fulltext)
    {
        FullText = fulltext;
        FilePath = filepath;
    }
    public String FullText(){
    	return FullText;
    }
    public String FilePath(){
    	return FilePath;
    }

    private final String END_DATA = "#\r\n";

    public static FileIniCreater Load(String filePath, String formatData)
    {
    	File f = new File(filePath);
        if (f.exists())
        {
        	try{
	        	char[] text = new char[(int) f.length()];
	        	FileReader fr = new FileReader(f.getAbsoluteFile());
				BufferedReader br = new BufferedReader(fr);
				br.read(text);
				br.close();
	            return new FileIniCreater(filePath, new String(text));
	        } catch (Exception e) {
				return null;
			}
        }
        return Write(filePath, formatData);
    }

    public static FileIniCreater Write(String filePath, String formatData)
    {
    	File f = new File(filePath);
        try
        {
            if (!f.exists())
                f.createNewFile();
            FileWriter fw = new FileWriter(f.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(formatData);
			bw.close();
            return new FileIniCreater(filePath, formatData);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public boolean Save()
    {
        try
        {
        	File f = new File(FilePath);
        	FileWriter fw = new FileWriter(f.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(FullText);
			bw.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean Set(String key, String value)
    {
        try
        {
            int from = FullText.indexOf(key), to = FullText.indexOf(END_DATA, from);
            FullText = FullText.replace(FullText.substring(from, to - from), String.format("%1$s=%2$s", key, value));
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public String Get(String key)
    {
        String value = null;
        try
        {
            int from = FullText.indexOf(key) + key.length() + 1, to = FullText.indexOf(END_DATA, from);
            value = FullText.substring(from, to - from);
        }
        catch (Exception e)
        {
            value = null;
        }
        return value;
    }
}
