package LearnJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class Test{
	private static final String url = "jdbc:mysql://localhost:3306/learnservlet";
	private static final String username = "root";
	private static final String password = "GodOfWarMySQL3119";
	
	public static void main(String[] args) {
		
		
		String name = "Vinay Singh";
		String email = "vinaysingh31193660@gmail.com";
		String pass = "31193660";
		
		insertData(name, email, pass);
		
	}
	
	public static void insertData(String name, String email, String password1) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver load successfull");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connection establish successfull");
			
			String query = "insert into user(name,email,password) values(?, ?, ?);";
			PreparedStatement preStmt = conn.prepareStatement(query);
			preStmt.setString(1, name);
			preStmt.setString(2, email);
			preStmt.setString(3, password1);
			
			preStmt.executeUpdate();
			
			System.out.println("Data insert Successfull");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
