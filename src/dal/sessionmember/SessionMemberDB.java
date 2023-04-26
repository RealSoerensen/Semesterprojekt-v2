package dal.sessionmember;

import dal.DBConnection;
import dal.person.PersonDB;
import dal.session.SessionDB;
import dal.session.SessionDataAccessIF;
import dal.person.PersonDataAccessIF;
import model.Course;
import model.Session;
import model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionMemberDB implements SessionMemberDataAccessIF {

    private final Connection connection;

    /**
     * Constructor for SessionMemberDB class.
     */
    public SessionMemberDB() throws SQLException {
        DBConnection dbConnection = DBConnection.getInstance();
        connection = dbConnection.getConnection();
    }

    @Override
    public boolean create(Session session, Person member) {
        boolean result = false;
        String sql = "INSERT INTO SessionMember (ssn, sessionid) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, member.getSsn());
            preparedStatement.setLong(2, session.getSessionID());
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean isPersonIn(Session session, Person person) {
        boolean result = false;
        String sql = "SELECT * FROM SessionMember WHERE ssn = ? AND sessionid = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, person.getSsn());
            preparedStatement.setLong(2, session.getSessionID());
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
    public List<Person> getAll(Session session) {
        List<Person> members = new ArrayList<>();
        String sql = "SELECT * FROM SessionMember WHERE sessionid = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, session.getSessionID());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PersonDataAccessIF personDataAccessIF = new PersonDB();
                Person member = personDataAccessIF.get(resultSet.getLong("ssn"));
                members.add(member);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return members;
    }

    @Override
    public boolean remove(Session session, Person member) {
        boolean result = false;
        String sql = "DELETE FROM SessionMember WHERE ssn = ? AND sessionid = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, member.getSsn());
            preparedStatement.setLong(2, session.getSessionID());
            preparedStatement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
