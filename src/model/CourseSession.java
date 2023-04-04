package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CourseSession {
	private Timestamp date;
	private Instructor instructor;
	private Address address;
	private Subject subject;
	private List<Member> members;


	public CourseSession(Timestamp date, Instructor instructor, Address address, Subject subject) {
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
}
