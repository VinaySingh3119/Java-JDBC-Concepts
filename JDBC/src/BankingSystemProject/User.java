package BankingSystemProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSet;


public class User {
	
	private Connection conn;
	private Scanner sc;

	public User(Connection conn, Scanner sc) {
		this.conn = conn;
		this.sc = sc;
	}
	
	
	public void register() {
		
		sc.nextLine();
		System.out.println("Full name: ");
		String fullName = sc.nextLine();
		
		System.out.println("Email: ");
		String email = sc.nextLine();
				
		System.out.println("Password: ");
		String password = sc.nextLine();
		
		if(userExist(email)) {
			System.out.println("User already exist for this email address!!");
			return;
		}
		
		String register = "INSERT INTO user (full_name, email, password) VALUES (?, ?, ?);";
		
		try {
			
			PreparedStatement preStmt = conn.prepareStatement(register);
			preStmt.setString(1, fullName);
			preStmt.setString(2, email);
			preStmt.setString(3, password);
			
			int rowsAffected = preStmt.executeUpdate();
			
			System.out.println("Register successful.");
			
			
		} catch (SQLException e) {
			System.out.println("Register failed");
			System.out.println(e.getMessage());
		}
		
		
	}
	
	
	public String login() {
		sc.nextLine();
		System.out.println("Email: ");
		String email = sc.nextLine();
		
		System.out.println("Password: ");
		String password = sc.nextLine();
		
		String loginQuery = "SELECT * FROM user WHERE email = ? AND password = ?;";
		
		try {
			PreparedStatement preStmt = conn.prepareStatement(loginQuery);
			preStmt.setString(1, email);
			preStmt.setString(2, password);
			
			ResultSet rs = preStmt.executeQuery();
			
			if(rs.next()){
				return email;
			}
			else {
				return null;
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return null;
		
	}
	
	
	private boolean userExist(String email) {
		
		String query = "SELECT * FROM user WHERE email = ?";
		
		try {
			PreparedStatement preStmt = conn.prepareStatement(query);
			preStmt.setString(1, email);
			
			ResultSet rs = preStmt.executeQuery();
			if(rs.next()) {
				return true;
			}
			else {
				return false;
			}
					
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
}











