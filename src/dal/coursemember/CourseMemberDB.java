package dal.coursemember;

import dal.DBConnection;
import dal.course.CourseContainer;
import dal.course.CourseDataAccessIF;
import dal.person.PersonContainer;
import dal.person.PersonDataAccessIF;
import model.Course;
import model.CourseMember;
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
     * Constructor for CourseSessionMemberDB class.
     */
    public CourseMemberDB() throws SQLException {
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
    public boolean create(CourseMember obj) {
        boolean result = false;
        String sql = "INSERT INTO CourseMembers (ssn, courseID) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, obj.getMember().getSsn());
            stmt.setLong(2, obj.getCourse().getCourseID());
            result = stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Gets a CourseSessionMember from the database.
     *
     * @param ssn The ssn of the CourseSessionMember to be retrieved.
     * @param courseSessionID The id of the CourseSession.
     * @return The CourseSessionMember with the given ssn and from the given course session.
     */
    @Override
    public CourseMember getCourseMember(long ssn, long courseID) {
        CourseMember courseMember = null;
        String sql = "SELECT * FROM CourseSessionMembers WHERE ssn = ? AND courseSessionID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, ssn);
            stmt.setLong(2, courseID);
            stmt.executeQuery();
            ResultSet courseMemberRS = stmt.getResultSet();
            if (courseMemberRS.next()) {
                courseMember = getCourseMember(courseMemberRS);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseMember;
    }

    /**
     * Gets all CourseSessionMembers from the database.
     *
     * @return A list of all CourseSessionMembers.
     */
    @Override
    public List<CourseMember> getAll() {
        List<CourseMember> courseMembers = new ArrayList<>();
        String sql = "SELECT * FROM CourseMembers";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeQuery();
            ResultSet courseMemberRS = stmt.getResultSet();
            while (courseMemberRS.next()) {
                courseMembers.add(getCourseMember(courseMemberRS));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseMembers;
    }

    /**
     * Updates a CourseSessionMember in the database.
     *
     * @param id  The id of the CourseSessionMember to be updated.
     * @param obj The CourseSessionMember to be updated.
     * @return True if the CourseSessionMember was updated successfully, false otherwise.
     */
    @Override
    public boolean update(long id, CourseMember obj) {
        boolean result = false;
        String sql = "UPDATE CourseMembers SET ssn = ?, courseID = ? WHERE ssn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, obj.getMember().getSsn());
            stmt.setLong(2, obj.getCourse().getCourseID());
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
        String sql = "DELETE FROM CourseMembers WHERE ssn = ?";
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
    private CourseMember getCourseMember(ResultSet courseMemberRS) throws SQLException {
        Person member = getMember(courseMemberRS.getLong("ssn"));
        Course course = getCourse(courseMemberRS.getLong("courseID"));
        return new CourseMember(member, course);
    }

    /**
     * Gets a Member from the database.
     * @param ssn The ssn of the Member to be retrieved.
     * @return The Member with the given ssn.
     */
    private Person getMember(long ssn) throws SQLException {
        PersonDataAccessIF memberDB = PersonContainer.getInstance();
        return memberDB.get(ssn);
    }

    /**
     * Gets a CourseSession from the database.
     * @param courseID The id of the CourseSession to be retrieved.
     * @return The CourseSession with the given id.
     */
    private Course getCourse(long courseID) throws SQLException {
        CourseDataAccessIF courseDB = CourseContainer.getInstance();
        return courseDB.get(courseID);
    }

    /**
     * Created to satisfy the interface. It does nothing.
     */
	@Override
	public CourseMember get(long id) {
		throw new UnsupportedOperationException();
	}
}
