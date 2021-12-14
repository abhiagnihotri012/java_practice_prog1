package com.jdbc;

import java.sql.SQLException;

import Controller.StudentController;

public class StudentNameApp {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		//get student name and marks
		StudentController.getStudents().stream().
		forEach(stu-> System.out.println(stu.getSname()
				+ " "+ stu.getMarks()));
		
		System.out.println("---------");
		//where marks>92
		StudentController.getStudents().stream().
		filter(st-> st.getMarks()>92).forEach(st->
		System.out.println(st.getSname()));

	}

}
