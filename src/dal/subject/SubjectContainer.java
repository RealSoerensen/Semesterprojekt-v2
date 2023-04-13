package dal.subject;

import model.Member;
import model.Subject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectContainer implements SubjectDataAccessIF {
    private SubjectContainer instance;
    private final List<Subject> container;

    public SubjectContainer() {
        container = new ArrayList<>();
    }

    public SubjectContainer getInstance() {
        if (instance == null) {
            instance = new SubjectContainer();
        }
        return instance;
    }

    @Override
    public boolean create(Subject obj) throws SQLException {
    	return container.add(obj);
    }

    @Override
    public Subject get(long id) throws SQLException {
    	Subject subject = null;
        for(int i = 0; i < container.size() && subject == null; i++) {
            if(container.get(i).getSubjectID() == id) {
            	subject = container.get(i);
            }
        }
        return subject;
    }

    @Override
    public List<Subject> getAll() throws SQLException {
    	return container;
    }

    @Override
    public boolean update(long id, Subject obj) throws SQLException {
    	boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getSubjectID() == id) {
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
            if (container.get(i).getSubjectID() == id) {
                container.remove(i);
                result = true;
            }
        }
        return result;
    }
}
