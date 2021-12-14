package com.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Joins {@SuppressWarnings("resource")
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
		System.out.println("Display emp details with 7% increment who belongs to BOSTON location.");
		System.out.println("There was no employee with Boston thats y used new york");
		String selectQuery="select e.ename, e.empno , d.loc ,sal, (sal+((sal*7)*0.01)) as incsal from  emp e inner join dept d using(deptno) having d.loc=\"NEW YORK\" ";
		rs =st.executeQuery(selectQuery);
		while(rs.next())
			System.out.println(rs.getInt("empno")+"\t"+rs.getString("ename")+"\t"+rs.getString("loc")+"\t"+rs.getString("sal")+"\t"+rs.getString("incsal"));
		System.out.println("  ");
        
		
		System.out.println("Count the no. of employees works for RESEARCH dept");
		String selectQuery2="select count(*)as count  from  emp e inner join dept d using(deptno) where d.dname=\"RESEARCH\"";
		rs =st.executeQuery(selectQuery2);
		while(rs.next())
			System.out.println("No.of employees works for RESEARCH dept: "+rs.getInt("count"));
		System.out.println("  ");
		
		System.out.println("Find out the max salary received in SALES dept");
		String selectQuery3="select max(sal)as max from emp e inner join dept d using(deptno) where d.dname=\"SALES\"";
		rs =st.executeQuery(selectQuery3);
		while(rs.next())
			System.out.println("MAX salary in SALES dept: "+rs.getInt("max"));
		System.out.println("  ");
		
		System.out.println("Display date of joining of emp whose dept location is not available");
		String selectQuery4="select e.hiredate as date, e.empno,e.ename,d.loc from emp e inner join dept d using(deptno) where d.loc is null";
		rs =st.executeQuery(selectQuery4);
		while(rs.next())
			System.out.println(rs.getInt("empno")+"\t"+rs.getDate("date")+"\t"+rs.getString("ename")+"\t"+rs.getString("loc"));
		System.out.println("  ");
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