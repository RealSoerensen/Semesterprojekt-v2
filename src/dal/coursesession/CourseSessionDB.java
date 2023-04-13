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

    /**
     * Constructor for CourseSessionDB class.
     */
    public CourseSessionDB() throws SQLException {
        dbConnection = DBConnection.getInstance();
        connection = dbConnection.getConnection();
    }

    /**
     * Creates a new CourseSession in the database.
     *
     * @param obj The CourseSession to be created.
     * @return True if the CourseSession was created successfully, false otherwise.
     */
    @Override
    public boolean create(CourseSession obj) {
        boolean result = false;
        String sql = " INSERT INTO courseSession(date, courseID, instructorSsn) " +
                " VALUES(?, ?, ?) ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, obj.getDate());
            stmt.setLong(2, obj.getCourse().getCourseID());
            stmt.setLong(3, obj.getInstructor().getSsn());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Gets a CourseSession from the database.
     *
     * @param id The id of the CourseSession to be retrieved.
     * @return The CourseSession with the given id.
     */
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

    /**
     * Get all CourseSessions from the database.
     * @return A list of all CourseSessions.
     */
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

    /**
     * Updates a CourseSession in the database.
     * @param id The id of the CourseSession to be updated.
     * @param obj The updated CourseSession.
     * @return True if the CourseSession was updated successfully, false otherwise.
     */
    @Override
    public boolean update(long id, CourseSession obj) {
        boolean result = false;
        String sql = " UPDATE courseSession SET date = ?, courseID = ?, instructorSsn = ? " +
                " WHERE courseSessionID = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, obj.getDate());
            stmt.setLong(2, id);
            stmt.setLong(3, obj.getInstructor().getSsn());
            stmt.setLong(4, id);
            result = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Deletes a CourseSession from the database.
     * @param id The id of the CourseSession to be deleted.
     * @return True if the CourseSession was deleted successfully, false otherwise.
     */
    @Override
    public boolean delete(long id) throws SQLException {
        boolean result;
        String sql = " DELETE FROM courseSession WHERE courseSessionID = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            result = stmt.executeUpdate() > 0;
        }
        return result;
    }

    /**
     * Creates a CourseSession object from a ResultSet.
     * @param rs The ResultSet to be converted.
     * @return The CourseSession object.
     */
    private CourseSession createCourseSession(ResultSet rs) throws SQLException {
        long instructorSsn = rs.getLong("instructorSsn");
        Timestamp date = rs.getTimestamp("date");
        Instructor instructor = getInstructor(instructorSsn);
        Course course = getCourse(rs.getLong("courseID"));
        Address address = getAddress(rs.getLong("addressID"));
        Subject subject = getSubject(rs.getLong("subjectID"));
        long id = rs.getLong("courseSessionID");
        return new CourseSession(id, date, instructor, course, address, subject);
    }

    /**
     * Gets an Address from the database.
     * @param addressId The id of the Address to be retrieved.
     * @return The Address with the given id.
     */
    private Address getAddress(long addressId) throws SQLException {
        AddressDB addressDB = new AddressDB();
        return addressDB.get(addressId);
    }

    /**
     * Gets an Instructor from the database.
     * @param ssn The ssn of the Instructor to be retrieved.
     * @return The Instructor with the given ssn.
     */
    private Instructor getInstructor(long ssn) throws SQLException {
        InstructorDB instructorDB = new InstructorDB();
        return instructorDB.get(ssn);
    }

    /**
     * Gets a Subject from the database.
     * @param subjectID The id of the Subject to be retrieved.
     * @return The Subject with the given id.
     */
    private Subject getSubject(long subjectID) throws SQLException {
        SubjectDB subjectDB = new SubjectDB();
        return subjectDB.get(subjectID);
    }

    /**
     * Gets a Course from the database.
     * @param courseID The id of the Course to be retrieved.
     * @return The Course with the given id.
     */
    private Course getCourse(long courseID) throws SQLException {
        CourseDB courseDB = new CourseDB();
        return courseDB.get(courseID);
    }
}
