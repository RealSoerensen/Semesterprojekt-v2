package dal.address;

import model.Address;

import java.util.List;
import java.util.ArrayList;

public class AddressContainer implements AddressDataAccessIF{
    private static AddressContainer instance;
    private final List<Address> container;

    /**
     * Constructor for AddressContainer class.
     * Initializes the container.
     * Private to prevent instantiation.
     * Use getInstance() to get the instance of the AddressContainer.
     */
    private AddressContainer() {
        container = new ArrayList<>();
    }

    public static AddressContainer getInstance() {
        if(instance == null) {
            instance = new AddressContainer();
        }
        return instance;
    }

    /**
     * Creates a new Address in the container.
     * @param obj The Address to be created.
     *
     * @return True if the Address was created, false otherwise.
     */
    @Override
    public boolean create(Address obj) {
        return container.add(obj);
    }

    /**
     * Gets an Address from the container.
     * @param id The ID of the Address to be retrieved.
     *
     * @return The Address with the given ID, null if no Address with the given ID was found.
     */
    @Override
    public Address get(long id) {
        Address address = null;
        for (int i = 0; i < container.size() && address == null; i++) {
            if (container.get(i).getAddressID() == id) {
                address = container.get(i);
            }
        }
        return address;
    }

    /**
     * Gets all Addresses from the container.
     *
     * @return A list of all Addresses in the container.
     */
    @Override
    public List<Address> getAll() {
        return container;
    }

    /**
     * Updates an Address in the container.
     * @param id The ID of the Address to be updated.
     * @param obj The Address to be updated.
     *
     * @return True if the Address was updated, false otherwise.
     */
    @Override
    public boolean update(long id, Address obj) {
        boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getAddressID() == id) {
                container.set(i, obj);
                result = true;
            }
        }
        return result;
    }

    /**
     * Deletes an Address from the container.
     * @param id The ID of the Address to be deleted.
     *
     * @return True if the Address was deleted, false otherwise.
     */
    @Override
    public boolean delete(long id) {
        boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getAddressID() == id) {
                container.remove(i);
                result = true;
            }
        }
        return result;
    }
}
