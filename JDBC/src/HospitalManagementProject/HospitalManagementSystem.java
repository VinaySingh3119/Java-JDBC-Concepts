package HospitalManagementProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalManagementSystem {
	
	private static final String url = "jdbc:mysql://localhost:3306/JDBCHospital";
	private static final String username = "root";
	private static final String password = "GodOfWarMySQL3119";
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Scanner sc = new Scanner(System.in);
		
		try {
			Connection conn = DriverManager.getConnection(url,username,password);
			
			Patient patient = new Patient(conn, sc);
			Doctor doctor = new Doctor(conn);
			
			while (true) {
				System.out.println("*** HOSPITAL MANAGEMENT SYSTEM ***");
				System.out.println("1. Add Patient");
				System.out.println("2. View Patients");
				System.out.println("3. View Doctors");
				System.out.println("4. Book Appointment");
				System.out.println("5. Exit");
				System.out.println("Enter your choice: ");
				
				int choice = sc.nextInt();
				
				switch(choice) {
					case 1:
						patient.addPatients();
						System.out.println();
						break;
						
					case 2:
						patient.viewPatients();
						System.out.println();
						break;
						
					case 3:
						doctor.viewDoctors();
						System.out.println();
						break;
						
					case 4:
						bookAppointment(patient, doctor, conn, sc);
						System.out.println();
						break;
						
					case 5:
						System.out.println("Thank You for using Hospital Management System");
						return;
						
					default:
						System.out.println("Enter valid choice!");
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void bookAppointment(Patient patient, Doctor doctor, Connection conn, Scanner sc) {
		
		System.out.println("Enter Patient Id:");
		int patientId = sc.nextInt();
		
		System.out.println("Enter Doctor Id:");
		int doctorId = sc.nextInt();
		
		System.out.println("Enter Appointment Date (YYYY-MM-DD): ");
		String appointmentDate = sc.next();
		
		if (patient.getPetientById(patientId) && doctor.getDoctorById(doctorId)) {
			
			if(checkDoctorAvailability(doctorId, appointmentDate, conn)) {
				String appointmentQuery = "INSERT INTO appointments(patient_id, doctor_id, appointment_date) VALUES (?, ?, ?);";
				
				try {
					PreparedStatement preStmt = conn.prepareStatement(appointmentQuery);
					preStmt.setInt(1, patientId);
					preStmt.setInt(2, doctorId);
					preStmt.setString(3, appointmentDate);
					
					int rowsAffected = preStmt.executeUpdate();
					
					System.out.println("Appointment Booked");
					
				} catch (SQLException e) {
					System.out.println("Failed to Book Appointment!");
					e.printStackTrace();
				}
			}
			else {
				System.out.println("Doctor not available on this date!");
			}
		}
		else {
			System.out.println("Either Doctor and Patient does'n exist!");
		}
		
	}

	public static boolean checkDoctorAvailability(int doctorId, String appointmentDate, Connection conn) {
		
		String query = "SELECT COUNT(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?;";
		
		try {
			PreparedStatement preStmt = conn.prepareStatement(query);
			preStmt.setInt(1, doctorId);
			preStmt.setString(2, appointmentDate);
			ResultSet rs = preStmt.executeQuery();
			
			if (rs.next()) {
				int count = rs.getInt(1);
				
				if(count == 0) {
					return true;
				}else {
					return false;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
















