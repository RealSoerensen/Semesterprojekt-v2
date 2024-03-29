package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.address.AddressDB;
import dal.address.AddressDataAccessIF;
import dal.course.CourseDB;
import dal.course.CourseDataAccessIF;
import dal.coursemember.CourseMemberDB;
import dal.coursemember.CourseMemberDataAccessIF;
import dal.instructorsubject.InstructorSubjectDB;
import dal.instructorsubject.InstructorSubjectDataAccessIF;
import dal.session.SessionDB;
import dal.session.SessionDataAccessIF;
import dal.sessionmember.SessionMemberDB;
import dal.sessionmember.SessionMemberDataAccessIF;
import dal.subject.SubjectDB;
import dal.subject.SubjectDataAccessIF;
import model.*;

public class CourseController {
	private CourseDataAccessIF courseDB;
	private CourseMemberDataAccessIF courseMemberDB;
	private SessionDataAccessIF sessionDB;
	private SessionMemberDataAccessIF sessionMemberDB;
	private SubjectDataAccessIF subjectDB;
	private InstructorSubjectDataAccessIF instructorSubjectDB;
	private AddressDataAccessIF addressDB;

	public CourseController() throws SQLException {
		setCourseDB(new CourseDB());
		setCourseMemberDB(new CourseMemberDB());
		setSessionDB(new SessionDB());
		setSessionMemberDB(new SessionMemberDB());
		setSubjetDB(new SubjectDB());
		setInstructorSubjectDB(new InstructorSubjectDB());
		setAddressDB(new AddressDB());
	}

	private void setAddressDB(AddressDB addressDB) {
		this.addressDB = addressDB;
	}

	private AddressDataAccessIF getAddressDB() {
		return addressDB;
	}

	private void setInstructorSubjectDB(InstructorSubjectDB instructorSubjectDB) {
		this.instructorSubjectDB = instructorSubjectDB;
	}

	private InstructorSubjectDataAccessIF getInstructorSubjectDB() {
		return instructorSubjectDB;
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


	public Course createCourse(Course course) throws SQLException {
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
		for (Session session : getAllSessionsFromCourse(course)) {
			getSessionMemberDB().removeAll(session);
			removeSession(session);
		}
		getCourseMemberDB().removeAll(course);
		return getCourseDB().delete(course);
	}

	public boolean createCourseMember(Course course, Person member) {
		return getCourseMemberDB().create(course, member);
	}

	public boolean removeCourseMember(Course course, Person member) throws SQLException {
		boolean result;
		List<Session> sessions = getSessionDB().getAll();
		for (Session currentSession : sessions) {
			if (currentSession.getCourse().getCourseID() == course.getCourseID()) {
				getSessionMemberDB().remove(currentSession, member);
			}
		}
		result = getCourseMemberDB().remove(course, member);
		return result;
	}
	
	public boolean isMemberInCourse(Course course, Person person) {
		return getCourseMemberDB().isPersonIn(course, person);
	}

	public List<Person> getAllCourseMembers(Course course) {
		return getCourseMemberDB().getAll(course);
	}

	public Session createSession(Session session) throws SQLException {
		Address address = getAddressDB().create(session.getAddress());
		session.setAddress(address);
		return getSessionDB().create(session);
	}

	public Session getSession(Course course, long sessionID) throws SQLException {
		Session session = null;
		List<Session> allSessions = getAllSessions();
		for(int i = 0; i < allSessions.size() && session == null; i++) {
			Session currentSession = allSessions.get(i);
			if(currentSession.getSessionID() == sessionID && currentSession.getCourse().getCourseID() == course.getCourseID()) {
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
			if (allSession.getCourse().getCourseID() == course.getCourseID()) {
				sessions.add(allSession);
			}
		}
		return sessions;
	}

	public boolean updateSession(Session session) throws SQLException {
		return getSessionDB().update(session);
	}

	public boolean removeSession(Session session) throws SQLException {
		getSessionMemberDB().removeAll(session);
		return getSessionDB().delete(session);
	}

	public boolean createSessionMember(Session session, Person member) {
		boolean success = false;
		Course course = session.getCourse();
		List<Person> allCourseMembers = getAllCourseMembers(course);
		for (Person currentCourseMember : allCourseMembers) {
			if (currentCourseMember.getSsn() == member.getSsn()) {
				success = getSessionMemberDB().create(session, member);
			}
		}
		return success;
	}

	public List<Person> getAllSessionMembers(Session session) {
		return getSessionMemberDB().getAll(session);
	}

	public boolean removeSessionMember(Session session, Person person) {
		return getSessionMemberDB().remove(session, person);
	}
	
	public boolean isMemberInSession(Session session, Person person) {
		return getSessionMemberDB().isPersonIn(session, person);
	}

	public List<Session> getEnrolledSessions(Person person, Course course) throws SQLException {
		return getSessionDB().getEnrolledSessions(person, course);
	}

	public Subject createSubject(Subject subject) throws SQLException {
		return getSubjectDB().create(subject);
	}

	public List<Subject> getAllSubjects() throws SQLException {
		return getSubjectDB().getAll();
	}

	public void createInstructorSubject(Person instructor, Subject subject) {
		getInstructorSubjectDB().create(instructor, subject);
	}

	public boolean removeInstructorSubject(Person instructor, Subject subject) {
		return getInstructorSubjectDB().remove(instructor, subject);
	}

	public void removeAllCoursesForMember(Person member) throws SQLException {
		List<Course> allCourses = getAllCourses();
		for (Course currentCourse : allCourses) {
			List<Session> allSessions = getEnrolledSessions(member, currentCourse);
			for (Session currentSession : allSessions) {
				removeSessionMember(currentSession, member);
			}
			removeCourseMember(currentCourse, member);
		}
	}

	public int[] StringArrToIntArr(String[] s) {
		int[] result = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			int newInt = Integer.parseInt(s[i]);
			result[i] = newInt;
		}
		return result;
	}

    public void removeAllCoursesForInstructor(Person person) throws SQLException {
		getSessionDB().setSessionInstructorToNull(person);
		getInstructorSubjectDB().removeAllByPerson(person);
    }
}
