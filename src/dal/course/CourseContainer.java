package dal.course;

import model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseContainer implements CourseDataAccessIF{
    private static CourseContainer instance;
    private final List<Course> container;

    private CourseContainer() {
        container = new ArrayList<>();
    }

    public static CourseContainer getInstance() {
        if(instance == null) {
            instance = new CourseContainer();
        }
        return instance;
    }

    @Override
    public boolean create(Course obj) {
        return container.add(obj);
    }

    @Override
    public Course get(long id) {
        Course course = null;
        for (int i = 0; i < container.size() && course == null; i++) {
            if (container.get(i).getCourseID() == id) {
                course = container.get(i);
            }
        }
        return course;
    }

    @Override
    public List<Course> getAll() {
        return container;
    }

    @Override
    public boolean update(long id, Course obj) {
        boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getCourseID() == id) {
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
            if (container.get(i).getCourseID() == id) {
                container.remove(i);
                result = true;
            }
        }
        return result;
    }
}
