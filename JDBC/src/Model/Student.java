package Model;

import java.io.Serializable;

public class Student   {
	private static final long serialVersionUID = 1L;
	private String sname;
	private int sid;
	private String course;
	private int marks;

	Student() {

	}

	public Student(int sid ,String sname, String course, int marks) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.course = course;
		this.marks = marks;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Student [sname=" + sname + ", sid=" + sid + ", course=" + course + ", marks=" + marks + "]";
	}

}
