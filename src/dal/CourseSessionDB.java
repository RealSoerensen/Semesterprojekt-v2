package dal;

import model.Address;
import model.CourseSession;
import model.Instructor;
import model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseSessionDB implements CRUD<CourseSession>{
    DBConnection dbConnection;
    Connection connection;

    public CourseSessionDB() throws SQLException {
        dbConnection = DBConnection.getInstance();
        connection = dbConnection.getConnection();
    }

    @Override
    public boolean create(CourseSession obj) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(CourseSession obj, long id) throws SQLException {
        String sql = " INSERT INTO courseSession(date, courseID, instructorSsn) " +
                " VALUES(?, ?, ?) ";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setTimestamp(1, obj.getDate());
            stmt.setLong(2, id);
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
                long instructorSsn = courseSessionRS.getLong("instructorSsn");
                Timestamp date = courseSessionRS.getTimestamp("date");
                Instructor instructor = getInstructor(instructorSsn);
                Address address = getAddress(courseSessionRS.getLong("addressID"));
                Subject subject = getSubject(courseSessionRS.getLong("subjectID"));
                courseSession = new CourseSession(date, instructor, address, subject);
            }
        }
        return courseSession;
    }

    @Override
    public List<CourseSession> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(long id, CourseSession obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return false;
    }

    private Address getAddress(long addressId) throws SQLException {
        Address address = null;
        String sql = " SELECT * FROM address WHERE addressID = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, addressId);
            stmt.executeQuery();
            ResultSet addressRS = stmt.getResultSet();
            if (addressRS.next()) {
                address = new Address(
                        addressRS.getString("street"),
                        addressRS.getString("city"),
                        addressRS.getString("state"),
                        addressRS.getString("zipCode")
                );
            }
        }
        return address;
    }


    private Instructor getInstructor(long ssn) throws SQLException {
        Instructor instructor = null;
        String sql = " SELECT * FROM instructor WHERE ssn = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, ssn);
            stmt.executeQuery();
            ResultSet instructorRS = stmt.getResultSet();
            if (instructorRS.next()) {
                Address address = getAddress(instructorRS.getLong("addressID"));
                List<Subject> subjects = getInstructorSubjects(ssn);
                instructor = new Instructor(
                        instructorRS.getString("firstName"),
                        instructorRS.getString("lastName"),
                        address,
                        instructorRS.getString("email"),
                        instructorRS.getString("phoneNo"),
                        instructorRS.getInt("role"),
                        instructorRS.getString("username"),
                        instructorRS.getString("password"),
                        ssn,
                        subjects
                );
            }
        }
        return instructor;
    }

    private List<Subject> getInstructorSubjects(long ssn) throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        String sql = " SELECT * FROM instructorSubjects WHERE ssn = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, ssn);
            ResultSet instructorSubjectRS = stmt.executeQuery();

            while (instructorSubjectRS.next()) {
                long subjectId = instructorSubjectRS.getLong("subjectID");
                Subject subject = getSubject(subjectId);
                subjects.add(subject);
            }
        }

        return subjects;
    }

    private Subject getSubject(long subjectID) throws SQLException {
        Subject subject = null;
        String sql = "SELECT * FROM Subject WHERE subjectID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, subjectID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String subjectName = rs.getString("subject");
                    String description = rs.getString("description");
                    subject = new Subject(subjectName);
                    subject.setDescription(description);
                }
            }
        }
        return subject;
    }

}
