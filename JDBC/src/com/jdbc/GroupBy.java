package com.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class GroupBy {
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
			String selectQuery="select count(*) as noOfEmp from emp";
			rs =st.executeQuery(selectQuery);
			while(rs.next())
				System.out.println("Total No of Employees: "+rs.getInt("noOfEmp"));
			System.out.println("  ");
			
			System.out.println(" Count dept wise total emps");
			String selectQuery2="select deptno,count(*) as emp from emp group by deptno";
			rs =st.executeQuery(selectQuery2);
			while(rs.next())
				System.out.println(rs.getInt("deptno")+"\t"+rs.getInt("emp"));
			System.out.println("  ");
			
			System.out.println("Display min salary of emps");
			String selectQuery3="select empno , ename,sal from emp where sal =(select min(sal) from emp);";
			rs =st.executeQuery(selectQuery3);
			while(rs.next())
				System.out.println(rs.getInt("empno")+"\t"+rs.getString("ename")+"\t"+rs.getInt("sal"));
			System.out.println("  ");
			
			System.out.println("Display the max salary of each dept");
			String selectQuery4="select deptno,max(sal) as maxsal from emp group by deptno";
			rs =st.executeQuery(selectQuery4);
			while(rs.next())
				System.out.println(rs.getInt("deptno")+"\t"+rs.getInt("maxsal"));
			System.out.println("  ");

			System.out.println("Count people have salary > 2000");
			String selectQuery5="select count(*) as count from emp where sal > 2000;";
			rs =st.executeQuery(selectQuery5);
			while(rs.next())
				System.out.println("Emp with sal > 2000: "+rs.getInt("count"));
			System.out.println("  ");
			
			System.out.println(" Count people from each dept have salary < 3000");
			String selectQuery6="select  deptno,count(*)as count from emp where sal < 3000 group by deptno";
			rs =st.executeQuery(selectQuery6);
			while(rs.next())
				System.out.println(rs.getInt("deptno")+"\t"+rs.getInt("count"));
			System.out.println("  ");
			
			System.out.println("Display dept has <= 3 people");
			String selectQuery7="select deptno, count(*) as count from emp group by deptno having count <=3";
			rs =st.executeQuery(selectQuery7);
			while(rs.next())
				System.out.println(rs.getInt("deptno")+"\t"+rs.getInt("count"));
			System.out.println("  ");
			
			
			System.out.println("Display deptno where dept's max(sal) > 3000");
			String selectQuery8="select deptno  from emp group by deptno having max(sal) >3000";
			rs =st.executeQuery(selectQuery8);
			while(rs.next())
				System.out.println("Dept with max salary > 3000: "+rs.getInt("deptno"));
			System.out.println("  ");
//			pst.close();
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