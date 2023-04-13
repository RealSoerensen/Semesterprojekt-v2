package dal.coursesession;

import dal.DBConnection;
import dal.address.AddressDB;
import dal.course.CourseDB;
import dal.instructor.InstructorDB;
import dal.subject.SubjectDB;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseSessionDB implements CourseSessionDataAccessIF {
    DBConnection dbConnection;
    Connection connection;

    public CourseSessionDB() throws SQLException {
        dbConnection = DBConnection.getInstance();
        connection = dbConnection.getConnection();
    }

    @Override
    public boolean create(CourseSession obj) throws SQLException {
        String sql = " INSERT INTO courseSession(date, courseID, instructorSsn) " +
                " VALUES(?, ?, ?) ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, obj.getDate());
            stmt.setLong(2, obj.getCourse().getCourseID());
            stmt.setLong(3, obj.getInstructor().getSsn());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public CourseSession get(long id) throws SQLException {
        CourseSession courseSession = null;
        String sql = " SELECT * FROM courseSession WHERE courseSessionID = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeQuery();
            ResultSet courseSessionRS = stmt.getResultSet();
            if (courseSessionRS.next()) {
                courseSession = createCourseSession(courseSessionRS);
            }
        }
        return courseSession;
    }

    @Override
    public List<CourseSession> getAll() throws SQLException {
        List<CourseSession> courseSessions = new ArrayList<>();
        String sql = " SELECT * FROM courseSession ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeQuery();
            ResultSet courseSessionRS = stmt.getResultSet();
            while (courseSessionRS.next()) {
                CourseSession courseSession = createCourseSession(courseSessionRS);
                courseSessions.add(courseSession);
            }
        }
        return courseSessions;
    }

    @Override
    public boolean update(long id, CourseSession obj) throws SQLException {
        String sql = " UPDATE courseSession SET date = ?, courseID = ?, instructorSsn = ? " +
                " WHERE courseSessionID = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, obj.getDate());
            stmt.setLong(2, id);
            stmt.setLong(3, obj.getInstructor().getSsn());
            stmt.setLong(4, id);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(long id) throws SQLException {
        String sql = " DELETE FROM courseSession WHERE courseSessionID = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
    
    public boolean removeMember(long courseSessionID, long ssn) throws SQLException {
    	String sql = " DELETE FROM courseSessionMembers WHERE courseSessionID = ? AND ssn = ? ";
    	try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, courseSessionID);
            stmt.setLong(2, ssn);
            return stmt.executeUpdate() > 0;
        }
    }

    private CourseSession createCourseSession(ResultSet rs) throws SQLException {
        long instructorSsn = rs.getLong("instructorSsn");
        Timestamp date = rs.getTimestamp("date");
        Instructor instructor = getInstructor(instructorSsn);
        Course course = getCourse(rs.getLong("courseID"));
        Address address = getAddress(rs.getLong("addressID"));
        Subject subject = getSubject(rs.getLong("subjectID"));
        long id = rs.getLong("courseSessionID");
        return new CourseSession(date, instructor, course, address, subject, id);
    }

    private Address getAddress(long addressId) throws SQLException {
        AddressDB addressDB = new AddressDB();
        return addressDB.get(addressId);
    }


    private Instructor getInstructor(long ssn) throws SQLException {
        InstructorDB instructorDB = new InstructorDB();
        return instructorDB.get(ssn);
    }

    private Subject getSubject(long subjectID) throws SQLException {
        SubjectDB subjectDB = new SubjectDB();
        return subjectDB.get(subjectID);
    }

    private Course getCourse(long courseID) throws SQLException {
        CourseDB courseDB = new CourseDB();
        return courseDB.get(courseID);
    }
}
