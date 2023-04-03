package dal;

import model.Address;
import model.Member;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.List;

public class MemberDB implements CRUD<Member>{
	
    @Override
    public boolean create(Member obj) throws SQLException {
    	
    	String sql = "INSERT INTO Person (firstName, lastName, email, ssn, role, phoneNumber, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    	PreparedStatement stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
    	
    	stmt.setString(1, obj.getFirstName());
    	stmt.setString(2, obj.getLastName());
    	stmt.setString(3, obj.getEmail());
    	stmt.setString(4, String.valueOf(obj.getSsn()));
    	stmt.setString(5, String.valueOf(obj.getRole()));
    	stmt.setString(6, obj.getPhoneNumber());
    	stmt.setString(7, obj.getUsername());
    	stmt.setString(8, obj.getPassword());
    	
    	stmt.executeUpdate();
    	stmt.close();
    	
    	String sql2 = "INSERT INTO Address (zipCode, address, country, street) VALUES (?, ?, ?, ?)";
    	PreparedStatement stmt2 = DBConnection.getInstance().getConnection().prepareStatement(sql2);
    	Address address = obj.getAddress();
    	
    	stmt2.setString(1, address.getZipCode());
    	stmt2.setString(2, address.getHouseNumber());
    	stmt2.setString(3, address.getCity());
    	stmt2.setString(4, address.getStreet());
    	
    	stmt2.executeUpdate();
    	stmt2.close();
    	
        return false;
    }

    @Override
    public Member get(long id) throws SQLException {
        return null;
    }

    @Override
    public List<Member> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(Member obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return false;
    }
}
