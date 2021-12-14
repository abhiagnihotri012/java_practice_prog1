package Controller;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCProperties {

	public static String driver_class = null;
	public static String url = null;
	public static String uname = null;
	public static String upass = null;
	static Connection con;
	public static void loadProperties() {
		try {
			FileInputStream f = new FileInputStream("resources/database.properties");
			Properties prop = new Properties();
			prop.load(f);
			driver_class = prop.getProperty("DB_DRIVER") ;
			url = prop.getProperty("DB_URL") ;
			uname = prop.getProperty("DB_USER") ;
			upass = prop.getProperty("DB_PASSWORD") ;
			prop.clear();
			f.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static Connection getConnection() {
		try {
			loadProperties();
			Class.forName(driver_class);
			con = DriverManager.getConnection(url, uname, upass);
			System.out.println("Connection Established!!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;	
	}
	public static void main(String[] args) {
		loadProperties();
		System.out.println(uname + upass + driver_class);
	}

}
