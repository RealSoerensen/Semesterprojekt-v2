package model;

import java.sql.Timestamp;

public class CourseSession {
	private long courseSessionID;
	private Timestamp date;
	private Person instructor;
	private Course course;
	private Address address;
	private Subject subject;

	public CourseSession(long courseSessionID, Timestamp date, Person instructor, Course course, Address address, Subject subject) {
		this.courseSessionID = courseSessionID;
		this.date = date;
		this.instructor = instructor;
		this.course = course;
		this.address = address;
		this.subject = subject;
	}

	public CourseSession(Timestamp date, Person instructor, Course course, Address address, Subject subject) {
		this.date = date;
		this.instructor = instructor;
		this.course = course;
		this.address = address;
		this.subject = subject;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Person getInstructor() {
		return instructor;
	}

	public void setInstructor(Person instructor) {
		this.instructor = instructor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
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
