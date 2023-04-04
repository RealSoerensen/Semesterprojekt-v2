package dal;

import model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDB implements CRUD<Course>{
    DBConnection dbConnection;
    Connection connection;

    public CourseDB() throws SQLException {
        dbConnection = DBConnection.getInstance();
        connection = dbConnection.getConnection();
    }

    @Override
    public boolean create(Course obj) throws SQLException {
        String sql = " INSERT INTO course(name, price, description, period) " +
                " VALUES(?, ?, ?, ?) ";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, obj.getName());
            stmt.setDouble(2, obj.getPrice());
            stmt.setString(3, obj.getDescription());
            stmt.setString(4, obj.getPeriod());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public Course get(long id) throws SQLException {
        String sql = " SELECT * FROM course WHERE courseID = ? ";
        ResultSet courseRS;
        Course course = null;
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            courseRS = stmt.executeQuery();
        }
        if (courseRS.next()) {
            course = new Course(
                    courseRS.getString("name"),
                    courseRS.getDouble("price"),
                    courseRS.getString("description"),
                    courseRS.getString("period")
            );
        }
        return course;
    }

    @Override
    public List<Course> getAll() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = " SELECT * FROM course ";
        ResultSet courseRS;
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            courseRS = stmt.executeQuery();
        }
        if(courseRS.next()) {
            Course course = new Course(
                    courseRS.getString("name"),
                    courseRS.getDouble("price"),
                    courseRS.getString("description"),
                    courseRS.getString("period")
            );
            courses.add(course);
        }
        return courses;
    }

    @Override
    public boolean update(long id, Course obj) throws SQLException {
        String sql = " UPDATE course SET name = ?, price = ?, description = ?, period = ? " +
                " WHERE courseID = ? ";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, obj.getName());
            stmt.setDouble(2, obj.getPrice());
            stmt.setString(3, obj.getDescription());
            stmt.setString(4, obj.getPeriod());
            stmt.setLong(5, id);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(long id) throws SQLException {
        String sql = " DELETE FROM course WHERE courseID = ? ";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}
