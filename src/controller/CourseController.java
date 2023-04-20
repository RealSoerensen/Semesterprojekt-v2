package controller;

import java.sql.SQLException;
import java.util.List;

import dal.session.SessionContainer;
import dal.course.CourseDataAccessIF;
import dal.session.SessionDataAccessIF;
import dal.sessionmember.SessionMemberContainer;
import dal.sessionmember.SessionMemberDataAccessIF;
import model.*;

public class CourseController {
	private CourseDataAccessIF courseDB;
	private final SessionDataAccessIF sessionDB;
	private final SessionMemberDataAccessIF sessionMemberDB;
	
	public CourseController(CourseDataAccessIF dataAccess) {
		courseDB = dataAccess;
		sessionDB = SessionContainer.getInstance();
		sessionMemberDB = SessionMemberContainer.getInstance();
	}

	private CourseDataAccessIF getCourseDB() {
		return courseDB;
	}

	private void setCourseDB(CourseDataAccessIF courseDB) {
		this.courseDB = courseDB;
	}

	public boolean markInstructorAbsent(Session session) throws SQLException {
		session.setInstructor(null);
		return sessionDB.update(session.getSessionID(), session);
	}

	public boolean markMemberAbsent(long ssn, Session courseSession) throws SQLException {
		boolean markedAbsent = false;
		Person member = getMemberFromSession(ssn, courseSession);
		if(member != null) {
			markedAbsent = removeMember(member);
		}
		return markedAbsent;
	}

	private Person getMemberFromSession(long ssn, Session courseSession) throws SQLException {
		Person member = null;
		List<SessionMember> courseMembers = sessionMemberDB.getAll();
		for(int i = 0; i < courseMembers.size() && member == null; i++) {
			SessionMember courseMember = courseMembers.get(i);
			long courseMemberSsn = courseMember.getPerson().getSsn();
			long courseMemberCourseSessionID = courseMember.getCourseSession().getSessionID();
			if(courseMemberSsn == ssn && courseMemberCourseSessionID == courseSession.getSessionID()) {
				member = courseMember.getPerson();
			}
		}
		return member;
	}

	private boolean removeMember(Person member) throws SQLException {
		return sessionMemberDB.delete(member.getSsn());
	}
}
