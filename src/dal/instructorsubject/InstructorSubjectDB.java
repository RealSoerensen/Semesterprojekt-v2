package dal.instructorsubject;

import dal.DBConnection;
import model.Person;
import model.Subject;

import java.sql.Connection;
import java.sql.SQLException;

public class InstructorSubjectDB implements InstructorSubjectDataAccessIF{
    private final Connection connection;

    public InstructorSubjectDB() throws SQLException {
        connection = DBConnection.getInstance().getConnection();
    }


    @Override
    public void create(Person person, Subject subject) {
        String sql = "INSERT INTO InstructorSubject (ssn, subjectid) VALUES (?, ?)";
        try (var stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, person.getSsn());
            stmt.setLong(2, subject.getSubjectID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isPersonIn(Person person, Subject subject) {
        boolean result = false;
        String sql = "SELECT * FROM InstructorSubject WHERE ssn = ? AND subjectid = ?";
        try (var stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, person.getSsn());
            stmt.setLong(2, subject.getSubjectID());
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public boolean remove(Person person, Subject subject) {
        boolean result;
        String sql = "DELETE FROM InstructorSubject WHERE ssn = ? AND subjectid = ?";
        try (var stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, person.getSsn());
            stmt.setLong(2, subject.getSubjectID());
            result = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void removeAllByPerson(Person person) {
        String sql = "DELETE FROM InstructorSubject WHERE ssn = ?";
        try (var stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, person.getSsn());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
