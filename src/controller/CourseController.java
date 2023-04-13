package controller;

import java.sql.SQLException;

import dal.coursesession.CourseSessionDB;
import dal.course.CourseDataAccessIF;
import dal.person.PersonContainer;
import model.*;

public class CourseController {
	private CourseDataAccessIF courseDB;
	private final CourseSessionDB courseSessionDB;
	private PersonController pc;
	
	public CourseController(CourseDataAccessIF dataAccess) throws SQLException {
		setCourseDB(dataAccess);
		courseSessionDB = new CourseSessionDB();
		pc = new PersonController(PersonContainer.getInstance());
	}

	private CourseDataAccessIF getCourseDB() {
		return courseDB;
	}

	public void setCourseDB(CourseDataAccessIF courseDB) {
		this.courseDB = courseDB;
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
