package config;

public class AppConfig {
	public static final String				APP_NAME							=	"Sales Manager";
	public static final String				APP_ICON							=	"appicon.png";
	
	public static final String				SECURITY_SECRET_KEY					=	"SM-SK-v0.0.3|15-10-2016|5:18PM";
	
	public static final boolean				THROGH_LOGIN_ENABLE					=	true;
	public static final String				THROGH_LOGIN_USERNAME				=	"admin";
	public static final String				THROGH_LOGIN_PASSWORD				=	"admin";
	
	public static final int					MAX_TIMES_LOGIN_FAIL				=	3;
	
	public static final String				MYSQLDUMP							=	"C:\\wamp64\\bin\\mysql\\mysql5.7.14\\bin\\mysqldump";
	public static final String				MYSQL								=	"C:\\wamp64\\bin\\mysql\\mysql5.7.14\\bin\\mysql";
	public static final String				DATABASE_NAME						=	"qlbh";
	public static final String				DATABASE_USERNAME					=	"root";
	public static final String				DATABASE_PASSWORD					=	"";
	
	public static final String				FILE_INI							=	"application.ini";
	public static final String				FILE_INI_FORMAT						=	"Username=\r\nPassword=\r\n";
}
