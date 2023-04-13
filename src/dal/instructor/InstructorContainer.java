package dal.instructor;

import model.Instructor;

import java.util.ArrayList;
import java.util.List;

public class InstructorContainer implements InstructorDataAccessIF {
    private InstructorContainer instance;
    private final List<Instructor> container;

    /**
     * The InstructorContainer function is a singleton class that creates an ArrayList of Instructor objects.
     * It is private so that it can only be accessed by the getInstance function.
     */
    public InstructorContainer() {
        container = new ArrayList<>();
    }

    /**
     * The getInstance function is a static function that returns the singleton instance of InstructorContainer.
     * If the instance does not exist, it creates one and then returns it.
     *
     * @return The instance of the class InstructorContainer
     */
    public InstructorContainer getInstance() {
        if (instance == null) {
            instance = new InstructorContainer();
        }
        return instance;
    }

    /**
     * The create function adds a new Instructor object to the container.
     *
     * @param obj Add the instructor to the container
     *
     * @return A boolean value to indicate whether the instructor was added to the container or not
     */
    @Override
    public boolean create(Instructor obj) {
    	return container.add(obj);
    }

    /**
     * The get function returns an instructor from the container.
     *
     * @param id The id of the instructor to be returned
     *
     * @return The instructor with the specified id
     */
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

    /**
     * The getAll function returns a list of all the instructors in the container.
     *
     * @return A list of all the instructors in the container
     */
    @Override
    public List<Instructor> getAll() {
    	return container;
    }

    /**
     * The update function updates an instructor in the container.
     *
     * @param id The id of the instructor to be updated
     * @param obj The instructor object to be updated
     *
     * @return A boolean value to indicate whether the instructor was updated in the container or not
     */
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

    /**
     * The delete function deletes an instructor from the container.
     *
     * @param id The id of the instructor to be deleted
     *
     * @return A boolean value to indicate whether the instructor was deleted from the container or not
     */
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
