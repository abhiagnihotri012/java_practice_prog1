package com.jdbc;

import java.*;

import java.sql.Connection;
import java.sql.CallableStatement;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

import com.mysql.cj.jdbc.DatabaseMetaData;
import com.mysql.cj.jdbc.Driver;

public class JDBC_Test {

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
			
			//create a table
			String createTableQuery ="Create Table Sample"
			+"(sid int AUTO_INCREMENT primary key,sname varchar(50) not null )";
			//System.out.println("Table Creation: "+st.executeUpdate(createTableQuery));
		
			//insert record into table
			String insertTableQuery="insert into Sample(sname) Values('abc'),('pqr'),('cde')";
			//System.out.println("Record Inserted :"+st.executeUpdate(insertTableQuery));
			
			
			//update a record
			String updateQuery="UPDATE Sample set sname='xyz' where sid=1";
			//System.out.println("Record Updated :"+st.executeUpdate(updateQuery));
			
			//delete a record
			String deleteQuery="Delete from sample where sid=7";
			//System.out.println("Record deleted"+st.executeUpdate(deleteQuery));
		

			//fetch the records
			String selectQuery="Select * from Sample";
			rs =st.executeQuery(selectQuery);
			while(rs.next())
				System.out.println(rs.getInt("sid")+"\t"+rs.getString("sname"));
			rs.close();

			System.out.println("======================");
//			//using prepared statement
//			String selectPreparedQuery="select * from sample where sid=?";
//			pst=con.prepareStatement(selectPreparedQuery);
//			pst.setInt(1, 2);
//			rs=pst.executeQuery();
//			if(rs.next())
//				System.out.println(rs.getInt("sid")+"\t"+rs.getString("sname"));
//			//multiple time execution of query
//			pst.setInt(1, 1);
//			rs=pst.executeQuery();
//			if(rs.next())
//				System.out.println(rs.getInt("sid")+"\t"+rs.getString("sname"));
//			
//			rs.close();
//			pst.close();
//			
//			//using pst for insertion
//			String insertPrepareQuery="insert into sample values(?,?)";
//			pst=con.prepareStatement(insertPrepareQuery);
//			pst.setInt(1, 5);
//			pst.setString(2, "Rocky");
//			System.out.println("prepared record inserted :"+pst.executeUpdate());
//			
//			pst.setInt(1, 6);
//			pst.setString(2, "Hero");
//			System.out.println("prepared record inserted :"+pst.executeUpdate());
//			
//			pst.setInt(1, 7);
//			pst.setString(2, "Boss");
//			System.out.println("prepared record inserted :"+pst.executeUpdate());
//			
//			pst.close();
			
			//Batch insertion using pst
//			String[] names=new String[] {
//					"Virat","Rohit","Rahul","Jadeja"
//			};
//			
//			String insertPrepareQuery="insert into sample(sname) values(?)";
//			pst=con.prepareStatement(insertPrepareQuery);
//			for (String name :names) {
//				pst.setString(1,name);
//				pst.addBatch();
//			}
//			System.out.println("Records added...:"+pst.executeBatch().length);
//			pst.close();
//			
//			//to call the stored procedure using Callable statements 
//			String prepareCallQuery="CALL mydb.GetEmpByDeptno(?) ";
//			cst = con.prepareCall(prepareCallQuery);
//			cst.setInt(1, 20);
//			rs = cst.executeQuery();
//			while(rs.next()) {
//				System.out.println("Empno :"+rs.getInt("empno")+", Ename"+rs.getString("ename"));
//				System.out.println(" ,Empsal :"+rs.getInt("sal")+", DLoc"+rs.getString("loc")+"\n");
//				
//			}
			
//			getting resultmetdata
//			String selectQuery2 = "SELECT * FROM Sample";
//			rs = st.executeQuery(selectQuery2);
//			java.sql.ResultSetMetaData rdmd = rs.getMetaData();
//			int coloumn_counter = rdmd.getColumnCount();
//			System.out.println("No of coloumns" + coloumn_counter);
//			for (int i = 1; i <= coloumn_counter; i++) {
//				System.out.println(rdmd.getColumnName(i)+" : "+rdmd.getColumnTypeName(i));
//			}
//			rs.close();
			
//			getting metadata from database
			DatabaseMetaData data = (DatabaseMetaData) con.getMetaData();
			System.out.println("Driver name" + data.getDriverName());
			System.out.println("Driver Version" + data.getDriverVersion());
			System.out.println("User name" + data.getUserName());
			System.out.println("DB Product name" + data.getDatabaseProductName());
			System.out.println("DB PRoduct version name" + data.getDatabaseProductVersion());
			String tables[] = {"tables"};
			rs = data.getTables(con.getCatalog(),null, null, null);
			while(rs.next()) {
				System.out.println(rs.getString(3));
			}
			rs.close();
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