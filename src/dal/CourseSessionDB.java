package dal;

import model.CourseSession;

import java.sql.SQLException;
import java.util.List;

public class CourseSessionDB implements CRUD<CourseSession>{
    @Override
    public boolean create(CourseSession obj) throws SQLException {
        return false;
    }

    @Override
    public CourseSession get(long id) throws SQLException {
        return null;
    }

    @Override
    public List<CourseSession> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(CourseSession obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return false;
    }
}
