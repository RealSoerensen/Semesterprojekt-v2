package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Session {
	private long sessionID;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private Person instructor;
	private Course course;
	private Address address;
	private Subject subject;

	public Session(long sessionID, LocalDate date, Person instructor, Course course, Address address, Subject subject, LocalTime startTime, LocalTime endTime) {
		this.sessionID = sessionID;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.instructor = instructor;
		this.course = course;
		this.address = address;
		this.subject = subject;
	}

	public Session(LocalDate date, Person instructor, Course course, Address address, Subject subject, LocalTime startTime, LocalTime endTime) {
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.instructor = instructor;
		this.course = course;
		this.address = address;
		this.subject = subject;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
}
