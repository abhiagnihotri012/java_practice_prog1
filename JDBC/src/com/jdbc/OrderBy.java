package com.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrderBy {	@SuppressWarnings("resource")
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
		System.out.println("Increment sal by 7% who is a manager and display records in descending of sal?");
		String selectPreparedQuery="select empno,ename,job,sal, (sal+((sal*7)*0.01))as incrsal from emp where job = \"MANAGER\" order by incrsal desc";
		pst=con.prepareStatement(selectPreparedQuery);
		rs=pst.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt("empno")+"\t"+rs.getString("ename")+"\t"+rs.getString("job")+"\t"+rs.getInt("sal")+"\t"+ rs.getInt("incrsal"));
		}
		System.out.println("  ");	
		System.out.println("Display eno and ename who have sal between 2000 and 3000. Display the records order by empno in ascending");
		String selectPreparedQuery2="select empno , ename,sal from emp where sal between 1500 and 2000 order by empno asc";
		pst=con.prepareStatement(selectPreparedQuery2);
		rs=pst.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt("empno")+"\t"+rs.getString("ename")+"\t"+rs.getInt("sal"));
		}
		System.out.println("  ");
		System.out.println("Display unique records of empno and job who are from dept 10 or 20");
		String selectPreparedQuery3="select empno , job from emp where deptno in (10,20)";
		pst=con.prepareStatement(selectPreparedQuery3);
		rs=pst.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt("empno")+"\t"+rs.getString("job"));
		}
		System.out.println("  ");
		System.out.println("Display unique jobs from emp except manager");
		String selectPreparedQuery4="select job as newjob from emp where job != \"MANAGER\"";
		pst=con.prepareStatement(selectPreparedQuery4);
		rs=pst.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString("newjob"));
		}
		rs.close();
		pst.close();
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