package dal.administrator;

import dal.DBConnection;
import dal.person.PersonDB;
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
    private PersonDataAccessIF personDB = new PersonDB();

    public AdminDB() throws SQLException {
        DBConnection dbConnection = DBConnection.getInstance();
        connection = dbConnection.getConnection();
    }

    @Override
    public boolean create(Administrator obj) throws SQLException {
        String sql = "INSERT INTO administrator (ssn) VALUES (?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, obj.getSsn());
        return stmt.executeUpdate() > 0;
    }

    @Override
    public Administrator get(long id) throws SQLException {
        Administrator administrator = null;
        String sql = "SELECT * FROM administrator WHERE ssn = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        ResultSet administratorRS = stmt.executeQuery();
        if (administratorRS.next()) {
            administrator = new Administrator(getPerson(id), id);
        }
        return administrator;
    }

    @Override
    public List<Administrator> getAll() throws SQLException {
        String sql = "SELECT * FROM administrator";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet administratorRS = stmt.executeQuery();
        List<Administrator> administrators = new ArrayList<>();
        while (administratorRS.next()) {
            long ssn = administratorRS.getLong("ssn");
            administrators.add(new Administrator(getPerson(ssn), ssn));
        }
        return administrators;
    }

    @Override
    public boolean update(long id, Administrator obj) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(long id) throws SQLException {
        String sql = "DELETE FROM administrator WHERE ssn = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        return stmt.executeUpdate() > 0;
    }

    private Person getPerson(long ssn) throws SQLException {
        return personDB.get(ssn);
    }
}
