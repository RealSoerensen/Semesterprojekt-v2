package controller;

import java.sql.SQLException;

import dal.*;
import model.*;

public class CourseController {
	private CourseDB courseDB;
	private CourseSessionDB courseSessionDB;
	private PersonController pc;
	
	public CourseController() throws SQLException {
		courseDB = new CourseDB();
		courseSessionDB = new CourseSessionDB();
		pc = new PersonController();
	}
	
	public void markAbsent(long courseSessionID, long ssn) throws SQLException {
		CourseSession courseSession = courseSessionDB.get(courseSessionID);
		Person personToBeMarkedAbsent = null;
		
		int role = pc.getRoleOfPerson(ssn);
		if(role == 1) {
			personToBeMarkedAbsent = getMemberFromCourseSession(ssn, courseSession);
		}
		else if(role == 2) {
			personToBeMarkedAbsent = courseSession.getInstructor();
		}
		
		//TODO Fjerne en person fra courseSession baseret på courseSessionID og ssn
	}
	
	private Member getMemberFromCourseSession(long ssn, CourseSession courseSession) {
		Member member = null;

		int amountOfMembers = courseSession.getMembers().size();
		boolean found = false;
		while(amountOfMembers > 0 && !found) {
			if(ssn == courseSession.getMembers().get(amountOfMembers).getSsn()) {
				member = courseSession.getMembers().get(amountOfMembers);
				found = true;
			}
			amountOfMembers--;
		}
		
		return member;
	}
}
