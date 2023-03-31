package dal;

import model.Course;

import java.sql.SQLException;
import java.util.List;

public class CourseDB implements CRUD<Course>{
    @Override
    public boolean create(Course obj) throws SQLException {
        return false;
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
