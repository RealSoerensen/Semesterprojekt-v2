package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.course.CourseContainer;
import dal.course.CourseDataAccessIF;
import dal.coursemember.CourseMemberContainer;
import dal.coursemember.CourseMemberDataAccessIF;
import dal.session.SessionContainer;
import dal.session.SessionDataAccessIF;
import dal.sessionmember.SessionMemberContainer;
import dal.sessionmember.SessionMemberDataAccessIF;
import dal.subject.SubjectContainer;
import dal.subject.SubjectDataAccessIF;
import model.*;

public class CourseController {
	private CourseDataAccessIF courseDB;
	private CourseMemberDataAccessIF courseMemberDB;
	private SessionDataAccessIF sessionDB;
	private SessionMemberDataAccessIF sessionMemberDB;
	private SubjectDataAccessIF subjectDB;

	public CourseController() {
		setCourseDB(CourseContainer.getInstance());
		setCourseMemberDB(CourseMemberContainer.getInstance());
		setSessionDB(SessionContainer.getInstance());
		setSessionMemberDB(SessionMemberContainer.getInstance());
		setSubjetDB(SubjectContainer.getInstance());
	}

	private void setSubjetDB(SubjectDataAccessIF subjectDB) {
		this.subjectDB = subjectDB;
	}

	private SubjectDataAccessIF getSubjectDB() {
		return subjectDB;
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
		List<Session> sessions = new ArrayList<>();
		List<Session> allSessions = getAllSessions();
		for (Session allSession : allSessions) {
			if (allSession.getCourse().equals(course)) {
				sessions.add(allSession);
			}
		}
		return sessions;
	}

	public boolean updateSession(Session session) throws SQLException {
		return getSessionDB().update(session);
	}

	public boolean removeSession(Session session) throws SQLException {
		return getSessionDB().delete(session);
	}

	public boolean createSessionMember(Session session, Person member) {
		Course course = session.getCourse();
		List<Person> personList = getAllCourseMembers(course);
		boolean isMember = personList.contains(member);
		if (isMember) {
			return getSessionMemberDB().create(session, member);
		} else {
			return false;
		}

	}

	public List<Person> getAllSessionMembers(Session session) {
		return getSessionMemberDB().getAll(session);
	}

	public boolean removeSessionMember(Session session, Person person) {
		return getSessionMemberDB().remove(session, person);
	}

	public List<Session> getEnrolledSessions(Person person, Course course) throws SQLException {
		List<Session> enrolledSessions = new ArrayList<>();
		List<Session> allSessions = getAllSessionsFromCourse(course);
		for (Session currentSession : allSessions) {
			List<Person> allSessionMembers = getAllSessionMembers(currentSession);
			for (Person currentSessionMember : allSessionMembers) {
				if (currentSessionMember.equals(person)) {
					enrolledSessions.add(currentSession);
				}
			}
		}
		return enrolledSessions;
	}

	public void deleteAllCourses() {
		getCourseDB().deleteAll();
	}

	public void deleteAllSessions() {
		getSessionDB().deleteAll();
	}

	public boolean createSubject(Subject subject) throws SQLException {
		return getSubjectDB().create(subject);
	}

	public Subject getSubject(long subjectID) throws SQLException {
		return getSubjectDB().get(subjectID);
	}

	public boolean updateSubject(Subject subject) throws SQLException {
		return getSubjectDB().update(subject);
	}

	public boolean removeSubject(Subject subject) throws SQLException {
		return getSubjectDB().delete(subject);
	}

	public List<Subject> getAllSubjects() throws SQLException {
		return getSubjectDB().getAll();
	}

	public int[] StringArrToIntArr(String[] s) {
		int[] result = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			int newInt = Integer.parseInt(s[i]);
			if(i == 0){
				newInt -= 1900;
			} else if(i == 1){
				newInt--;
			}
			result[i] = newInt;
		}
		return result;
	}
}
