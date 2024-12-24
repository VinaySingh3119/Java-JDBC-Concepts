package HospitalManagementProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
	
	private Connection conn;
	
	
	public Doctor(Connection conn) {
		this.conn = conn;
	}
	
	
	public void viewDoctors() {
		String query = "SELECT * FROM doctors";
		
		try {
			PreparedStatement preStmt = conn.prepareStatement(query);
			ResultSet rs = preStmt.executeQuery();
			
			System.out.println("Doctors: ");
			System.out.println("+------------+-----------------------+----------------------------+");
			System.out.println("| Doctor Id  | Name                  | Specilization              |");
			System.out.println("+------------+-----------------------+----------------------------+");
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String specilization = rs.getString("specilization");
				
				System.out.printf("| %-11s| %-22s| %-27s|\n", id, name, specilization);
				System.out.println("+------------+-----------------------+----------------------------+");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean getDoctorById(int id) {
		String query = "SELECT * FROM doctors WHERE id = ?;";	
		
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
