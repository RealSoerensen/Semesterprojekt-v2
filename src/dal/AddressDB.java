package dal;

import model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddressDB implements CRUD<Address>{
    private final Connection connection;

    public AddressDB() throws SQLException {
        DBConnection dbConnection = DBConnection.getInstance();
        connection = dbConnection.getConnection();
    }

    @Override
    public boolean create(Address obj) {
        return false;
    }

    @Override
    public boolean create(Address obj, long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Address get(long id) throws SQLException {
        Address address = null;
        String sql = " SELECT * FROM address WHERE addressID = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeQuery();
            ResultSet addressRS = stmt.getResultSet();
            if (addressRS.next()) {
                address = new Address(
                        addressRS.getString("street"),
                        addressRS.getString("city"),
                        addressRS.getString("zipCode"),
                        addressRS.getString("country")
                );
            }
        }
        return address;
    }

    @Override
    public List<Address> getAll() {
        return null;
    }

    @Override
    public boolean update(long id, Address obj) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
