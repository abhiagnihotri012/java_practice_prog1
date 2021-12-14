package com.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTestWithImage {

	public static void main(String[] args){
		
		final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
		final String DB_USER = "root";
		final String DB_PASSWORD = "rohan";

		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		CallableStatement ct = null;
		try {
			// 1) register the driver
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded");
			
			// 2) creating a connection
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("Connection Established!!");
			
			//creating a table
//			String createTableQuery= "CREATE TABLE IF NOT EXISTS"
//					+ " profile(pid INT AUTO_INCREMENT PRIMARY KEY,"
//					+ "photo MEDIUMBLOB NOT NULL)";
//
//			pst = con.prepareStatement(createTableQuery);
//			System.out.println("Table Creation : "+pst.executeUpdate());
//			pst.close();
			
			//Inserting an image 
//			File pic = new File("C:\\Users\\Rohan\\Desktop\\rohan.jpeg");
//			FileInputStream fis = new FileInputStream(pic);
//			System.out.println(pic.length());		
//			pst = con.prepareStatement("INSERT INTO profile(photo) VALUES(?)");
//			pst.setBlob(1, fis, (int) pic.length());
//			System.out.println("Image Insertion : "+pst.executeUpdate());
//			pst.close();
			
			//Retrive Image
			pst =con.prepareStatement("SELECT * FROM profile WHERE pid=?");
			pst.setInt(1, 1);
			rs=pst.executeQuery();
			System.out.println(rs.getFetchSize());
		     if(rs.next())
		     {
		    	 File file=new File("C:\\Users\\Rohan\\Desktop\\image"+rs.getInt(1)+".jpg");
		    	 FileOutputStream fos=new FileOutputStream(file);
		    	 Blob blob=rs.getBlob("photo");
		    	 byte b[]=blob.getBytes(1,(int)blob.length());
		    	 fos.write(b);
		    	 System.out.println("image downloaded at"+file.getPath());
		    	 fos.close();
		     }
			
			rs.close();
			pst.close();
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			// 4) closing resources and connection
			try {
				
				if(pst != null)
					pst.close();
				if(rs != null)
					rs.close();
				
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

