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
    	Administrator administrator = null;
        for(int i = 0; i < container.size() && administrator == null; i++) {
            if(container.get(i).getSsn() == id) {
            	administrator = container.get(i);
            }
        }
        return administrator;
    }

    @Override
    public List<Administrator> getAll() {
    	return container;
    }

    @Override
    public boolean update(long id, Administrator obj) {
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
    public boolean delete(long id) {
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
