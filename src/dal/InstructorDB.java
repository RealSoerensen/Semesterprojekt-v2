package dal;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorDB implements CRUD<Instructor>{
    private final Connection connection;

    public InstructorDB() throws SQLException {
        DBConnection dbConnection = DBConnection.getInstance();
        connection = dbConnection.getConnection();
    }

    @Override
    public boolean create(Instructor obj) throws SQLException {
        return false;
    }

    @Override
    public boolean create(CourseSession obj, long id) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Instructor get(long id) throws SQLException {
        Instructor instructor = null;
        String sql = " SELECT * FROM instructor WHERE ssn = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeQuery();
            ResultSet instructorRS = stmt.getResultSet();
            if (instructorRS.next()) {
                Address address = getAddress(instructorRS.getLong("addressID"));
                List<Subject> subjects = getInstructorSubjects(id);
                instructor = new Instructor(
                        instructorRS.getString("firstName"),
                        instructorRS.getString("lastName"),
                        address,
                        instructorRS.getString("email"),
                        instructorRS.getString("phoneNo"),
                        instructorRS.getInt("role"),
                        instructorRS.getString("username"),
                        instructorRS.getString("password"),
                        id,
                        subjects
                );
            }
        }
        return instructor;
    }

    @Override
    public List<Instructor> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(long id, Instructor obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return false;
    }

    public Address getAddress(long id) throws SQLException {
        AddressDB addressDB = new AddressDB();
        return addressDB.get(id);
    }

    public List<Subject> getInstructorSubjects(long id) {
        List<Subject> subjects = new ArrayList<>();
        String sql = " SELECT * FROM instructorSubjects WHERE ssn = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet instructorSubjectRS = stmt.executeQuery();
            SubjectDB subjectDB = new SubjectDB();
            while (instructorSubjectRS.next()) {
                long subjectId = instructorSubjectRS.getLong("subjectID");
                Subject subject = subjectDB.get(subjectId);
                subjects.add(subject);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return subjects;
    }

}
