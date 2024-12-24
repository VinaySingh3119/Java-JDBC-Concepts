package LearnJDBC;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC_08_ImageHandling {
	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException {
		
		String url = "jdbc:mysql://localhost:3306/LearnJDBC";
		String username = "root";
		String password = "GodOfWarMySQL3119";
		
		String image_path = "C:\\Users\\admin\\Pictures\\Assignment\\OIP.jpg";
		
		String query = "INSERT INTO image_table (image_data) values (?);";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver load successfull");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connection establish successfull");
			
			FileInputStream fileInputStream = new FileInputStream(image_path);
			// FileInputStream class is useful to read data from a file in the form of sequence of bytes
			
			byte[] imageData = new byte[fileInputStream.available()]; 
			// Returns an estimate of the number of remaining bytes that can be read (or skipped over) from this input stream.
			
			fileInputStream.read(imageData);
			// Reads a byte of data from this input stream
			
			PreparedStatement prestmt = conn.prepareStatement(query);
			prestmt.setBytes(1, imageData);
			
			int rowsAffect = prestmt.executeUpdate();
			
			System.out.println("Image insertion successfull");
			
			prestmt.close();
			conn.close();
			
			System.out.println("Conection close successfull");
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} 
		
	}
}
