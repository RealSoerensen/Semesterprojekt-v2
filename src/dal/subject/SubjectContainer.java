package dal.subject;

import model.Subject;

import java.util.ArrayList;
import java.util.List;

/*
    * SubjectContainer class
    * Implements SubjectDataAccessIF
    * Singleton
 */
public class SubjectContainer implements SubjectDataAccessIF {
    private static SubjectContainer instance;
    private final List<Subject> container;

    /**
     * The SubjectContainer function is a container for the Subject class.
     * It allows us to add, remove and get subjects from the list of subjects.
     */
    public SubjectContainer() {
        container = new ArrayList<>();
    }

    /**
     * The getInstance function is a static function that returns the singleton instance of the SubjectContainer class.
     * If no instance exists, it creates one and then returns it.
     * @return The instance of the subjectContainer class that is currently in use
     */
    public static SubjectContainer getInstance() {
        if (instance == null) {
            instance = new SubjectContainer();
        }
        return instance;
    }

    /**
     * The create function adds a new Subject to the container.
     *
     * @param obj Pass in a subject object to be added to the container
     *
     * @return A boolean value that indicates whether the subject was added to the container or not
     */
    @Override
    public boolean create(Subject obj) {
    	return container.add(obj);
    }

    /**
     * The get function is used to retrieve a subject from the container.
     *
     * @param id Find the subject in the container
     *
     * @return A subject object that is found in the container
    */
    @Override
    public Subject get(long id) {
    	Subject subject = null;
        for(int i = 0; i < container.size() && subject == null; i++) {
            if(container.get(i).getSubjectID() == id) {
            	subject = container.get(i);
            }
        }
        return subject;
    }

    /**
     * The getAll function returns a list of all the subjects in the container.
     *
     * @return A list of all the subjects in the container
     */
    @Override
    public List<Subject> getAll() {
    	return container;
    }

    /**
     * The update function is used to update the Subject object in the container.
     *
     * @param obj Update the subject object in the container
     *
     * @return A boolean value that indicates whether the subject was updated in the container or not
     */
    @Override
    public boolean update(Subject obj) {
    	boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getSubjectID() == obj.getSubjectID()) {
                container.set(i, obj);
                result = true;
            }
        }
        return result;
    }

    /**
     * The delete function deletes a subject from the container.
     *
     * @param obj Delete the subject object from the container
     *
     * @return True if the object was deleted, and false otherwise
     */
    @Override
    public boolean delete(Subject obj) {
    	boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getSubjectID() == obj.getSubjectID()) {
                container.remove(i);
                result = true;
            }
        }
        return result;
    }
}
