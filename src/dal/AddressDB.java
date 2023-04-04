package dal;

import model.Address;

import java.sql.*;
import java.util.List;

public class AddressDB implements CRUD<Address>{
    private final Connection connection;

    public AddressDB() throws SQLException {
        DBConnection dbConnection = DBConnection.getInstance();
        connection = dbConnection.getConnection();
    }

    @Override
    public boolean create(Address obj) throws SQLException {
        PreparedStatement stmt = insertAddress(obj);
        return stmt.executeUpdate() > 0;
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

    public long getLongFromCreatedAddress(Address address) {
        long id;
        Statement stmt = insertAddress(address);
        try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                id = generatedKeys.getLong(1);
            } else {
                throw new SQLException("Creating address failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    private PreparedStatement insertAddress(Address address) {
        String sql = " INSERT INTO address (houseNumber, street, city, zipCode) VALUES (?, ?, ?, ?) ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, address.getHouseNumber());
            stmt.setString(2, address.getStreet());
            stmt.setString(3, address.getCity());
            stmt.setString(4, address.getZipCode());
            stmt.executeUpdate();
            return stmt;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
