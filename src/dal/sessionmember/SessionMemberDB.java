package dal.sessionmember;

import dal.DBConnection;
import dal.person.PersonDB;
import dal.session.SessionDB;
import dal.session.SessionDataAccessIF;
import dal.person.PersonDataAccessIF;
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
        return false;
    }

    @Override
    public Person getMemberFromSession(long ssn, Session session) {
        return null;
    }

    @Override
    public List<Person> getMembersInSession(Session session) {
        return null;
    }

    @Override
    public boolean removeMemberFromSession(Session session, Person member) {
        return false;
    }

    /**
     * Gets a Member from the database.
     * 
     * @param ssn The ssn of the Member to be retrieved.
     * @return The Member with the given ssn.
     */
    private Person getMember(long ssn) throws SQLException {
        PersonDataAccessIF memberDB = new PersonDB();
        return memberDB.get(ssn);
    }

    /**
     * Gets a Session from the database.
     * 
     * @param sessionID The id of the Session to be retrieved.
     * @return The Session with the given id.
     */
    private Session getSession(long sessionID) throws SQLException {
        SessionDataAccessIF sessionDB = new SessionDB();
        return sessionDB.get(sessionID);
    }
}
