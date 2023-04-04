package dal;

import model.Address;

import java.sql.SQLException;
import java.util.List;

public class AddressDB implements CRUD<Address> {
    @Override
    public boolean create(Address obj) throws SQLException {
        return false;
    }

    @Override
    public boolean create(Address obj, long id) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Address get(long id) throws SQLException {
        return null;
    }

    @Override
    public List<Address> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(long id, Address obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return false;
    }
}
