package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CourseSession {
	private Timestamp date;
	private Instructor instructor;
	private Course course;
	private Address address;
	private Subject subject;
	private List<Member> members;
	private long courseSessionID;


	public CourseSession(Timestamp date, Instructor instructor, Course course, Address address, Subject subject, long courseSessionID) {
		this.date = date;
		this.instructor = instructor;
		this.course = course;
		this.address = address;
		this.subject = subject;
		this.courseSessionID = courseSessionID;
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public long getCourseSessionID() {
		return courseSessionID;
	}

	public void setCourseSessionID(long courseSessionID) {
		this.courseSessionID = courseSessionID;
	}
}
