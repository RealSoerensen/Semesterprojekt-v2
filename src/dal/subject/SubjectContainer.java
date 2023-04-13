package dal.subject;

import model.Subject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectContainer implements SubjectDataAccessIF {
    private SubjectContainer instance;
    private final List<Subject> subjects;

    public SubjectContainer() {
        subjects = new ArrayList<>();
    }

    public SubjectContainer getInstance() {
        if (instance == null) {
            instance = new SubjectContainer();
        }
        return instance;
    }

    @Override
    public boolean create(Subject obj) throws SQLException {
        return false;
    }

    @Override
    public Subject get(long id) throws SQLException {
        return null;
    }

    @Override
    public List<Subject> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(long id, Subject obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return false;
    }
}
