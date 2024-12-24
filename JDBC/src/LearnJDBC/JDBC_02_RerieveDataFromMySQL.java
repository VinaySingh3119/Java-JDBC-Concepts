package LearnJDBC;

// This line imports all classes from the java.sql package.
// This package contains essential classes and interfaces for database connectivity.
import java.sql.*;

public class JDBC_02_RerieveDataFromMySQL {
	public static void main(String[] args) throws ClassNotFoundException {
		
		// JDBC URL, username and password
		String url = "jdbc:mysql://localhost:3306/LearnJDBC";
		String username = "root";
		String password = "GodOfWarMySQL3119";
		
		// Execute a query to retrieve data from the "employee" table
		String query = "SELECT * FROM employee;";
		
		
		// Load and register the JDBC Driver
//		Driver Manager: Responsible for managing database drivers. It loads the appropriate driver class based on the JDBC URL.
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded successully");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			//Establish the connection
//			Connection: Represents a connection to the database. You obtain a connection using the DriverManager.getConnection() method.
			Connection conn = DriverManager.getConnection(url,username,password); 
			System.out.println("Connection established successully");
			
			
			// Create a statement
//			Statement: Used to execute SQL queries. There are two types: "Statement" (for static SQL) and "PreparedStatement" (for parameterized queries).
			Statement stmt = conn.createStatement();
			
			
			// Process the result set
//			ResultSet: Represents the result of a query. It allows you to iterate through the retrieve data.
//			You typically use executeQuery() when you want to retrieve data from the database.
			ResultSet rs = stmt.executeQuery(query);
			
			
			// Initially, the cursor is positioned before the first row in the result set
			// When you call "rs.next()", it moves the cursor to the next row(if available).
			// If there is no next row, it return false, indicating that you've reached the end of the result set.
			while(rs.next()) {
				
				// To retrieve a integer value from a result set, use the getInt("ColumnName") method.
				int id = rs.getInt("ID");
				
				// To retrieve a string value from a result set, use the getString("ColumnName") method.
				String name = rs.getString("Name");
				String job_title = rs.getString("Job_title");
				
				// To retrieve decimal or floating-point values, use the getDouble("ColumnName") method.
				double salary = rs.getDouble("Salary");
				
				System.out.println();
				System.out.println("=====================================================");
				System.out.println("ID        : " + id);
				System.out.println("Name      : " + name);
				System.out.println("Job Title : " + job_title);
				System.out.println("Salary    : " + salary);
			}
			
			// Close the resources
			rs.close();
			stmt.close();
			conn.close();
			
			System.out.println();
			System.out.println("Connection close successfully");
			
		} catch (SQLException e) {
//			Exception: JDBC throws exceptions related to database connectivity, queries, and other error.
			System.out.println(e.getMessage());
		}
		
	}
}
