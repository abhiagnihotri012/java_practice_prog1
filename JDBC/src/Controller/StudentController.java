package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Student;

public class StudentController {
	public static List<Student> getStudents() throws SQLException{
		List<Student> students = new ArrayList<Student>();
		Connection con = JDBCProperties.getConnection();
		try {
			String query  = "Select * From mydb.student";
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rst = pst.executeQuery();
			while(rst.next()) {
				students.add(new Student(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			con.close();
		}
		return students;
	}

}
