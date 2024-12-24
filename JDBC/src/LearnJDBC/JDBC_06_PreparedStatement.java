package LearnJDBC;

import java.sql.*;
import java.util.Scanner;

public class JDBC_06_PreparedStatement {
	public static void main(String[] args) throws ClassNotFoundException {
		
		String url = "jdbc:mysql://localhost:3306/LearnJDBC";
		String username = "root";
		String password = "GodOfWarMySQL3119";
		
		String query = "SELECT * FROM employee WHERE Name = ? AND ID = ?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver load successfully");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connection established successfully");
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the employee Name:");
			String employeeName = sc.next();
			System.out.println("Enter the employee ID:");
			int employeeId = sc.nextInt();
			
			
			PreparedStatement prestmt = conn.prepareStatement(query);
			prestmt.setString(1, employeeName);
			prestmt.setInt(2, employeeId);
			
			ResultSet rs = prestmt.executeQuery();
			
			boolean working = false;
			
			while(rs.next()) {
				
				int id = rs.getInt("ID");
				String name = rs.getString("Name");
				String jobtitle = rs.getString("Job_Title");
				double salary = rs.getDouble("Salary");
				
				System.out.printf("Employee ID = %d, Name = %s, Job Title = %s and Salary = %.2f \n", id, name, jobtitle, salary);
				
				working = true;
			}
			
			if (working) {
				System.out.println("Data Getting Succefully...");
			} else {
				System.out.println("Unexpected argument !!!");
			}
			
			
			rs.close();
			prestmt.close();
			conn.close();
			
			System.out.println("Driver close successfully");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
}













