package dal.course;

import model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseContainer implements CourseDataAccessIF{
    private static CourseContainer instance;
    private final List<Course> container;

    /**
     * Constructor for CourseContainer class.
     * Initializes the container.
     * Private to prevent instantiation.
     * Use getInstance() to get the instance of the CourseContainer.
     */
    private CourseContainer() {
        container = new ArrayList<>();
    }

    /**
     * Gets the instance of the CourseContainer.
     *
     * @return The instance of the CourseContainer.
     */
    public static CourseContainer getInstance() {
        if(instance == null) {
            instance = new CourseContainer();
        }
        return instance;
    }

    /**
     * Creates a new Course in the container.
     *
     * @param obj The Course to be created.
     * @return The created Course.
     */
    @Override
    public Course create(Course obj) {
        container.add(obj);
        return obj;
    }

    /**
     * Gets a Course from the container.
     * @param id The ID of the Course to be retrieved.
     *
     * @return The Course with the given ID, null if no Course with the given ID was found.
     */
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

    /**
     * Gets all Courses from the container.
     *
     * @return A list of all Courses in the container.
     */
    @Override
    public List<Course> getAll() {
        return container;
    }

    /**
     * Updates a Course in the container.
     * @param obj The Course to be updated.
     *
     * @return True if the Course was updated, false otherwise.
     */
    @Override
    public boolean update(Course obj) {
        boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getCourseID() == obj.getCourseID()) {
                container.set(i, obj);
                result = true;
            }
        }
        return result;
    }

    /**
     * Deletes a Course from the container.
     * @param obj The Course to be deleted.
     *
     * @return True if the Course was deleted, false otherwise.
     */
    @Override
    public boolean delete(Course obj) {
        boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getCourseID() == obj.getCourseID()) {
                container.remove(i);
                result = true;
            }
        }
        return result;
    }
}
