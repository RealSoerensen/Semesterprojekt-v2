package dal.address;

import dal.DBConnection;
import model.Address;
import model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDB implements AddressDataAccessIF {
    private final Connection connection;

    /**
     * Constructor for AddressDB class.
     */
    public AddressDB() throws SQLException {
        connection = DBConnection.getInstance().getConnection();
    }

    /**
     * Creates a new Address in the database.
     *
     * @param obj The Address to be created.
     * @return True if the Address was created successfully, false otherwise.
     */
    @Override
    public Address create(Address obj) throws SQLException {
        Address address = null;
        String sql = " INSERT INTO address (street, city, zipCode, houseNumber) VALUES (?, ?, ?, ?) ";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, obj.getStreet());
            stmt.setString(2, obj.getCity());
            stmt.setString(3, obj.getZipCode());
            stmt.setString(4, obj.getHouseNumber());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                address = new Address(
                        rs.getLong(1),
                        obj.getZipCode(),
                        obj.getCity(),
                        obj.getStreet(),
                        obj.getHouseNumber()
                );
            }
        }
        return address;
    }

    /**
     * Gets an Address from the database.
     *
     * @param id The id of the Address to be retrieved.
     * @return The Address with the given id.
     */
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
                        addressRS.getString("zipCode"),
                        addressRS.getString("city"),
                        addressRS.getString("street"),
                        addressRS.getString("houseNumber"));
            }
        }
        return address;
    }

    /**
     * Gets all Addresses from the database.
     *
     * @return A list of all Addresses.
     */
    @Override
    public List<Address> getAll() throws SQLException {
        List<Address> addresses = new ArrayList<>();
        String sql = " SELECT * FROM address ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.executeQuery();
            ResultSet addressRS = stmt.getResultSet();
            while (addressRS.next()) {
                addresses.add(new Address(
                        addressRS.getLong("addressID"),
                        addressRS.getString("zipCode"),
                        addressRS.getString("city"),
                        addressRS.getString("street"),
                        addressRS.getString("houseNumber")
                ));
            }
        }
        return addresses;
    }

    /**
     * Updates an Address in the database.
     *
     * @param obj The Address to be updated.
     * @return True if the Address was updated successfully, false otherwise.
     */
    @Override
    public boolean update(Address obj) {
        boolean result = false;
        String sql = "UPDATE address SET zipCode = ?, city = ?, houseNumber = ?, street = ?" +
                " WHERE addressID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, obj.getZipCode());
            stmt.setString(2, obj.getCity());
            stmt.setString(3, obj.getHouseNumber());
            stmt.setString(4, obj.getStreet());
            stmt.setLong(5, obj.getAddressID());
            result = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Deletes an Address from the database.
     *
     * @param obj The Address to be deleted.
     * @return True if the Address was deleted successfully, false otherwise.
     */
    @Override
    public boolean delete(Address obj) {
        boolean result = false;
        String sql = " DELETE FROM address WHERE addressID = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, obj.getAddressID());
            result = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Creates a new Address in the database and returns the id of the newly created Address.
     *
     * @param address The Address to be created.
     * @return The id of the newly created Address.
     */
    public long createAddressAndGetID(Address address) {
        long id = 0;
        String sql = " INSERT INTO address (street, city, zipCode, houseNumber) VALUES (?, ?, ?, ?) ";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, address.getStreet());
            stmt.setString(2, address.getCity());
            stmt.setString(3, address.getZipCode());
            stmt.setString(4, address.getHouseNumber());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
