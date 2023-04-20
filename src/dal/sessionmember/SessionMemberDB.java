package dal.sessionmember;

import dal.DBConnection;
import dal.person.PersonDB;
import dal.session.SessionDB;
import dal.session.SessionDataAccessIF;
import dal.person.PersonDataAccessIF;
import model.Session;
import model.SessionMember;
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

    /**
     * Creates a new SessionMembers in the database.
     *
     * @param obj The SessionMembers to be created.
     * @return True if the SessionMembers was created successfully, false otherwise.
     */
    @Override
    public boolean create(SessionMember obj) {
        boolean result = false;
        String sql = "INSERT INTO SessionMember (ssn, sessionID) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, obj.getPerson().getSsn());
            stmt.setLong(2, obj.getSession().getSessionID());
            result = stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Gets a SessionMembers from the database.
     *
     * @param ssn       The ssn of the SessionMembers to be retrieved.
     * @param sessionID The id of the Session.
     * @return The SessionMembers with the given ssn and from the given course
     *         session.
     */
    @Override
    public SessionMember getSessionMember(long ssn, long sessionID) {
        SessionMember sessionMember = null;
        String sql = "SELECT * FROM SessionMember WHERE ssn = ? AND sessionID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, ssn);
            stmt.setLong(2, sessionID);
            stmt.executeQuery();
            ResultSet sessionMemberRS = stmt.getResultSet();
            if (sessionMemberRS.next()) {
                sessionMember = getSessionMember(sessionMemberRS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessionMember;
    }

    /**
     * Gets all SessionMembers from the database.
     *
     * @return A list of all SessionMembers.
     */
    @Override
    public List<SessionMember> getAll() {
        List<SessionMember> sessionMembers = new ArrayList<>();
        String sql = "SELECT * FROM SessionMember";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeQuery();
            ResultSet sessionMemberRS = stmt.getResultSet();
            while (sessionMemberRS.next()) {
                sessionMembers.add(getSessionMember(sessionMemberRS));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessionMembers;
    }

    /**
     * Updates a SessionMembers in the database.
     *
     * @param id  The id of the SessionMembers to be updated.
     * @param obj The SessionMembers to be updated.
     * @return True if the SessionMembers was updated successfully, false otherwise.
     */
    @Override
    public boolean update(long id, SessionMember obj) {
        boolean result = false;
        String sql = "UPDATE SessionMember SET ssn = ?, courseSessionID = ? WHERE ssn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, obj.getPerson().getSsn());
            stmt.setLong(2, obj.getSession().getSessionID());
            stmt.setLong(3, id);
            result = stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Deletes a SessionMembers from the database.
     *
     * @param id The id of the SessionMembers to be deleted.
     * @return True if the SessionMembers was deleted successfully, false otherwise.
     */
    @Override
    public boolean delete(long id) {
        boolean result = false;
        String sql = "DELETE FROM SessionMember WHERE ssn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            result = stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Gets a SessionMembers from the database.
     * 
     * @param sessionMemberRS The ResultSet containing the SessionMembers.
     * @return The SessionMembers with the given id.
     */
    private SessionMember getSessionMember(ResultSet sessionMemberRS) throws SQLException {
        Person member = getMember(sessionMemberRS.getLong("ssn"));
        Session session = getSession(sessionMemberRS.getLong("courseSessionID"));
        return new SessionMember(member, session);
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

    /**
     * Created to satisfy the interface. It does nothing.
     */
    @Override
    public SessionMember get(long id) {
        throw new UnsupportedOperationException();
    }
}
