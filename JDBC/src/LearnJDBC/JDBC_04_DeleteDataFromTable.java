package LearnJDBC;

import java.sql.*;

public class JDBC_04_DeleteDataFromTable {
	public static void main(String[] args) throws ClassNotFoundException {
		
		// JDBC URL, username and password
		String url = "jdbc:mysql://localhost:3306/LearnJDBC";
		String username = "root";
		String password = "GodOfWarMySQL3119";
		
		// Execute a query to delete data from the "employee" table
		String deleteQuery = "DELETE FROM employee WHERE ID = 4";
		
		try {
			// Load and register the JDBC Driver
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded successfully");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			//Establish the connection
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connection established successfully");
			
			// Create a statement
			Statement stmt = conn.createStatement();
			
			int rowsaffected = stmt.executeUpdate(deleteQuery);
			
			if (rowsaffected > 0) {
				System.out.println("Deletion successfull. "+ rowsaffected + " row(s) affected.");
			}
			else {
				System.out.println("Delition failed!!");
			}
			
			
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
