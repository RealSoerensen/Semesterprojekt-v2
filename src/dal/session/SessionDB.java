package dal.session;

import dal.DBConnection;
import dal.address.AddressDB;
import dal.address.AddressDataAccessIF;
import dal.course.CourseDB;
import dal.course.CourseDataAccessIF;
import dal.person.PersonDB;
import dal.person.PersonDataAccessIF;
import dal.subject.SubjectDB;
import dal.subject.SubjectDataAccessIF;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionDB implements SessionDataAccessIF {
    Connection connection;

    /**
     * Constructor for SessionDB class.
     */
    public SessionDB() throws SQLException {
        connection = DBConnection.getInstance().getConnection();
    }

    /**
     * Creates a new Session in the database.
     *
     * @param obj The Session to be created.
     * @return True if the Session was created successfully, false otherwise.
     */
    @Override
    public boolean create(Session obj) {
        boolean result = false;
        String sql = " INSERT INTO Session(date, courseID, instructorSsn) VALUES(?, ?, ?) ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, obj.getDate());
            stmt.setLong(2, obj.getCourse().getCourseID());
            stmt.setLong(3, obj.getInstructor().getSsn());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Gets a Session from the database.
     *
     * @param id The id of the Session to be retrieved.
     * @return The Session with the given id.
     */
    @Override
    public Session get(long id) throws SQLException {
        Session session = null;
        String sql = " SELECT * FROM Session WHERE sessionID = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeQuery();
            ResultSet sessionRS = stmt.getResultSet();
            if (sessionRS.next()) {
                session = createSession(sessionRS);
            }
        }
        return session;
    }

    /**
     * Get all Session from the database.
     * @return A list of all Session.
     */
    @Override
    public List<Session> getAll() throws SQLException {
        List<Session> sessions = new ArrayList<>();
        String sql = " SELECT * FROM Session ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeQuery();
            ResultSet sessionRS = stmt.getResultSet();
            while (sessionRS.next()) {
                Session courseSession = createSession(sessionRS);
                sessions.add(courseSession);
            }
        }
        return sessions;
    }

    /**
     * Updates a Session in the database.
     * @param obj The updated Session.
     * @return True if the Session was updated successfully, false otherwise.
     */
    @Override
    public boolean update(Session obj) {
        boolean result = false;
        String sql = " UPDATE Session SET date = ?, courseID = ?, instructorSsn = ? " +
                " WHERE sessionID = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, obj.getDate());
            stmt.setLong(2, obj.getCourse().getCourseID());
            stmt.setLong(3, obj.getInstructor().getSsn());
            stmt.setLong(4, obj.getSessionID());
            result = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Deletes a Session from the database.
     * @param obj The Session to be deleted.
     * @return True if the Session was deleted successfully, false otherwise.
     */
    @Override
    public boolean delete(Session obj) throws SQLException {
        boolean result;
        String sql = " DELETE FROM Session WHERE sessionID = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, obj.getSessionID());
            result = stmt.executeUpdate() > 0;
        }
        return result;
    }

    /**
     * Creates a Session object from a ResultSet.
     * @param rs The ResultSet to be converted.
     * @return The Session object.
     */
    private Session createSession(ResultSet rs) throws SQLException {
        Date date = rs.getDate("date");
        Person instructor = getInstructor(rs.getLong("instructorSsn"));
        Course course = getCourse(rs.getLong("courseID"));
        Address address = getAddress(rs.getLong("addressID"));
        Subject subject = getSubject(rs.getLong("subjectID"));
        long id = rs.getLong("sessionID");
        return new Session(id, date, instructor, course, address, subject);
    }

    /**
     * Gets an Address from the database.
     * @param addressId The id of the Address to be retrieved.
     * @return The Address with the given id.
     */
    private Address getAddress(long addressId) throws SQLException {
        AddressDataAccessIF addressDB = new AddressDB();
        return addressDB.get(addressId);
    }

    /**
     * Gets an Instructor from the database.
     * @param ssn The ssn of the Instructor to be retrieved.
     * @return The Instructor with the given ssn.
     */
    private Person getInstructor(long ssn) throws SQLException {
        PersonDataAccessIF instructorDB = new PersonDB();
        return instructorDB.get(ssn);
    }

    /**
     * Gets a Subject from the database.
     * @param subjectID The id of the Subject to be retrieved.
     * @return The Subject with the given id.
     */
    private Subject getSubject(long subjectID) throws SQLException {
        SubjectDataAccessIF subjectDB = new SubjectDB();
        return subjectDB.get(subjectID);
    }

    /**
     * Gets a Course from the database.
     * @param courseID The id of the Course to be retrieved.
     * @return The Course with the given id.
     */
    private Course getCourse(long courseID) throws SQLException {
        CourseDataAccessIF courseDB = new CourseDB();
        return courseDB.get(courseID);
    }

    @Override
    public void deleteAll() {
        String sql = " DELETE * FROM Session ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
