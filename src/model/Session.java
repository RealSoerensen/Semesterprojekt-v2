package model;

import java.sql.Date;

public class Session {
	private long sessionID;
	private Date date;
	private Person instructor;
	private Course course;
	private Address address;
	private Subject subject;

	public Session(long sessionID, Date date, Person instructor, Course course, Address address, Subject subject) {
		this.sessionID = sessionID;
		this.date = date;
		this.instructor = instructor;
		this.course = course;
		this.address = address;
		this.subject = subject;
	}

	public Session(Date date, Person instructor, Course course, Address address, Subject subject) {
		this.date = date;
		this.instructor = instructor;
		this.course = course;
		this.address = address;
		this.subject = subject;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
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

	public long getSessionID() {
		return sessionID;
	}

	public void setSessionID(long sessionID) {
		this.sessionID = sessionID;
	}
}
