package dal.administrator;

import model.Administrator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminContainer implements AdminDataAccessIF{
    final List<Administrator> container;
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
    public boolean create(Administrator obj) {
        return container.add(obj);
    }

    @Override
    public Administrator get(long id) {
        return null;
    }

    @Override
    public List<Administrator> getAll() {
        return null;
    }

    @Override
    public boolean update(long id, Administrator obj) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
