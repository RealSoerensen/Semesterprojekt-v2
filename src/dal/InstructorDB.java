package dal;

import model.Instructor;

import java.sql.SQLException;
import java.util.List;

public class InstructorDB implements CRUD<Instructor> {

    @Override
    public boolean create(Instructor obj) throws SQLException {
        return false;
    }

    @Override
    public boolean create(Instructor obj, long id) throws SQLException {
        throw new UnsupportedOperationException();
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
