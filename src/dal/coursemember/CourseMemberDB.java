package dal.coursemember;

import dal.DBConnection;
import dal.course.CourseDB;
import dal.course.CourseDataAccessIF;
import dal.person.PersonDB;
import dal.person.PersonDataAccessIF;
import model.Course;
import model.Person;
import model.Session;

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
        return false;
    }

    @Override
    public boolean isPersonIn(Course course, Person person) {
        boolean result = false;
        String sql = "SELECT * FROM CourseMember WHERE ssn = ? AND sessionid = ?";
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
        return null;
    }

    @Override
    public boolean remove(Course course, Person member) {
        return false;
    }
}
