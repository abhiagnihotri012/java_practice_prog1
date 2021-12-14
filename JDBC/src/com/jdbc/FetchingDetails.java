package com.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.DatabaseMetaData;

public class FetchingDetails {

	@SuppressWarnings("resource")
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
			System.out.println("Use database mydb and emp table from it");
			String selectQuery="Select * from emp";
			rs =st.executeQuery(selectQuery);
			while(rs.next())
				System.out.println(rs.getInt("empno")+"\t"+
			rs.getString("ename")+"\t"+rs.getString("job")+"\t"+"\t"+rs.getInt("mgr")+"\t"+"\t"+rs.getDate("hiredate")+"\t"+"\t"+rs.getBigDecimal("sal"));
			rs.close();
			System.out.println("  ");
			
			System.out.println("Decrement sal by 5% who is a clerk");
			String selectPreparedQuery="select empno , ename ,sal, ((sal*0.1)/2) as decsal from emp where job = ?";
			pst=con.prepareStatement(selectPreparedQuery);
			pst.setString(1, "CLERK");
			rs=pst.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("empno")+"\t"+rs.getString("ename")+"\t"+rs.getInt("sal")+"\t"+ rs.getInt("decsal"));
			}
			System.out.println("  ");
			
			System.out.println("Display eno and ename who have sal between 2000 and 3000");
			String selectQuery2="select empno , ename, sal from emp where sal BETWEEN ? AND ?";
			pst=con.prepareStatement(selectQuery2);
			pst.setInt(1,1500);
			pst.setInt(2,2000);
			rs=pst.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("empno")+"\t"+rs.getString("ename")+"\t"+rs.getInt("sal"));
			}
			System.out.println("  ");
			
            System.out.println("Display eno and job who are from dept 10 or 20");
			String selectQuery3="select empno , mgr , deptno from emp where deptno in(?,?)";
			pst=con.prepareStatement(selectQuery3);
			pst.setInt(1,10);
			pst.setInt(2,20);
			rs=pst.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("empno")+"\t"+rs.getString("mgr")+"\t"+rs.getInt("deptno"));
			}
			System.out.println("  ");
			
			System.out.println("Display eno,mgr who have salary > 2000 or deptno > 10 or job is clerk");
			String selectQuery4="select empno,mgr,sal,deptno,job  from emp where sal > 1600 or deptno > 10 or job =\"CLERK\"";
			pst=con.prepareStatement(selectQuery4);
			rs=pst.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("empno")+"\t"+rs.getString("mgr")+"\t"+rs.getInt("sal")+"\t"+rs.getString("job")+"\t"+rs.getInt("deptno"));
			}
			System.out.println("  ");
			
			System.out.println("Increment sal by 15% who is not reporting to any one");
			String selectQuery5="select ename,empno , (sal + (((sal*0.1)/2)+(sal*0.1))) as incsal , sal from emp where mgr is null and comm is null";
			pst=con.prepareStatement(selectQuery5);
			rs=pst.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("empno")+"\t"+rs.getString("ename")+"\t"+rs.getInt("incsal")+"\t"+rs.getInt("sal"));
			}
			System.out.println("  ");
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
					st.close();
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