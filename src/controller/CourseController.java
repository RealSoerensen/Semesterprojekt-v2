package controller;

import java.sql.SQLException;
import java.util.List;

import dal.coursesession.CourseSessionContainer;
import dal.course.CourseDataAccessIF;
import dal.coursesession.CourseSessionDataAccessIF;
import dal.coursessessionmember.CourseSessionMemberContainer;
import dal.coursessessionmember.CourseSessionMemberDataAccessIF;
import model.*;

public class CourseController {
	private CourseDataAccessIF courseDB;
	private final CourseSessionDataAccessIF courseSessionDB;
	private final CourseSessionMemberDataAccessIF courseSessionMemberDB;
	
	public CourseController(CourseDataAccessIF dataAccess) {
		courseDB = dataAccess;
		courseSessionDB = CourseSessionContainer.getInstance();
		courseSessionMemberDB = CourseSessionMemberContainer.getInstance();
	}

	private CourseDataAccessIF getCourseDB() {
		return courseDB;
	}

	private void setCourseDB(CourseDataAccessIF courseDB) {
		this.courseDB = courseDB;
	}
	
	public boolean markMemberAbsent(long ssn, CourseSession courseSession) throws SQLException {
		boolean markedAbsent = false;
		Member member = getMemberFromCourseSession(ssn, courseSession);
		if(member != null) {
			markedAbsent = removeMember(member);
		}
		return markedAbsent;
	}

	public boolean markInstructorAbsent(CourseSession courseSession) throws SQLException {
		courseSession.setInstructor(null);
		return courseSessionDB.update(courseSession.getCourseSessionID(), courseSession);
	}

	private Member getMemberFromCourseSession(long ssn, CourseSession courseSession) throws SQLException {
		Member member = null;
		List<CourseSessionMember> courseMembers = courseSessionMemberDB.getAll();
		for(CourseSessionMember courseMember : courseMembers) {
			long courseMemberSsn = courseMember.getMember().getSsn();
			long courseMemberCourseSessionID = courseMember.getCourseSession().getCourseSessionID();
			if(courseMemberSsn == ssn && courseMemberCourseSessionID == courseSession.getCourseSessionID()) {
				member = courseMember.getMember();
			}
		}
		return member;
	}

	private boolean removeMember(Member member) throws SQLException {
		return courseSessionMemberDB.delete(member.getSsn());
	}
}
