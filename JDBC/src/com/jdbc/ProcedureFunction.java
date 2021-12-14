package com.jdbc;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

public class ProcedureFunction {@SuppressWarnings("resource")
public static void main(String[] args) {
	// TODO Auto-generated method stub
	final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
	final String DB_USER = "root";
	final String DB_PASSWORD = "rohan";

	Connection con = null;
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement pst=null;
	CallableStatement cst=null;

	try {
		// register the driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Loaded!!!");

		// creating a connection
		con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		System.out.println("Connection established!!");
	
		//use statement object to perform queries
		st=con.createStatement();
		System.out.println(" ");
		System.out.println("Create a function to give incremented salary value if passed Int and Percentage parameter");
		String prepareCallQuery="{?=CALL mydb.salIncre(?,?)}";
		cst = con.prepareCall(prepareCallQuery);
		cst.registerOutParameter(1, Types.INTEGER);
		Scanner input = new Scanner(System.in);
		System.out.println("Enter salary");
		int sal = input.nextInt();
		System.out.println("Enter percentage increment u want");
		int percentage = input.nextInt();
		cst.setInt(2, sal);
		cst.setInt(3, percentage);
		cst.execute();
		Integer ans = cst.getInt(1);
		System.out.println(ans);
		System.out.println(" ");
		
		
	}
	
	catch (Exception e) {
		e.printStackTrace();
	}
	finally {
		try {
			if(cst!=null)
				cst.close();
			if(pst!=null)
				pst.close();
			if(st!=null)
				st.close();
			if(rs!=null)
				rs.close();
			if (con != null) {
				con.close();
				System.out.println("Connection closed");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

}