package dal.address;

import model.Address;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class AddressContainer implements AddressDataAccessIF{
    private static AddressContainer instance;
    private final List<Address> container;

    private AddressContainer() {
        container = new ArrayList<>();
    }

    public static AddressContainer getInstance() {
        if(instance == null) {
            instance = new AddressContainer();
        }
        return instance;
    }

    @Override
    public boolean create(Address obj) {
        return container.add(obj);
    }

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

    @Override
    public List<Address> getAll() {
        return container;
    }

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
