package controller;

import java.sql.SQLException;
import java.util.List;

import dal.course.CourseDataAccessIF;
import dal.coursemember.CourseMemberDataAccessIF;
import dal.session.SessionDataAccessIF;
import dal.sessionmember.SessionMemberDB;
import dal.sessionmember.SessionMemberDataAccessIF;
import model.*;

public class CourseController {
	private CourseDataAccessIF courseDB;
	private CourseMemberDataAccessIF courseMemberDB;
	private SessionDataAccessIF sessionDB;
	private SessionMemberDataAccessIF sessionMemberDB;

	public CourseController(CourseDataAccessIF courseDataAccess, CourseMemberDataAccessIF courseMemberDataAccess, SessionDataAccessIF sessionDataAccess, SessionMemberDataAccessIF sessionMemberDataAccess) {
		setCourseDB(courseDataAccess);
		setCourseMemberDB(courseMemberDataAccess);
		setSessionDB(sessionDataAccess);
		setSessionMemberDB(sessionMemberDataAccess);
	}

	private CourseDataAccessIF getCourseDB() {
		return courseDB;
	}

	private void setCourseDB(CourseDataAccessIF courseDB) {
		this.courseDB = courseDB;
	}

	private CourseMemberDataAccessIF getCourseMemberDB() {
		return courseMemberDB;
	}

	private void setCourseMemberDB(CourseMemberDataAccessIF courseMemberDB) {
		this.courseMemberDB = courseMemberDB;
	}

	private SessionDataAccessIF getSessionDB(){
		return sessionDB;
	}

	private void setSessionDB(SessionDataAccessIF sessionDB) {
		this.sessionDB = sessionDB;
	}

	private SessionMemberDataAccessIF getSessionMemberDB() {
		return sessionMemberDB;
	}

	private void setSessionMemberDB(SessionMemberDataAccessIF sessionMemberDB) {
		this.sessionMemberDB = sessionMemberDB;
	}

	public boolean createCourse(Course course) throws SQLException {
		return getCourseDB().create(course);
	}

	public Course getCourse(long courseID) throws SQLException {
		return getCourseDB().get(courseID);
	}

	public List<Course> getAllCourses() throws SQLException {
		return getCourseDB().getAll();
	}

	public boolean updateCourse(Course course) throws SQLException {
		return getCourseDB().update(course);
	}

	public boolean removeCourse(Course course) throws SQLException {
		return getCourseDB().delete(course);
	}

	public boolean createCourseMember(Course course, Person member) {
		return getCourseMemberDB().create(course, member);
	}

	public boolean removeCourseMember(Course course, Person member) {
		return getCourseMemberDB().remove(course, member);
	}

	public List<Person> getAllCourseMembers(Course course) {
		return getCourseMemberDB().getAll(course);
	}

	public boolean createSession(Session session) throws SQLException {
		return getSessionDB().create(session);
	}

	public Session getSession(Course course, long sessionID) throws SQLException {
		Session session = null;
		List<Session> allSessions = getAllSessions();
		for(int i = 0; i < allSessions.size() && session == null; i++) {
			Session currentSession = allSessions.get(i);
			if(currentSession.getSessionID() == sessionID && currentSession.getCourse().equals(course)) {
				session = allSessions.get(i);
			}
		}
		return session;
	}

	public List<Session> getAllSessions() throws SQLException {
		return getSessionDB().getAll();
	}

	public List<Session> getAllSessionsFromCourse(Course course) throws SQLException {
		List<Session> allSessions = getAllSessions();
		for(int i = 0; i < allSessions.size(); i++) {
			if(!allSessions.get(i).getCourse().equals(course)) {
				allSessions.remove(i);
				i--;
			}
		}
		return allSessions;
	}

	public boolean updateSession(Session session) throws SQLException {
		return getSessionDB().update(session);
	}

	public boolean removeSession(Session session) throws SQLException {
		return getSessionDB().delete(session);
	}

	public boolean createSessionMember(Session session, Person member) {
		return getSessionMemberDB().create(session, member);
	}

	public List<Person> getAllSessionMembers(Session session) {
		return getSessionMemberDB().getAll(session);
	}

	public boolean removeSessionMember(Session session, Person person) {
		return getSessionMemberDB().remove(session, person);
	}

	public void deleteAllCourses() throws SQLException {
		List<Course> allCourses = getAllCourses();
		while(!allCourses.isEmpty()) {
			removeCourse(allCourses.get(0));
			allCourses = getAllCourses();
		}
	}

	public void deleteAllSessions() throws SQLException {
		List<Session> allSessions = getAllSessions();
		while(!allSessions.isEmpty()) {
			removeSession(allSessions.get(0));
			allSessions = getAllSessions();
		}
	}
}
