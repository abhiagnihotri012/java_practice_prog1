package com.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

public class DDLDML {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
		final String DB_USER = "root";
		final String DB_PASSWORD = "rohan";

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		CallableStatement cst = null;

		try {
			// register the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded!!!");

			// creating a connection
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("Connection established!!");

			// use statement object to perform queries
			st = con.createStatement();
//			String createTableQuery ="Create Table student_info"
//					+"(rno int AUTO_INCREMENT primary key,name varchar(50) not null,city varchar(50) not null )";
//			System.out.println("Table Creation: "+st.executeUpdate(createTableQuery));

//			2,'',''
//			3,'',''

//			String insertPrepareQuery="insert into student_info(name,city) values(?,?)";
//			pst=con.prepareStatement(insertPrepareQuery);
//			pst.setString(1, "gautam");
//			pst.setString(2, "jalgaon");
//			System.out.println("prepared record inserted :"+pst.executeUpdate());

//			String[] names=new String[] {
//					"gautam","balaji","rushin"
//			};
//			String[] city=new String[] {
//					"jalgaon","parbhani","jalgaon"
//			};

//			String insertPrepareQuery="insert into student_info(name,city) values(?,?)";
//			pst=con.prepareStatement(insertPrepareQuery);
//			for (String name :names) {
//				for (String cit : city) {
//					pst.setString(1,name);
//					pst.setString(2,cit);
//					pst.addBatch();
//					
//				}
//			}
//			System.out.println("Records added...:"+pst.executeBatch().length);
//			pst.close();

//			String createTableQuery = "Create Table marks"
//					+ "(rno int not null,subject_code decimal(4,0) NOT NULL,marks decimal(4,0) default null)";
//			System.out.println("Table Creation: " + st.executeUpdate(createTableQuery));

//			String insertPrepareQuery = "insert into marks(rno,subject_code,marks) values(?,?,?)";
//			pst = con.prepareStatement(insertPrepareQuery);
//			pst.setInt(1, 1);
//			pst.setInt(2, 100);
//			pst.setInt(3, 80);
//			System.out.println("prepared record inserted :"+pst.executeUpdate());

//			pst.setInt(1, 2);
//			pst.setInt(2, 100);
//			pst.setNull(3,Types.NULL);
//			System.out.println("prepared record inserted :"+pst.executeUpdate());

//			pst.setInt(1, 1);
//			pst.setInt(2, 101);
//			pst.setInt(3,90);
//			System.out.println("prepared record inserted :"+pst.executeUpdate());
//			
//			pst.setInt(1, 2);
//			pst.setInt(2, 101);
//			pst.setInt(3,78);
//			System.out.println("prepared record inserted :"+pst.executeUpdate());
//			
//			pst.setInt(1, 3);
//			pst.setInt(2, 100);
//			pst.setInt(3,30);
//			System.out.println("prepared record inserted :"+pst.executeUpdate());
//			
//			pst.setInt(1, 3);
//			pst.setInt(2, 101);
//			pst.setNull(3,Types.NULL);
//			System.out.println("prepared record inserted :"+pst.executeUpdate());

//			String createTableQuery = "Create Table subject"
//					+ "(subject_code decimal(4,0) NOT NULL,subject_name varchar(50) not null)";
//			System.out.println("Table Creation: " + st.executeUpdate(createTableQuery));

//			String insertPrepareQuery="insert into subject(subject_code,subject_name) values(101,\"se\")";
//			System.out.println("Record Inserted :"+st.executeUpdate(insertPrepareQuery));

//			String updateQuery="UPDATE student_info set city='parbhani' where rno=2";
//			System.out.println("Record Updated :"+st.executeUpdate(updateQuery));
			System.out.println("Find out average marks of each student along with roll number of student");
			String selectQuery = "select m.rno,avg(m.marks)as avg from  marks m inner join student_info s using(rno) group by m.rno";
			rs = st.executeQuery(selectQuery);
			while (rs.next())
				System.out.println(rs.getInt("rno") + "\t" + rs.getInt("avg"));
			rs.close();
			System.out.println(" ");

			System.out.println("Find out how many students have failed i.e. obtained less than 40 marks in DBMS");
			String selectQuery2 = "select count(*)as count from  marks m inner join subject s  using(subject_code) where m.subject_code =(select subject_code from subject where subject_name = \"dbms\") and m.marks<40";
			rs = st.executeQuery(selectQuery2);
			while (rs.next())
				System.out.println("Students failed in DBMS: "+rs.getInt("count"));
			rs.close();
			System.out.println(" ");
			
			System.out.println("Find names of Students who are absent in Exam i.e. students having null marks");
			String selectQuery3 = "select s.name,m.marks from  marks m inner join student_info s using(rno) where m.marks is null;";
			rs = st.executeQuery(selectQuery3);
			while (rs.next())
				System.out.println(rs.getString("name") + "\t" + rs.getInt("marks"));
			rs.close();
			System.out.println(" ");
			
			System.out.println("Find the name of students who live in either ‘pune’ or 'jalgaon");
			String selectQuery4 = "select name,city from student_info where city = \"jalgaon\" or \"pune\";";
			rs = st.executeQuery(selectQuery4);
			while (rs.next())
				System.out.println(rs.getString("name") + "\t" + rs.getString("city"));
			rs.close();
			System.out.println(" ");
			
			System.out.println("Find out roll no and total marks of each student");
			String selectQuery5 = "select rno,sum(marks)as sum from marks group by rno;";
			rs = st.executeQuery(selectQuery5);
			while (rs.next())
				System.out.println(rs.getInt("rno") + "\t" + rs.getInt("sum"));
			rs.close();
			System.out.println(" ");
			
			System.out.println("Find those student names which start with 'ba'.");
			String selectQuery6 = "select name from student_info where name Like 'ba%';";
			rs = st.executeQuery(selectQuery6);
			while (rs.next())
				System.out.println(rs.getString("name"));
			rs.close();
			System.out.println(" ");
			
			System.out.println("Display total no of students having greater than 75 marks in any one subject.");
			String selectQuery7 = "select count(*) as count from (select rno from marks where marks >75 group by rno) x;";
			rs = st.executeQuery(selectQuery7);
			while (rs.next())
				System.out.println("no of students having greater than 75 marks in any one subject. "+rs.getInt("count"));
			rs.close();
			System.out.println(" ");
			
			System.out.println("Display marks of all students for DBMS subject.");
			String selectQuery8 = "select rno,marks from marks where subject_code= (select subject_code from subject where subject_name = \"dbms\");";
			rs = st.executeQuery(selectQuery8);
			while (rs.next())
				System.out.println(rs.getInt("rno")+"\t"+rs.getInt("marks"));
			rs.close();
			System.out.println(" ");
			
			System.out.println("Display no of students appeared for the exam.");
			String selectQuery9 = "select count(*) as count from (select rno from marks where marks is not null group by rno) x;";
			rs = st.executeQuery(selectQuery9);
			while (rs.next())
				System.out.println("no of students appeared for the exam "+rs.getInt("count"));
			rs.close();
			System.out.println(" ");
			
			System.out.println("Display all subjects marks of students whose name is gautam");
			String selectQuery10 = "select s.name , m.subject_code ,m.marks from marks m inner join student_info s using (rno) where name = \"gautam\" ;";
			rs = st.executeQuery(selectQuery10);
			while (rs.next())
				System.out.println(rs.getString("name")+"\t"+rs.getInt("subject_code")+"\t"+rs.getInt("marks"));
			rs.close();
			System.out.println(" ");
			
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cst != null)
					cst.close();
				if (pst != null)
					pst.close();
				if (st != null)
					st.close();
				if (rs != null)
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