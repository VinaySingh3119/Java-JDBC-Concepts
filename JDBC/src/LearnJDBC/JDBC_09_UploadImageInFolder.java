package LearnJDBC;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JDBC_09_UploadImageInFolder {
	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException {
		
		String url = "jdbc:mysql://localhost:3306/LearnJDBC";
		String username = "root";
		String password = "GodOfWarMySQL3119";
		
		String folder_path = "C:\\SQL\\Insert image using JDBC\\";
		
		String query = "SELECT image_data FROM image_table WHERE image_id = (?);";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver load successfull");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connection establish successfull");
			
			PreparedStatement prestmt = conn.prepareStatement(query);
			prestmt.setInt(1, 1);
			
			ResultSet rs = prestmt.executeQuery();
			
			
			if (rs.next()) {
				byte[] imageData = rs.getBytes("image_data");
				
				String image_path = folder_path + " extrectedImage2.jpg";
				
				OutputStream outputStream = new FileOutputStream(image_path);
				// FileOutputStream is an outputstream for writing data/streams of raw bytes to file or storing data to file.
				
				outputStream.write(imageData);
				// This will enable us to write data to the file. Then, to write data to the file, we should write data using the FileOutputStream as
				
				
				System.out.println("Image iteration successfull");
				
			}
			
			rs.close();
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
