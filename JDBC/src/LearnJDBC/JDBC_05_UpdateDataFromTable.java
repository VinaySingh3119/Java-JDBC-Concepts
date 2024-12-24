package LearnJDBC;

import java.sql.*;

public class JDBC_05_UpdateDataFromTable {
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/LearnJDBC";
		String username = "root";
		String password = "GodOfWarMySQL3119";
		
		String updateQuery = "UPDATE employee SET Name = 'Vinay', Salary = '165000.0' WHERE ID = 3;";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded successfully");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		try {
			// Established the connection
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connection established successfully");
			
			// Create a statement
			Statement stmt = conn.createStatement();
			
			// Execute query
			int rowsaffected = stmt.executeUpdate(updateQuery);
			
			System.out.println("Query update successfully: " + rowsaffected + " row(s) affected");
			
			// Close the resources
			stmt.close();
			conn.close();
			System.out.println();
			System.out.println("Connection close successfully");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}
