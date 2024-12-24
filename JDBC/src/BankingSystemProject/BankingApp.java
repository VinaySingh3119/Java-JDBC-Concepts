package BankingSystemProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BankingApp {
	
	private static final String url = "jdbc:mysql://localhost:3306/JDBCBankingSystem";
	private static final String username = "root";
	private static final String password = "GodOfWarMySQL3119";
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver load successful");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			
			Connection conn = DriverManager.getConnection(url,username,password);
			Scanner sc = new Scanner(System.in);
			
			User user = new User(conn, sc);
			Accounts accounts = new Accounts(conn, sc);
			AccountManager accountManager = new AccountManager(conn, sc);
			
			String email;
			long accountNumber = 0;
			
			while(true) {
				System.out.println("*** WELCOME TO BANKING SYSTEM ***");
				System.out.println();
				System.out.println("1. Register");
				System.out.println("2. Login");
				System.out.println("3. Exit");
				System.out.println("Enter your choice: ");
				
				int choice1 = sc.nextInt();
				
				switch(choice1) {
					case 1:
						user.register();
						// System.out.print("\03[H\033[2J");
						// 6System.out.flush();
						break;
						
					case 2:
						email = user.login();
						if (email == null) {
							System.out.println("Wrong Password!");
							break;
						}
						
						if(email != null) {
							System.out.println();
							System.out.println("User Logged In!");

							if(!accounts.accountExist(email)) {
								System.out.println();
								System.out.println("1. Open a new Bank Account");
								System.out.println("2. Exit");
								
								if(sc.nextInt() == 1) {
									accountNumber = accounts.openAccount(email);
									System.out.println("Account Created Successfully");
									System.out.println("Your Account Number is: " + accountNumber);
								}
								else {
									break;
								}
								
							}
							
							accountNumber = accounts.getAccountNumber(email);
							
							int choice2 = 0;
							
							while(choice2 != 5) {
								System.out.println();
								System.out.println("1. Debit Money");
								System.out.println("2. Credit Money");
								System.out.println("3. Transfer Money");
								System.out.println("4. Check Balance");
								System.out.println("5. Log Out");
								System.out.println("Enter Your Choice: ");
								choice2 = sc.nextInt();
								
								switch(choice2) {
									case 1:
										accountManager.debitMoney(accountNumber);
										break;
										
									case 2:
										accountManager.creditMoney(accountNumber);
										break;
										
									case 3:
										accountManager.transferMoney(accountNumber);
										break;
										
									case 4:
										accountManager.getBalance(accountNumber);
										break;
										
									case 5:
										break;
										
									default:
										System.out.println("Enter Valid Choice!");
										break;
								}
							}
							
						}
						
					case 3:
						System.out.println("THANK YOU FOR USING BANKING SYSTEM!!!");
						System.out.println("Existing System!!");
						return;
						
					default:
						System.out.println("Enter Valid Choice!");
						break;
				}
				
			}
						
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

}



















