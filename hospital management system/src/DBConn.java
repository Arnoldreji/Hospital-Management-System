import java.sql.*;

public class DBConn {
	
	Connection conn;

	void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Class loaded...");
		    } 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	void open() {
		
		try {
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
			System.out.println("Connection established...");
			} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}


	void close() {
		if(conn!=null) {
		try {
			conn.close();
		    } 
		catch (SQLException e) {
			e.printStackTrace();
		}
	  }
		System.out.println("Connection closed...");
	}

}
