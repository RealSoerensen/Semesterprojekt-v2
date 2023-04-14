package dal.administrator;

import dal.DBConnection;
import dal.person.PersonContainer;
import dal.person.PersonDataAccessIF;
import model.Administrator;
import model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDB implements AdminDataAccessIF {
    private final Connection connection;

    /**
     * Constructor for AdminDB class.
     */
    public AdminDB() throws SQLException {
        connection = DBConnection.getInstance().getConnection();
    }

    /**
     * Creates a new Administrator in the database.
     *
     * @param obj The Administrator to be created.
     * @return True if the Administrator was created successfully, false otherwise.
     */
    @Override
    public boolean create(Administrator obj) throws SQLException {
        boolean result;
        String sql = "INSERT INTO administrator (ssn) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, obj.getSsn());
            result = stmt.executeUpdate() == 1;
        }
        return result;
    }

    /**
     * Gets an Administrator from the database.
     *
     * @param id The id of the Administrator to be retrieved.
     * @return The Administrator with the given id.
     */
    @Override
    public Administrator get(long id) {
        Administrator administrator = null;
        String sql = "SELECT * FROM administrator WHERE ssn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeQuery();
            ResultSet administratorRS = stmt.getResultSet();
            if (administratorRS.next()) {
                Person person = getPerson(administratorRS.getLong("ssn"));
                administrator = new Administrator(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administrator;
    }

    /**
     * Gets all Administrators from the database.
     *
     * @return A list of all Administrators.
     */
    @Override
    public List<Administrator> getAll() throws SQLException {
        String sql = "SELECT * FROM administrator";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet administratorRS = stmt.executeQuery();
        List<Administrator> administrators = new ArrayList<>();
        while (administratorRS.next()) {
            long ssn = administratorRS.getLong("ssn");
            administrators.add(new Administrator(getPerson(ssn)));
        }
        return administrators;
    }

    /**
     * Updates an Administrator in the database.
     *
     * @param id  The id of the Administrator to be updated.
     * @param obj The Administrator to be updated.
     * @return True if the Administrator was updated successfully, false otherwise.
     */
    @Override
    public boolean update(long id, Administrator obj) {
        boolean result = false;
        String sql = "UPDATE administrator SET ssn = ? WHERE ssn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, obj.getSsn());
            stmt.setLong(2, id);
            result = stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Deletes an Administrator from the database.
     *
     * @param id The id of the Administrator to be deleted.
     * @return True if the Administrator was deleted successfully, false otherwise.
     */
    @Override
    public boolean delete(long id) {
        boolean result = false;
        String sql = "DELETE FROM administrator WHERE ssn = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            result = stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Gets a Person from the database.
     *
     * @param ssn The ssn of the Person to be retrieved.
     * @return The Person with the given ssn.
     */
    private Person getPerson(long ssn) throws SQLException {
        PersonDataAccessIF personDB = PersonContainer.getInstance();
        return personDB.get(ssn);
    }
}
