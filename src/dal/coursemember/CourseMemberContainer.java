package dal.coursemember;

import model.Course;
import model.CourseMember;

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
     * Gets the instance of the SessionMemberContainer.
     *
     * @return The instance of the SessionMemberContainer.
     */
    public static CourseMemberContainer getInstance() {
        if (instance == null) {
            instance = new CourseMemberContainer();
        }
        return instance;
    }

    /**
     * Creates a new SessionMember in the container.
     * @param obj The SessionMember to be created.
     *
     * @return True if the SessionMember was created, false otherwise.
     */
    @Override
    public boolean create(CourseMember obj) {
        return container.add(obj);
    }

    /**
     * Gets a CourseMember from the container.
     *
     * @param ssn The ssn of the CourseMember to be retrieved.
     * @param course The course of the CourseMember to be retrieved.
     * @return The CourseMember with the given ssn and from the given course.
     */
    @Override
    public CourseMember getCourseMember(long ssn, Course course) {
        CourseMember courseMember = null;
        for (int i = 0; i < container.size() && courseMember == null; i++) {
            if (container.get(i).getMember().getSsn() == ssn && container.get(i).getCourse().equals(course)) {
                courseMember = container.get(i);
            }
        }
        return courseMember;
    }

    /**
     * Gets all CourseMembers from the container.
     *
     * @return A list of all CourseMembers in the container.
     */
    @Override
    public List<CourseMember> getAll() {
        return container;
    }

    /**
     * Updates a CourseMember in the container.
     * @param id The ID of the CourseMember to be updated.
     * @param obj The CourseMember to be updated.
     *
     * @return True if the CourseMember was updated, false otherwise.
     */
    @Override
    public boolean update(long id, CourseMember obj) {
        boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getMember().getSsn() == id) {
                container.set(i, obj);
                result = true;
            }
        }
        return result;
    }

    /**
     * Deletes a CourseMember from the container.
     * @param id The ID of the CourseMember to be deleted.
     *
     * @return True if the CourseMember was deleted, false otherwise.
     */
    @Override
    public boolean delete(long id) {
        boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getMember().getSsn() == id) {
                container.remove(i);
                result = true;
            }
        }
        return result;
    }

    /**
     * Created to satisfy the interface. It does nothing.
     */
	@Override
	public CourseMember get(long id) {
		throw new UnsupportedOperationException();
	}
}
