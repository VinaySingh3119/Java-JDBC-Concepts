package LearnJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class JDBC_11_BatchProcessing {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String url = "jdbc:mysql://localhost:3306/LearnJDBC";
		String username = "root";
		String password = "GodOfWarMySQL3119";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Connection estblish successful");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connection establish successful");
			
			conn.setAutoCommit(false);
			
		
//// Batch Processing using simple Statement
			
//			Statement stmt = conn.createStatement();
//			stmt.addBatch("INSERT INTO employee (Name, Job_Title, Salary) VALUES ('Vashu', 'HR Manager', 65000.0)");
//			stmt.addBatch("INSERT INTO employee (Name, Job_Title, Salary) VALUES ('Karan', 'Cyber Security Analyst', 62000.0)");
//			stmt.addBatch("INSERT INTO employee (Name, Job_Title, Salary) VALUES ('Vipu;', 'DevOps Engineer', 67000.0)");
			
//			int[] batchResult = stmt.executeBatch();
//			conn.commit();
//			System.out.println("Batch execute successfuly");
			
			
// Batch Processing using Prepared Statement
			
			String query = "INSERT INTO employee (Name, Job_Title, Salary) VALUES (?, ?, ?)";
			
			PreparedStatement preStmt = conn.prepareStatement(query);
			
			Scanner sc = new Scanner(System.in);
			
			while(true) {
				System.out.print("Name: ");
				String name = sc.nextLine();
				
				System.out.print("Job Title: ");
				String jobTitle = sc.nextLine();
				
				System.out.print("Salary: ");
				double salary = sc.nextDouble();
				sc.nextLine();
				
				preStmt.setString(1, name);
				preStmt.setString(2, jobTitle);
				preStmt.setDouble(3, salary);
				preStmt.addBatch();
				
				System.out.println("Add more values Y/N: ");
				String decision = sc.nextLine();
				
				if(decision.toUpperCase().equals("N")) {
					break;
				}
				
				
			}
			
			int[] batchResult = preStmt.executeBatch();
			
			conn.commit();
			System.out.println("Batch execute succesfully");
					
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
}











