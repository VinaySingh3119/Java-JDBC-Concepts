package LearnJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC_07_InsertUsingPreparedStatement {
	public static void main(String[] args) throws ClassNotFoundException {
		
		String url = "jdbc:mysql://localhost:3306/LearnJDBC";
		String username = "root";
		String password = "GodOfWarMySQL3119";
		
		String query = "INSERT INTO employee (ID, Name, Job_Title, Salary) "
				+ " Values(?, ?, ?, ?);";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver load Successfully");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connection established successfull");
			
			Scanner sc = new Scanner(System.in);
						
			System.out.println("Enter employee ID:");
			int employeeId = sc.nextInt();
			sc.nextLine();
			
			System.out.println("Enter employee Name:");
			String employeeName = sc.nextLine();
			
			System.out.println("Enter employee Job Title:");
			String employeeJobTitle = sc.nextLine();
			
			System.out.println("Enter employee Salary:");
			double employeeSalary = sc.nextDouble();
			
			
			PreparedStatement prestmt = conn.prepareStatement(query);
			
			prestmt.setInt(1, employeeId);
			prestmt.setString(2, employeeName);
			prestmt.setString(3, employeeJobTitle);
			prestmt.setDouble(4, employeeSalary);
			
			int rowsAffected = prestmt.executeUpdate();
			
			System.out.println("Enter data sucessfully " + rowsAffected + " row(s) affected");
			
			prestmt.close();
			conn.close();
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
