package dal.coursemember;

import model.CourseMember;
import model.CourseSessionMember;

import java.util.ArrayList;
import java.util.List;

public class CourseMemberContainer implements CourseMemberDataAccessIF{
    private static CourseMemberContainer instance;
    private final List<CourseMember> container;

    /**
     * Constructor for CourseSessionMemberContainer class.
     * Initializes the container.
     * Private to prevent instantiation.
     * Use getInstance() to get the instance of the CourseSessionMemberContainer.
     */
    private CourseMemberContainer() {
    	container = new ArrayList<>();
    }

    /**
     * Gets the instance of the CourseSessionMemberContainer.
     *
     * @return The instance of the CourseSessionMemberContainer.
     */
    public static CourseMemberContainer getInstance() {
        if (instance == null) {
            instance = new CourseMemberContainer();
        }
        return instance;
    }

    /**
     * Creates a new CourseSessionMember in the container.
     * @param obj The CourseSessionMember to be created.
     *
     * @return True if the CourseSessionMember was created, false otherwise.
     */
    @Override
    public boolean create(CourseMember obj) {
        return container.add(obj);
    }

    /**
     * Gets a CourseSessionMember from the container.
     *
     * @param ssn The ssn of the CourseSessionMember to be retrieved.
     * @param courseSessionID The id of the CourseSession.
     * @return The CourseSessionMember with the given ssn and from the given course session.
     */
    @Override
    public CourseMember getCourseMember(long ssn, long courseID) {
        CourseMember courseMember = null;
        for (int i = 0; i < container.size() && courseMember == null; i++) {
            if (container.get(i).getMember().getSsn() == ssn && container.get(i).getCourse().getCourseID() == courseID) {
                courseMember = container.get(i);
            }
        }
        return courseMember;
    }

    /**
     * Gets all CourseSessionMembers from the container.
     *
     * @return A list of all CourseSessionMembers in the container.
     */
    @Override
    public List<CourseMember> getAll() {
        return container;
    }

    /**
     * Updates a CourseSessionMember in the container.
     * @param id The ID of the CourseSessionMember to be updated.
     * @param obj The CourseSessionMember to be updated.
     *
     * @return True if the CourseSessionMember was updated, false otherwise.
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
     * Deletes a CourseSessionMember from the container.
     * @param id The ID of the CourseSessionMember to be deleted.
     *
     * @return True if the CourseSessionMember was deleted, false otherwise.
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
