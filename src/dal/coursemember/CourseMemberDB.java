package dal.coursemember;

import dal.DBConnection;
import dal.course.CourseContainer;
import dal.course.CourseDB;
import dal.course.CourseDataAccessIF;
import dal.person.PersonContainer;
import dal.person.PersonDB;
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
     * Constructor for CourseMemberDB class.
     */
    public CourseMemberDB() throws SQLException {
        DBConnection dbConnection = DBConnection.getInstance();
        connection = dbConnection.getConnection();
    }

    /**
     * Creates a new CourseMember in the database.
     *
     * @param obj The CourseMember to be created.
     * @return True if the CourseMember was created successfully, false otherwise.
     */
    @Override
    public boolean create(CourseMember obj) {
        boolean result = false;
        String sql = "INSERT INTO CourseMember (ssn, courseID) VALUES (?, ?)";
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
     * Gets a CourseMember from the database.
     *
     * @param ssn The ssn of the CourseMember to be retrieved.
     * @param courseID The id of the Course.
     * @return The CourseMember with the given ssn and from the given course session.
     */
    @Override
    public CourseMember getCourseMember(long ssn, long courseID) {
        CourseMember courseMember = null;
        String sql = "SELECT * FROM SessionMember WHERE ssn = ? AND sessionID = ?";
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
     * Gets all CourseMember from the database.
     *
     * @return A list of all CourseMember.
     */
    @Override
    public List<CourseMember> getAll() {
        List<CourseMember> courseMembers = new ArrayList<>();
        String sql = "SELECT * FROM CourseMember";
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
     * Updates a CourseMember in the database.
     *
     * @param id  The id of the CourseMember to be updated.
     * @param obj The CourseMember to be updated.
     * @return True if the CourseMember was updated successfully, false otherwise.
     */
    @Override
    public boolean update(long id, CourseMember obj) {
        boolean result = false;
        String sql = "UPDATE CourseMember SET ssn = ?, courseID = ? WHERE ssn = ?";
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
     * Deletes a CourseMember from the database.
     *
     * @param id The id of the CourseMember to be deleted.
     * @return True if the CourseMember was deleted successfully, false otherwise.
     */
    @Override
    public boolean delete(long id) {
        boolean result = false;
        String sql = "DELETE FROM CourseMember WHERE ssn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            result = stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Gets a CourseMember from the database.
     * @param courseMemberRS The ResultSet containing the CourseMember.
     * @return The CourseMember with the given id.
     */
    private CourseMember getCourseMember(ResultSet courseMemberRS) throws SQLException {
        Person member = getMember(courseMemberRS.getLong("ssn"));
        Course course = getCourse(courseMemberRS.getLong("courseID"));
        return new CourseMember(member, course);
    }

    /**
     * Gets a Person from the database.
     * @param ssn The ssn of the Person to be retrieved.
     * @return The Person with the given ssn.
     */
    private Person getMember(long ssn) throws SQLException {
        PersonDataAccessIF memberDB = new PersonDB();
        return memberDB.get(ssn);
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

    /**
     * Created to satisfy the interface. It does nothing.
     */
	@Override
	public CourseMember get(long id) {
		throw new UnsupportedOperationException();
	}
}
