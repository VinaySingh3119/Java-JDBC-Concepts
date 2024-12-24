package BankingSystemProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Accounts {
	private Connection conn;
	private Scanner sc;
	
	
	public Accounts(Connection conn, Scanner sc) {
		this.conn = conn;
		this.sc = sc;
	}
	
	
	public long openAccount(String email) {
		
		if(!accountExist(email)) {
			String openAccountQuery = "INSERT INTO accounts (account_number, full_name, email, balance, security_pin) VALUES (?, ?, ?, ?, ?);";
			
			sc.nextLine();
			System.out.println("Enter Full Name: ");
			String fullName = sc.nextLine();
			
			System.out.println("Enter Initial Amount: ");
			double balance = sc.nextDouble();
			sc.nextLine();
			
			System.out.println("Enter Security Pin: ");
			String securityPin = sc.nextLine();
			
			try {
				long accountNumber = generateAccountNumber();
				
				PreparedStatement preStmt = conn.prepareStatement(openAccountQuery);
				preStmt.setLong(1, accountNumber);
				preStmt.setString(2, fullName);
				preStmt.setString(3, email);
				preStmt.setDouble(4, balance);
				preStmt.setString(5, securityPin);
				
				int rowsAffected = preStmt.executeUpdate();
				
				return accountNumber;
				
				
				
			} catch (SQLException e) {
				
				System.out.println("Account create failed");
				System.out.println(e.getMessage());
			}
		}
		
		throw new RuntimeException("Account Already Exist!!");
		
	}
	
	
	public long getAccountNumber(String email) {
		String query = "SELECT account_number FROM accounts WHERE email = ?;";
		
		try {
			PreparedStatement preStmt = conn.prepareStatement(query);
			preStmt.setString(1,email);
			ResultSet rs = preStmt.executeQuery();
			
			if(rs.next()) {
				return rs.getLong("account_number");
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		throw new RuntimeException("Account number does'n exist!!");
	}
	
	
	public long generateAccountNumber() {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT account_number FROM accounts ORDER BY account_number DESC LIMIT 1;");
			
			if(rs.next()) {
				long lastAccountNumber = rs.getLong("account_number");
				return lastAccountNumber + 1;
			}
			else {
				return 10000100;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return 10000100;
	}
	
	
	public boolean accountExist(String email) {
		
		String query = "SELECT account_number FROM accounts WHERE email = ?";
		
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
