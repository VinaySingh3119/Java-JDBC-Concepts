package HospitalManagementProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
	private Connection conn;
	private Scanner sc;
	
	
	public Patient(Connection conn, Scanner sc) {
		this.conn = conn;
		this.sc = sc;
	}
	
	public void addPatients() {
		System.out.print("Enter Patient Name: ");
		String name = sc.next();
		
		System.out.print("Enter Patient Age: ");
		int age = sc.nextInt();
		
		System.out.print("Enter Patient Gender: ");
		String gender = sc.next();
		
		try {
			String query = "INSERT INTO patients(name, age, gender) VALUES(?, ?, ?);";
			PreparedStatement preStmt = conn.prepareStatement(query);
			preStmt.setString(1, name);
			preStmt.setInt(2, age);
			preStmt.setString(3, gender);
			
			int rowsAffected = preStmt.executeUpdate();
			
			System.out.println("Patient Added Successfully");
			
		} catch (SQLException e) {
			System.out.println("Failed to Added Patient!");
			e.printStackTrace();
		}
		
	}

	
	public void viewPatients() {
		String query = "SELECT * FROM patients";
		
		try {
			PreparedStatement preStmt = conn.prepareStatement(query);
			ResultSet rs = preStmt.executeQuery();
			
			System.out.println("Patients: ");
			System.out.println("+------------+-----------------------+------------+---------------+");
			System.out.println("| Patient Id | Name                  | Age        | Gender        |");
			System.out.println("+------------+-----------------------+------------+---------------+");
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String gender = rs.getString("gender");
				
				System.out.printf("| %-11s| %-22s| %-11s| %-14s|\n", id, name, age, gender);
				System.out.println("+------------+-----------------------+------------+---------------+");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean getPetientById(int id) {
		String query = "SELECT * FROM patients WHERE id = ?;";	
		
		try {
			PreparedStatement preStmt = conn.prepareStatement(query);
			preStmt.setInt(1, id);
			ResultSet rs = preStmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}












