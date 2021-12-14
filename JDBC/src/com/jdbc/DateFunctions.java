package com.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DateFunctions{ @SuppressWarnings("resource")
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
		
		System.out.println("Display the most seniour employees? (Based on days)");
		String selectQuery1 = "select  ename, DATEDIFF(CURDATE(),hiredate) as expdays from emp having max(expdays); ";
		rs = st.executeQuery(selectQuery1);
		while (rs.next())
			System.out.println(rs.getString("ename")+"\t"+rs.getInt("expdays"));
		rs.close();
		System.out.println(" ");
		
		System.out.println("Give 5% increment to employees who are here since last 3 years?");
		String selectQuery2 = "select ename, sal , hiredate,(1.05*sal) as incre_sal, year(curDate())-year(hiredate) as exp from emp where year(curDate())-year(hiredate)>=3;;";
		rs = st.executeQuery(selectQuery2);
		while (rs.next())
			System.out.println(rs.getString("ename")+"\t"+rs.getInt("sal")+"\t"+rs.getInt("incre_sal")+"\t"+rs.getString("hiredate") +"\t"+ rs.getInt("exp"));
		rs.close();
		System.out.println(" ");
		
		
		System.out.println("Give 7% increment to employees who are here since last 5 years and salary < 2000?");
		String selectQuery3 ="select ename,SAL,hiredate,(1.07*sal) as incre_sal, year(curDate())-year(hiredate) as exp from emp where year(curdate())-year(hiredate)>=5 and sal<2000";
		rs = st.executeQuery(selectQuery3);
		while (rs.next())
			System.out.println(rs.getString("ename")+"\t"+rs.getInt("SAL")+"\t"+rs.getInt("incre_sal")+"\t"+rs.getString("hiredate") +"\t"+ rs.getInt("exp"));
		rs.close();
		System.out.println(" ");
		
		System.out.println("Display yearly report which shows year wise no. of joinings");
		String selectQuery4 = "select  count(*) as noOfJoinings,DATE_FORMAT(hiredate, \"%Y\")as year from emp group by year order by year asc;";
		rs = st.executeQuery(selectQuery4);
		while (rs.next())
			System.out.println(rs.getInt("noOfJoinings")+"\t"+rs.getInt("year"));
		rs.close();
		System.out.println(" ");
		
		System.out.println("Display monthly report which shows year wise no. of joinings");
		String selectQuery5 = "select  count(*) as noOfJoinings,DATE_FORMAT(hiredate, \"%M\")as month from emp group by month order by month asc;";
		rs = st.executeQuery(selectQuery5);
		while (rs.next())
			System.out.println(rs.getInt("noOfJoinings")+"\t"+rs.getString("month"));
		rs.close();
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