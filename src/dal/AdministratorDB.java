package dal;

import model.Administrator;
import model.Course;

import java.sql.SQLException;
import java.util.List;

public class AdministratorDB implements CRUD<Administrator>{
    @Override
    public boolean create(Administrator obj) throws SQLException {
        return false;
    }

    @Override
    public Administrator get(long id) throws SQLException {
        return null;
    }

    @Override
    public List<Administrator> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(long id, Administrator obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return false;
    }
}
