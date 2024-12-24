package BankingSystemProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {
	
	private Connection conn;
	private Scanner sc;
	
	public AccountManager(Connection conn, Scanner sc) {
		this.conn = conn;
		this.sc = sc;
	}

	
	public void creditMoney(long accountNumber) throws SQLException{
		sc.nextLine();
		System.out.println("Enter Amuont: ");
		double amount = sc.nextDouble();
		sc.nextLine();
		
		System.out.println("Security Pin: ");
		String securityPin = sc.nextLine();
		
		try {
			
			conn.setAutoCommit(false);
			
			if(accountNumber != 0) {
				
				PreparedStatement preStmt1 = conn.prepareStatement("SELECT * FROM accounts WHERE account_number = ? AND security_pin = ?");
				preStmt1.setLong(1, accountNumber);
				preStmt1.setString(2, securityPin);
				ResultSet rs = preStmt1.executeQuery();
				
				if(rs.next()) {
					
					String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?;";
					PreparedStatement preStmt2 = conn.prepareStatement(creditQuery);
					preStmt2.setDouble(1, amount);
					preStmt2.setLong(2,accountNumber);
					
					int rowsAffected = preStmt2.executeUpdate();
					
					System.out.println("Rs." + amount + " credit successfully.");
					conn.commit();
					conn.setAutoCommit(true);
					
				}
				else {
					System.out.println("Invalid Pin!");
				}
				
			}
			
		} catch (SQLException e) {
			System.out.println("Transaction failed!");
			conn.rollback();
			conn.setAutoCommit(true);			
			System.out.println(e.getMessage());
		}
		
	}
	
	
	public void debitMoney(long accountNumber) throws SQLException{

		sc.nextLine();
		System.out.println("Enter Amount: ");
		double amount = sc.nextDouble();
		sc.nextLine();
		
		System.out.println("Security Pin: ");
		String securityPin = sc.nextLine();
		
		try {
			
			conn.setAutoCommit(false);
			
			if(accountNumber != 0) {
				
				PreparedStatement preStmt1 = conn.prepareStatement("SELECT * FROM accounts WHERE account_number = ? AND security_pin = ?");
				preStmt1.setLong(1, accountNumber);
				preStmt1.setString(2, securityPin);
				ResultSet rs = preStmt1.executeQuery();
				
				if(rs.next()) {
					double currentBalance = rs.getDouble("balance");
					
					if(amount <= currentBalance) {
						String debitQuery = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?;";
						PreparedStatement preStmt2 = conn.prepareStatement(debitQuery);
						preStmt2.setDouble(1, amount);
						preStmt2.setLong(2,accountNumber);
						
						int rowsAffected = preStmt2.executeUpdate();
						
						System.out.println("Rs." + amount + " debit successfully.");
						conn.commit();
						conn.setAutoCommit(true);
						
					}
					else {
						System.out.println("Insufficent Balance!");
					}
					
				}
				else {
					System.out.println("Invalid Pin!");
				}
				
			}
			
		} catch (SQLException e) {
			System.out.println("Transaction failed!");
			conn.rollback();
			conn.setAutoCommit(true);			
			System.out.println(e.getMessage());
		}
		
	}

	
	public void transferMoney(long accountNumber) throws SQLException {
		sc.nextLine();
		System.out.println("Enter Reciver Account Number: ");
		long reciverAccountNumber = sc.nextLong();
		sc.nextLine();
		
		System.out.println("Enter Amount: ");
		double amount = sc.nextDouble();
		sc.nextLine();
		
		System.out.println("Enter Security Pin: ");
		String securityPin = sc.nextLine();
		
		try {
			conn.setAutoCommit(false);
			if (accountNumber != 0 && reciverAccountNumber != 0) {
				PreparedStatement preStmt = conn.prepareStatement("SELECT * FROM accounts WHERE account_number = ? AND security_pin = ?;");
				preStmt.setLong(1, accountNumber);
				preStmt.setString(2, securityPin);
				
				ResultSet rs = preStmt.executeQuery();
				
				if(rs.next()) {
					double currentBalance = rs.getDouble("balance");
					
					if (amount <= currentBalance) {
						String debitQuery = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?;";
						String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?;";
						
						PreparedStatement debitPreStmt = conn.prepareStatement(debitQuery);
						PreparedStatement creditPreStmt = conn.prepareStatement(creditQuery);
						
						debitPreStmt.setDouble(1, amount);
						debitPreStmt.setLong(2, accountNumber);
						
						creditPreStmt.setDouble(1, amount);
						creditPreStmt.setLong(2, reciverAccountNumber);
						
						int rowsAffected1 = debitPreStmt.executeUpdate();
						int rowsAffected2 = creditPreStmt.executeUpdate();
						
						if (rowsAffected1 > 0 && rowsAffected2 > 0) {
							System.out.println("Transaction Successful");
							System.out.println("Rs." + amount +" Transfer Successful");
							conn.commit();
							conn.setAutoCommit(true);
						}
						
					}
					else {
						System.out.println("Insufficent Balance!");
					}
					
				}
				else {
					System.out.println("Invalid Security Pin!");
				}
				
			}
			else {
				System.out.println("Invalid Account Number!");
			}
		} catch (SQLException e) {
			System.out.println("Transaction Failed!");
			conn.rollback();
			conn.setAutoCommit(true);
			System.out.println(e.getMessage());
		}
	}


	public void getBalance(long accountNumber) {
		sc.nextLine();
		System.out.println("Enter Security Pin: ");
		String securityPin = sc.nextLine();
		
		try {
			PreparedStatement preStmt = conn.prepareStatement("SELECT balance FROM accounts WHERE account_number = ? AND security_pin = ?;");
			preStmt.setLong(1, accountNumber);
			preStmt.setString(2, securityPin);
			
			ResultSet rs = preStmt.executeQuery();
			
			if (rs.next()) {
				double balance = rs.getDouble("balance");
				System.out.println("Balance: " + balance);
			}
			
		} catch (SQLException e) {
			System.out.println("Invalid Pin!");
			System.out.println(e.getMessage());
		}
		
	}
	
}










