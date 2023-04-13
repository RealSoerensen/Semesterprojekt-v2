package dal.administrator;

import model.Administrator;

import java.util.ArrayList;
import java.util.List;

public class AdminContainer implements AdminDataAccessIF{
    final List<Administrator> container;
    AdminContainer instance;

    /**
     * Constructor for AdminContainer class.
     * Initializes the container.
     * Private to prevent instantiation.
     * Use getInstance() to get the instance of the AdminContainer.
     */
    private AdminContainer() {
        container = new ArrayList<>();
    }

    /**
     * Gets the instance of the AdminContainer.
     *
     * @return The instance of the AdminContainer.
     */
    public AdminContainer getInstance() {
        if (instance == null) {
            instance = new AdminContainer();
        }
        return instance;
    }

    /**
     * Creates a new Administrator in the container.
     * @param obj The Administrator to be created.
     *
     * @return True if the Administrator was created, false otherwise.
     */
    @Override
    public boolean create(Administrator obj) {
        return container.add(obj);
    }

    /**
     * Gets a Administrator from the container.
     * @param id The ID of the Administrator to be retrieved.
     *
     * @return The Administrator with the given ID, null if no Administrator with the given ID was found.
     */
    @Override
    public Administrator get(long id) {
    	Administrator administrator = null;
        for(int i = 0; i < container.size() && administrator == null; i++) {
            if(container.get(i).getSsn() == id) {
            	administrator = container.get(i);
            }
        }
        return administrator;
    }

    /**
     * Gets all Administrators from the container.
     *
     * @return A list of all Administrators in the container.
     */
    @Override
    public List<Administrator> getAll() {
    	return container;
    }

    /**
     * Updates an Administrator in the container.
     * @param id The ID of the Administrator to be updated.
     * @param obj The Administrator to be updated.
     *
     * @return True if the Administrator was updated, false otherwise.
     */
    @Override
    public boolean update(long id, Administrator obj) {
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
     * Deletes an Administrator from the container.
     * @param id The ID of the Administrator to be deleted.
     *
     * @return True if the Administrator was deleted, false otherwise.
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
