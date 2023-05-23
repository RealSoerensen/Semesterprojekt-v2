package dal.address;

import dal.CRUD;
import model.Address;

public interface AddressDataAccessIF extends CRUD<Address> {
    long createAddressAndGetID(Address address) throws Exception;
}
