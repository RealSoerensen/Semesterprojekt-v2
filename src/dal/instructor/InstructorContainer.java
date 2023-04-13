package dal.instructor;

import model.Instructor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorContainer implements InstructorDataAccessIF {
    private InstructorContainer instance;
    private final List<Instructor> instructors;

    public InstructorContainer() {
        instructors = new ArrayList<>();
    }

    public InstructorContainer getInstance() {
        if (instance == null) {
            instance = new InstructorContainer();
        }
        return instance;
    }

    @Override
    public boolean create(Instructor obj) throws SQLException {
        return false;
    }

    @Override
    public Instructor get(long id) throws SQLException {
        return null;
    }

    @Override
    public List<Instructor> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(long id, Instructor obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return false;
    }
}
