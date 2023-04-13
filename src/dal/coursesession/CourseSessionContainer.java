package dal.coursesession;

import model.CourseSession;

import java.util.ArrayList;
import java.util.List;

public class CourseSessionContainer implements CourseSessionDataAccessIF {
    private CourseSessionContainer instance;
    private final List<CourseSession> container;

    public CourseSessionContainer() {
    	container = new ArrayList<>();
    }

    public CourseSessionContainer getInstance() {
        if (instance == null) {
            instance = new CourseSessionContainer();
        }
        return instance;
    }

    @Override
    public boolean create(CourseSession obj) {
    	return container.add(obj);
    }

    @Override
    public CourseSession get(long id) {
    	CourseSession courseSession = null;
        for(int i = 0; i < container.size() && courseSession == null; i++) {
            if(container.get(i).getCourseSessionID() == id) {
            	courseSession = container.get(i);
            }
        }
        return courseSession;
    }

    @Override
    public List<CourseSession> getAll() {
    	return container;
    }

    @Override
    public boolean update(long id, CourseSession obj) {
    	boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getCourseSessionID() == id) {
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
            if (container.get(i).getCourseSessionID() == id) {
                container.remove(i);
                result = true;
            }
        }
        return result;
    }
}
