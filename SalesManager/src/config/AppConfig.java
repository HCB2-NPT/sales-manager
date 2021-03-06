package config;

public class AppConfig {
	public static final String				APP_NAME							=	"Sales Manager";
	public static final String				APP_ICON							=	"appicon.png";
	public static final int					LIMIT_NUMBER_TABS					=	8;
	
	public static final String				SECURITY_SECRET_KEY					=	"SM-SK-v0.0.3|15-10-2016|5:18PM";
	
	public static final boolean				THROGH_LOGIN_ENABLE					=	false;
	public static final String				THROGH_LOGIN_USERNAME				=	"admin";
	public static final String				THROGH_LOGIN_PASSWORD				=	"admin";
	
	public static final int					MAX_TIMES_LOGIN_FAIL				=	3;
	
	public static final String				MYSQLDUMP							=	"C:\\wamp64\\bin\\mysql\\mysql5.7.14\\bin\\mysqldump";
	public static final String				MYSQL								=	"C:\\wamp64\\bin\\mysql\\mysql5.7.14\\bin\\mysql";
	public static final String				DATABASE_NAME						=	"qlbh";
	public static final String				DATABASE_USERNAME					=	"root";
	public static final String				DATABASE_PASSWORD					=	"";
	
	public static final String				LOGIN_INI							=	"login.ini";
	public static final String				LOGIN_INI_FORMAT					=	"Remember=#\r\n" + 
																					"Username=#\r\n" + 
																					"Password=#\r\n";
	
	public static final String				DETAILS_INI							=	"details.ini";
	public static final String				DETAILS_INI_FORMAT					=	"Name=#\r\n" + 
																					"Addr=#\r\n" + 
																					"Desc=#\r\n";
}
