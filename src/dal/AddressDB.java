package dal;

import model.Address;

import java.sql.*;
import java.util.List;

public class AddressDB implements CRUD<Address> {
    private final Connection connection;

    public AddressDB() throws SQLException {
        DBConnection dbConnection = DBConnection.getInstance();
        connection = dbConnection.getConnection();
    }

    @Override
    public boolean create(Address obj) throws SQLException {
        String sql = " INSERT INTO address (houseNumber, street, city, zipCode) VALUES ('" + obj.getHouseNumber() + "', " +
                "'" + obj.getStreet() + "', '" + obj.getCity() + "', '" + obj.getZipCode() + "') ";
        Statement stmt = connection.createStatement();
        return stmt.executeUpdate(sql) > 0;
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
                        addressRS.getLong("addressID"),
                        addressRS.getString("street"),
                        addressRS.getString("city"),
                        addressRS.getString("zipCode"),
                        addressRS.getString("houseNumber")
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

    public long createAddressAndGetID(Address address) throws SQLException {
        String sql = " INSERT INTO address (houseNumber, street, city, zipCode) VALUES ('" + address.getHouseNumber() + "', " +
                "'" + address.getStreet() + "', '" + address.getCity() + "', '" + address.getZipCode() + "') ";
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getLong(1);
        }
        return 0;
    }
}
