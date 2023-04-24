package dal.coursemember;

import model.Course;
import model.Person;

import java.util.ArrayList;
import java.util.List;

public class CourseMemberContainer implements CourseMemberDataAccessIF{
    private static CourseMemberContainer instance;
    private final List<Person> container;

    /**
     * Constructor for SessionMemberContainer class.
     * Initializes the container.
     * Private to prevent instantiation.
     * Use getInstance() to get the instance of the SessionMemberContainer.
     */
    private CourseMemberContainer() {
    	container = new ArrayList<>();
    }

    /**
     * Gets the instance of the CourseMemberContainer.
     * @return The instance of the CourseMemberContainer.
     */
    public static CourseMemberContainer getInstance() {
        if (instance == null) {
            instance = new CourseMemberContainer();
        }
        return instance;
    }

    @Override
    public boolean create(Course course, Person member) {
        return false;
    }

    @Override
    public Person getMemberFromCourse(long ssn, Course course) {
        return null;
    }

    @Override
    public List<Person> getMembersInCourse(Course course) {
        return null;
    }

    @Override
    public boolean removeMemberFromCourse(Course course, Person member) {
        return false;
    }
}
