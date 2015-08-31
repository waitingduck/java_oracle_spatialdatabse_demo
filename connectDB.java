

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectDB {
	
	private static Connection con = null;
	private static ConnectDB DB = new ConnectDB();
	
	private ConnectDB(){
		System.out.println("-------- Oracle JDBC Connection Testing ------");

		//try to find jdbc driver. if not find, remember to include jdbc's jar file
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Where is your Oracle JDBC Driver? I cannot find it!");
			e.printStackTrace();
		}

		
		//try to connect to DB 
		try{
			System.out.println("Oracle JDBC Driver Registered!");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:cheng", //username
					"SCOTT", //dbname
					"123456"); // password
		} 
		catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		
		//output connection result
		if (con != null) {
			System.out.println("Oracle database is connected!");
		} 
		else {
			System.out.println("Failed to make connection!");
		}
	}
	
	public static Connection Connect(){
		return con;
	}

}
