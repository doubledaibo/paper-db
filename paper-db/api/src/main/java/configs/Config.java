package configs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Config {
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_ADDRESS = "jdbc:mysql://127.0.0.1:3306/paper_db";
	public static final String DB_USER = "root";
	public static final String DB_PASSWD = "123456";
	
	public static final String AUTH_KEY = "qweriujklsavfqewfvnmqwpoxl";
	public static final String PASSWD_KEY = "qelithqgrgqdhxucvhlkaqwenrf";
	
	public static final String COOKIE_AUTH_NAME = "auth_data";
	public static final String COOKIE_AUTH_PATH = "/api/";
	public static final int COOKIE_AUTH_EXPIRE_TIME = 2 * 60 * 60;
	
	public static final String USER_INFO_NAME = "user_info";
	public static final DateFormat DATE_FOMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss", Locale.CHINA);
}
