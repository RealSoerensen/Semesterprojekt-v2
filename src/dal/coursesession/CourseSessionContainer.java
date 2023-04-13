package dal.coursesession;

import model.CourseSession;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseSessionContainer implements CourseSessionDataAccessIF {
    private CourseSessionContainer instance;
    private final List<CourseSession> courseSessions;

    public CourseSessionContainer() {
        courseSessions = new ArrayList<>();
    }

    public CourseSessionContainer getInstance() {
        if (instance == null) {
            instance = new CourseSessionContainer();
        }
        return instance;
    }

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
    public boolean update(long id, CourseSession obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return false;
    }
}
