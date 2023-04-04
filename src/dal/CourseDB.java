package dal;

import model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    public List<Course> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(Course obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return false;
    }
}
