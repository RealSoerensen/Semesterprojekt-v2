package dal.session;

import model.Session;

import java.util.ArrayList;
import java.util.List;

public class SessionContainer implements SessionDataAccessIF {
    private static SessionContainer instance;
    private final List<Session> container;

    /**
     * Constructor for SessionContainer class.
     * Initializes the container.
     * Private to prevent instantiation.
     * Use getInstance() to get the instance of the SessionContainer.
     */
    private SessionContainer() {
        container = new ArrayList<>();
    }

    /**
     * Gets the instance of the SessionContainer.
     *
     * @return The instance of the SessionContainer.
     */
    public static SessionContainer getInstance() {
        if (instance == null) {
            instance = new SessionContainer();
        }
        return instance;
    }

    /**
     * Creates a new Session in the container.
     * 
     * @param obj The Session to be created.
     *
     * @return True if the Session was created, false otherwise.
     */
    @Override
    public boolean create(Session obj) {
        return container.add(obj);
    }

    /**
     * Gets a Session from the container.
     * 
     * @param id The ID of the Session to be retrieved.
     *
     * @return The Session with the given ID, null if no Session with the given ID
     *         was found.
     */
    @Override
    public Session get(long id) {
        Session session = null;
        for (int i = 0; i < container.size() && session == null; i++) {
            if (container.get(i).getSessionID() == id) {
                session = container.get(i);
            }
        }
        return session;
    }

    /**
     * Gets all Session from the container.
     *
     * @return A list of all Session in the container.
     */
    @Override
    public List<Session> getAll() {
        return container;
    }

    /**
     * Updates a Session in the container.
     * 
     * @param id  The ID of the Session to be updated.
     * @param obj The Session to be updated.
     *
     * @return True if the Session was updated, false otherwise.
     */
    @Override
    public boolean update(long id, Session obj) {
        boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getSessionID() == id) {
                container.set(i, obj);
                result = true;
            }
        }
        return result;
    }

    /**
     * Deletes a Session from the container.
     * 
     * @param id The ID of the Session to be deleted.
     *
     * @return True if the Session was deleted, false otherwise.
     */
    @Override
    public boolean delete(long id) {
        boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getSessionID() == id) {
                container.remove(i);
                result = true;
            }
        }
        return result;
    }
}
