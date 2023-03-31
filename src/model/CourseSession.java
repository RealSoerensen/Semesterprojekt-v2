package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CourseSession {
	private Timestamp date;
	private Instructor instructor;
	private List<Member> members;
	private String address;
	private String subject;
	private long courseSessionID;

	public CourseSession(Timestamp date, Instructor instructor, String address, String subject) {
		this.date = date;
		this.instructor = instructor;
		this.address = address;
		this.subject = subject;
		members = new ArrayList<>();
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<Member> members) {
		this.members = members;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public long getCourseSessionID() {
		return courseSessionID;
	}

	public void setCourseSessionID(long courseSessionID) {
		this.courseSessionID = courseSessionID;
	}
}
