package dal.coursesession;

import model.CourseSession;

import java.util.ArrayList;
import java.util.List;

public class CourseSessionContainer implements CourseSessionDataAccessIF {
    private static CourseSessionContainer instance;
    private final List<CourseSession> container;

    /**
     * Constructor for CourseSessionContainer class.
     * Initializes the container.
     * Private to prevent instantiation.
     * Use getInstance() to get the instance of the CourseSessionContainer.
     */
    private CourseSessionContainer() {
    	container = new ArrayList<>();
    }

    /**
     * Gets the instance of the CourseSessionContainer.
     *
     * @return The instance of the CourseSessionContainer.
     */
    public static CourseSessionContainer getInstance() {
        if (instance == null) {
            instance = new CourseSessionContainer();
        }
        return instance;
    }

    /**
     * Creates a new CourseSession in the container.
     * @param obj The CourseSession to be created.
     *
     * @return True if the CourseSession was created, false otherwise.
     */
    @Override
    public boolean create(CourseSession obj) {
    	return container.add(obj);
    }

    /**
     * Gets a CourseSession from the container.
     * @param id The ID of the CourseSession to be retrieved.
     *
     * @return The CourseSession with the given ID, null if no CourseSession with the given ID was found.
     */
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

    /**
     * Gets all CourseSessions from the container.
     *
     * @return A list of all CourseSessions in the container.
     */
    @Override
    public List<CourseSession> getAll() {
    	return container;
    }

    /**
     * Updates a CourseSession in the container.
     * @param id The ID of the CourseSession to be updated.
     * @param obj The CourseSession to be updated.
     *
     * @return True if the CourseSession was updated, false otherwise.
     */
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

    /**
     * Deletes a CourseSession from the container.
     * @param id The ID of the CourseSession to be deleted.
     *
     * @return True if the CourseSession was deleted, false otherwise.
     */
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
