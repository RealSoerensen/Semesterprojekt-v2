package dal.administrator;

import model.Administrator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminContainer implements AdminDataAccessIF{
    List<Administrator> container;
    AdminContainer instance;

    public AdminContainer() {
        container = new ArrayList<>();
    }

    public AdminContainer getInstance() {
        if (instance == null) {
            instance = new AdminContainer();
        }
        return instance;
    }

    @Override
    public boolean create(Administrator obj) throws SQLException {
        return container.add(obj);
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
