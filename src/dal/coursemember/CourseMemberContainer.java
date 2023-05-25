package dal.coursemember;

import model.Course;
import model.Person;

import java.util.ArrayList;
import java.util.List;

public class CourseMemberContainer implements CourseMemberDataAccessIF{
    private static CourseMemberContainer instance;
    private final List<CourseMember> container;

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
        boolean result = false;
        CourseMember newCourseMember = new CourseMember(course, member);
        if(!isPersonIn(course, member)) {
            container.add(newCourseMember);
            result = true;
        }
        return result;
    }

    @Override
    public boolean isPersonIn(Course course, Person person) {
        Person result = null;
        CourseMember newCourseMember = new CourseMember(course, person);
        for(int i = 0; i < container.size() && result == null; i++) {
            CourseMember courseMember = container.get(i);
            if(newCourseMember.equals(courseMember)) {
                result = courseMember.member();
            }
        }
        return result != null;
    }

    @Override
    public List<Person> getAll(Course course) {
        List<Person> result = new ArrayList<>();
        for (CourseMember courseMember : container) {
            if (courseMember.course().equals(course)) {
                result.add(courseMember.member());
            }
        }
        return result;
    }

    @Override
    public boolean remove(Course course, Person member) {
        boolean result = false;
        for(int i = 0; i < container.size() && !result; i++) {
            CourseMember courseMember = container.get(i);
            if(courseMember.course().equals(course) && courseMember.member().equals(member)) {
                container.remove(i);
                result = true;
            }
        }
        return result;
    }

    @Override
    public void removeAll(Course course) {
        boolean result = false;
        for(int i = 0; i < container.size() && !result; i++) {
            CourseMember courseMember = container.get(i);
            if(courseMember.course().getCourseID() == course.getCourseID()) {
                container.remove(i);
                result = true;
            }
        }
    }
}

record CourseMember(Course course, Person member) {
}
