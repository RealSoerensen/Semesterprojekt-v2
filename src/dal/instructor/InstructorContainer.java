package dal.instructor;

import model.Instructor;

import java.util.ArrayList;
import java.util.List;

public class InstructorContainer implements InstructorDataAccessIF {
    private InstructorContainer instance;
    private final List<Instructor> container;

    public InstructorContainer() {
        container = new ArrayList<>();
    }

    public InstructorContainer getInstance() {
        if (instance == null) {
            instance = new InstructorContainer();
        }
        return instance;
    }

    @Override
    public boolean create(Instructor obj) {
    	return container.add(obj);
    }

    @Override
    public Instructor get(long id) {
    	Instructor instructor = null;
        for(int i = 0; i < container.size() && instructor == null; i++) {
            if(container.get(i).getSsn() == id) {
            	instructor = container.get(i);
            }
        }
        return instructor;
    }

    @Override
    public List<Instructor> getAll() {
    	return container;
    }

    @Override
    public boolean update(long id, Instructor obj) {
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
