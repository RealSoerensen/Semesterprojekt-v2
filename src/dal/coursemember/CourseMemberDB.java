package dal.coursemember;

import dal.DBConnection;
import dal.person.PersonDB;
import model.Course;
import model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseMemberDB implements CourseMemberDataAccessIF {

    private final Connection connection;

    /**
     * Constructor for CourseMemberDB class.
     */
    public CourseMemberDB() throws SQLException {
        DBConnection dbConnection = DBConnection.getInstance();
        connection = dbConnection.getConnection();
    }


    @Override
    public boolean create(Course course, Person member) {
        boolean result;
        String sql = "INSERT INTO CourseMember(ssn, courseID) VALUES(?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, member.getSsn());
            preparedStatement.setLong(2, course.getCourseID());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean isPersonIn(Course course, Person person) {
        boolean result = false;
        String sql = "SELECT * FROM CourseMember WHERE ssn = ? AND courseID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, person.getSsn());
            preparedStatement.setLong(2, course.getCourseID());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public List<Person> getAll(Course course) {
        List<Person> result = new ArrayList<>();
        String sql = "SELECT ssn FROM CourseMember WHERE courseID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, course.getCourseID());
            stmt.executeQuery();
            ResultSet subjectRS = stmt.getResultSet();
            while (subjectRS.next()) {
                long ssn = subjectRS.getLong("ssn");
                Person person = getPerson(ssn);
                result.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean remove(Course course, Person member) {
        boolean result;
        String sql = "DELETE FROM CourseMember WHERE ssn = ? AND courseID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, member.getSsn());
            stmt.setLong(2, course.getCourseID());
            result = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private Person getPerson(long ssn) throws SQLException {
        PersonDB personDB = new PersonDB();
        return personDB.get(ssn);
    }

    @Override
    public void removeAll(Course course) {
        String sql = "DELETE FROM CourseMember WHERE courseID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, course.getCourseID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
