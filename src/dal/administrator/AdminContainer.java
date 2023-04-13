package dal.administrator;

import model.Administrator;
import model.Person;

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
    	Administrator administrator = null;
        for(int i = 0; i < container.size() && administrator == null; i++) {
            if(container.get(i).getSsn() == id) {
            	administrator = container.get(i);
            }
        }
        return administrator;
    }

    @Override
    public List<Administrator> getAll() throws SQLException {
    	return container;
    }

    @Override
    public boolean update(long id, Administrator obj) throws SQLException {
    	boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getSsn() == id) {
                container.set(i, obj);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean delete(long id) throws SQLException {
    	boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getSsn() == id) {
                container.remove(i);
                result = true;
            }
        }
        return result;
    }
}
