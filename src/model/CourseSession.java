package model;

import java.sql.Timestamp;
import java.util.ArrayList;

public class CourseSession {
private Timestamp date;
private Instructor instructor;
private ArrayList<Member> members;
private String address;
private String subject;
private long CourseSessionID;

public CourseSession(Timestamp date, Instructor instructor, String address, String subject) {
	this.date = date;
	this.instructor = instructor;
	this.address = address;
	this.subject = subject;
	members = new ArrayList<>();
	
}
}
