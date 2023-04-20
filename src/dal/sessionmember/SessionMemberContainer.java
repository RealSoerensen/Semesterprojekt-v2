package dal.sessionmember;

import model.SessionMember;

import java.util.ArrayList;
import java.util.List;

public class SessionMemberContainer implements SessionMemberDataAccessIF {
    private static SessionMemberContainer instance;
    private final List<SessionMember> container;

    /**
     * Constructor for CourseSessionMemberContainer class.
     * Initializes the container.
     * Private to prevent instantiation.
     * Use getInstance() to get the instance of the CourseSessionMemberContainer.
     */
    private SessionMemberContainer() {
    	container = new ArrayList<>();
    }

    /**
     * Gets the instance of the CourseSessionMemberContainer.
     *
     * @return The instance of the CourseSessionMemberContainer.
     */
    public static SessionMemberContainer getInstance() {
        if (instance == null) {
            instance = new SessionMemberContainer();
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
    public boolean create(SessionMember obj) {
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
    public SessionMember getCourseSessionMember(long ssn, long courseSessionID) {
        SessionMember courseSessionMember = null;
        for (int i = 0; i < container.size() && courseSessionMember == null; i++) {
            if (container.get(i).getPerson().getSsn() == ssn && container.get(i).getCourseSession().getSessionID() == courseSessionID) {
                courseSessionMember = container.get(i);
            }
        }
        return courseSessionMember;
    }

    /**
     * Gets all CourseSessionMembers from the container.
     *
     * @return A list of all CourseSessionMembers in the container.
     */
    @Override
    public List<SessionMember> getAll() {
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
    public boolean update(long id, SessionMember obj) {
        boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getPerson().getSsn() == id) {
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
            if (container.get(i).getPerson().getSsn() == id) {
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
	public SessionMember get(long id) {
		throw new UnsupportedOperationException();
	}
}
