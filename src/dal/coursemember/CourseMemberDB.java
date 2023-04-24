package dal.coursemember;

import dal.DBConnection;
import dal.course.CourseDB;
import dal.course.CourseDataAccessIF;
import dal.person.PersonDB;
import dal.person.PersonDataAccessIF;
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
        return false;
    }

    @Override
    public Person getMemberFromCourse(long ssn, Course course) {
        return null;
    }

    @Override
    public List<Person> getMembersInCourse(Course course) {
        return null;
    }

    @Override
    public boolean removeMemberFromCourse(Course course, Person member) {
        return false;
    }
}
