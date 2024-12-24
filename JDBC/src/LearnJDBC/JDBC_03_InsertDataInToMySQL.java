package LearnJDBC;

import java.sql.*;

public class JDBC_03_InsertDataInToMySQL {
	public static void main(String[] args) throws ClassNotFoundException {
		
		// JDBC URL, username and password
		String url = "jdbc:mysql://localhost:3306/LearnJDBC";
		String username = "root";
		String password = "GodOfWarMySQL3119";
		
		// Execute a query to insert data into the "employee" table
		String query = "INSERT INTO employee(ID , Name, Job_title, Salary) VALUES (4, 'Harry', 'Senior Developer', 120000.0);";
		
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
			
//			The executeUpdate(query) method is used to execute SQL statements that modify the database. These statements include INSERT, UPDATE, and DELETE queries.
			int rowsaffected = stmt.executeUpdate(query);
			
			if(rowsaffected > 0) {
				System.out.println("Insert data successfull. "+ rowsaffected + " row(s) affected.");
				
			}else {
				System.out.println("Insertion failed!!");
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
 