package dal.sessionmember;

import model.SessionMember;

import java.util.ArrayList;
import java.util.List;

public class SessionMemberContainer implements SessionMemberDataAccessIF {
    private static SessionMemberContainer instance;
    private final List<SessionMember> container;

    /**
     * Constructor for SessionMemberContainer class.
     * Initializes the container.
     * Private to prevent instantiation.
     * Use getInstance() to get the instance of the SessionMemberContainer.
     */
    private SessionMemberContainer() {
    	container = new ArrayList<>();
    }

    /**
     * Gets the instance of the SessionMemberContainer.
     *
     * @return The instance of the SessionMemberContainer.
     */
    public static SessionMemberContainer getInstance() {
        if (instance == null) {
            instance = new SessionMemberContainer();
        }
        return instance;
    }

    /**
     * Creates a new SessionMemberContainer in the container.
     * @param obj The SessionMemberContainer to be created.
     *
     * @return True if the SessionMemberContainer was created, false otherwise.
     */
    @Override
    public boolean create(SessionMember obj) {
        return container.add(obj);
    }

    /**
     * Gets a SessionMemberContainer from the container.
     *
     * @param ssn The ssn of the SessionMember to be retrieved.
     * @param sessionID The id of the Session.
     * @return The SessionMember with the given ssn and from the given session id.
     */
    @Override
    public SessionMember getSessionMember(long ssn, long sessionID) {
        SessionMember sessionMember = null;
        for (int i = 0; i < container.size() && sessionMember == null; i++) {
            if (container.get(i).getPerson().getSsn() == ssn && container.get(i).getSession().getSessionID() == sessionID) {
                sessionMember = container.get(i);
            }
        }
        return sessionMember;
    }

    /**
     * Gets all SessionMembers from the container.
     *
     * @return A list of all SessionMembers in the container.
     */
    @Override
    public List<SessionMember> getAll() {
        return container;
    }

    /**
     * Updates a SessionMembers in the container.
     * @param id The ID of the SessionMembers to be updated.
     * @param obj The SessionMembers to be updated.
     *
     * @return True if the SessionMembers was updated, false otherwise.
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
     * Deletes a SessionMembers from the container.
     * @param id The ID of the SessionMembers to be deleted.
     *
     * @return True if the SessionMembers was deleted, false otherwise.
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
