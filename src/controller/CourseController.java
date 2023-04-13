package controller;

import java.sql.SQLException;

import dal.coursesession.CourseSessionContainer;
import dal.course.CourseDataAccessIF;
import dal.coursesession.CourseSessionDataAccessIF;
import dal.coursessessionmember.CourseSessionMemberContainer;
import dal.coursessessionmember.CourseSessionMemberDataAccessIF;
import dal.person.PersonContainer;
import model.*;

public class CourseController {
	private CourseDataAccessIF courseDB;
	private final CourseSessionDataAccessIF courseSessionDB;
	private final CourseSessionMemberDataAccessIF courseSessionMemberDB;
	private final PersonController personController;
	
	public CourseController(CourseDataAccessIF dataAccess) {
		courseDB = dataAccess;
		courseSessionDB = CourseSessionContainer.getInstance();
		courseSessionMemberDB = CourseSessionMemberContainer.getInstance();
		personController = new PersonController(PersonContainer.getInstance());
	}

	private CourseDataAccessIF getCourseDB() {
		return courseDB;
	}

	private void setCourseDB(CourseDataAccessIF courseDB) {
		this.courseDB = courseDB;
	}
	
	public void markAbsent(CourseSession courseSession, long ssn) throws SQLException {
		int role = personController.getRole(ssn);
		if(role == 1) {
			removeMember(ssn);
		}
		else if(role == 2) {
			courseSession.setInstructor(null);
			courseSessionDB.update(courseSession.getCourseSessionID(), courseSession);
		}
	}
	
	private Member getMemberFromCourseSession(long ssn, CourseSession courseSession) {
		Member member = null;

		//TODO: Implement this method
		
		return member;
	}

	private boolean removeMember(long ssn) throws SQLException {
		return courseSessionMemberDB.delete(ssn);
	}
}
