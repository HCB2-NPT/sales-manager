package helper;

import java.io.File;
import java.security.CodeSource;

public class JavaAppProjectURL {
	public static final String FOLDER_TARGET		=	getTarget();
	
	public static String getTarget(){
		try{
			CodeSource codeSource = application.Main.class.getProtectionDomain().getCodeSource();
	        File jarFile = new File(codeSource.getLocation().toURI().getPath());
	        return jarFile.getParentFile().getPath();
		}catch(Exception e){}
		return "";
	}
}
