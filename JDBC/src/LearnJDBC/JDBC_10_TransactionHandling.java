package LearnJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC_10_TransactionHandling {
	public static void main(String[] args) throws ClassNotFoundException {
		
		String url = "jdbc:mysql://localhost:3306/LearnJDBC";
		String username = "root";
		String password = "GodOfWarMySQL3119";
		
		String withdrowQuery = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
		String depositQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?;";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver load successful");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connection establish successful");
			conn.setAutoCommit(false);
			
			try {
				
				PreparedStatement withdrowStmt = conn.prepareStatement(withdrowQuery);
				withdrowStmt.setDouble(1, 500.00);
				withdrowStmt.setString(2, "account123");
				
				PreparedStatement depositStmt = conn.prepareStatement(depositQuery);
				depositStmt.setDouble(1, 500.00);
				depositStmt.setString(2, "account456");
								
				
				withdrowStmt.executeUpdate();
				depositStmt.executeUpdate();
				
				conn.commit();
				System.out.println("Transaction successfully");
				
			} catch (SQLException e) {
				conn.rollback();
				System.out.println("Transaction failed!");
				System.out.println(e.getMessage());
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
