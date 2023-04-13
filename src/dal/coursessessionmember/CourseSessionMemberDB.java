package dal.coursessessionmember;

import dal.DBConnection;
import dal.coursesession.CourseSessionContainer;
import dal.coursesession.CourseSessionDataAccessIF;
import dal.member.MemberContainer;
import dal.member.MemberDataAccessIF;
import model.CourseSession;
import model.CourseSessionMember;
import model.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseSessionMemberDB implements CourseSessionMemberDataAccessIF {

    private final Connection connection;

    /**
     * Constructor for CourseSessionMemberDB class.
     */
    public CourseSessionMemberDB() throws SQLException {
        DBConnection dbConnection = DBConnection.getInstance();
        connection = dbConnection.getConnection();
    }

    /**
     * Creates a new CourseSessionMember in the database.
     *
     * @param obj The CourseSessionMember to be created.
     * @return True if the CourseSessionMember was created successfully, false otherwise.
     */
    @Override
    public boolean create(CourseSessionMember obj) {
        boolean result = false;
        String sql = "INSERT INTO CourseSessionMembers (ssn, courseSessionID) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, obj.getMember().getSsn());
            stmt.setLong(2, obj.getCourseSession().getCourseSessionID());
            result = stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Gets a CourseSessionMember from the database.
     *
     * @param id The id of the CourseSessionMember to be retrieved.
     * @return The CourseSessionMember with the given id.
     */
    @Override
    public CourseSessionMember get(long id) {
        CourseSessionMember courseSessionMember = null;
        String sql = "SELECT * FROM CourseSessionMembers WHERE ssn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeQuery();
            ResultSet courseSessionMemberRS = stmt.getResultSet();
            if (courseSessionMemberRS.next()) {
                courseSessionMember = getCourseSessionMember(courseSessionMemberRS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseSessionMember;
    }

    /**
     * Gets all CourseSessionMembers from the database.
     *
     * @return A list of all CourseSessionMembers.
     */
    @Override
    public List<CourseSessionMember> getAll() {
        List<CourseSessionMember> courseSessionMembers = new ArrayList<>();
        String sql = "SELECT * FROM CourseSessionMembers";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeQuery();
            ResultSet courseSessionMemberRS = stmt.getResultSet();
            while (courseSessionMemberRS.next()) {
                courseSessionMembers.add(getCourseSessionMember(courseSessionMemberRS));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseSessionMembers;
    }

    /**
     * Updates a CourseSessionMember in the database.
     *
     * @param id  The id of the CourseSessionMember to be updated.
     * @param obj The CourseSessionMember to be updated.
     * @return True if the CourseSessionMember was updated successfully, false otherwise.
     */
    @Override
    public boolean update(long id, CourseSessionMember obj) {
        boolean result = false;
        String sql = "UPDATE CourseSessionMembers SET ssn = ?, courseSessionID = ? WHERE ssn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, obj.getMember().getSsn());
            stmt.setLong(2, obj.getCourseSession().getCourseSessionID());
            stmt.setLong(3, id);
            result = stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Deletes a CourseSessionMember from the database.
     *
     * @param id The id of the CourseSessionMember to be deleted.
     * @return True if the CourseSessionMember was deleted successfully, false otherwise.
     */
    @Override
    public boolean delete(long id) {
        boolean result = false;
        String sql = "DELETE FROM CourseSessionMembers WHERE ssn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            result = stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Gets a CourseSessionMember from the database.
     * @param courseSessionMemberRS The ResultSet containing the CourseSessionMember.
     * @return The CourseSessionMember with the given id.
     */
    private CourseSessionMember getCourseSessionMember(ResultSet courseSessionMemberRS) throws SQLException {
        Member member = getMember(courseSessionMemberRS.getLong("ssn"));
        CourseSession courseSession = getCourseSession(courseSessionMemberRS.getLong("courseSessionID"));
        return new CourseSessionMember(member, courseSession);
    }

    /**
     * Gets a Member from the database.
     * @param ssn The ssn of the Member to be retrieved.
     * @return The Member with the given ssn.
     */
    private Member getMember(long ssn) throws SQLException {
        MemberDataAccessIF memberDB = MemberContainer.getInstance();
        return memberDB.get(ssn);
    }

    /**
     * Gets a CourseSession from the database.
     * @param courseSessionID The id of the CourseSession to be retrieved.
     * @return The CourseSession with the given id.
     */
    private CourseSession getCourseSession(long courseSessionID) throws SQLException {
        CourseSessionDataAccessIF courseSessionDB = CourseSessionContainer.getInstance();
        return courseSessionDB.get(courseSessionID);
    }
}
