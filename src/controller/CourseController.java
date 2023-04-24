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

	public boolean markMemberAbsent(long ssn, Session session) throws SQLException {
		boolean markedAbsent = false;
		Person member = getMemberFromSession(ssn, session);
		if(member != null) {
			markedAbsent = removeMember(member);
		}
		return markedAbsent;
	}

	private Person getMemberFromSession(long ssn, Session session) throws SQLException {
		Person member = null;
		List<SessionMember> sessionMembers = sessionMemberDB.getAll();
		for(int i = 0; i < sessionMembers.size() && member == null; i++) {
			SessionMember sessionMember = sessionMembers.get(i);
			long sessionMemberSsn = sessionMember.getPerson().getSsn();
			long memberSessionID = sessionMember.getSession().getSessionID();
			if(sessionMemberSsn == ssn && memberSessionID == session.getSessionID()) {
				member = sessionMember.getPerson();
			}
		}
		return member;
	}

	private boolean removeMember(Person member) throws SQLException {
		return sessionMemberDB.delete(member.getSsn());
	}
}
