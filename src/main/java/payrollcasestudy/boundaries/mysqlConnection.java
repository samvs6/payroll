package payrollcasestudy.boundaries;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class mysqlConnection implements Repository{
	
public static mysqlConnection relationalDatabase = new mysqlConnection();
	
	private Connection connection;
	private String localhost = "jdbc:mysql://localhost:33060";
	private String userDB = "root";
	private String password = "root";
	
	public String getStatusConnection() {
		try{
			connection = (Connection) DriverManager.getConnection(localhost, userDB,password);
			return "Connection success";
		}catch (Exception e){
			return "Connection failed";
		}
		
	}
}

