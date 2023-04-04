package dal;

import model.Address;
import model.Member;

import java.sql.*;
import java.util.List;

public class MemberDB implements CRUD<Member>{
	private final DBConnection dbConnection;

	public MemberDB() throws SQLException {
		dbConnection = DBConnection.getInstance();
	}

	@Override
    public boolean create(Member obj) throws SQLException {
		boolean result;
		try (Connection connection = dbConnection.getConnection()) {
			result = insertPerson(connection, obj);
			if (result) {
				result = insertAddress(connection, obj.getAddress());
			}
		}

		return result;
    }

    @Override
    public Member get(long id) throws SQLException {
    	Member member;
    	try (Connection connection = dbConnection.getConnection()) {
    		member = findMember(connection, id);
    	}
    	
        return member;
    }

    @Override
    public List<Member> getAll() throws SQLException {
    	List<Member> members;
    	try (Connection connection = dbConnection.getConnection()) {
    		members = findAllMembers(connection);
    	}
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
    
    private List<Member> findAllMembers(Connection connection) {
    	
    }
    
    private Member findMember(Connection connection, long id) throws SQLException {
    	String sql = "SELECT * FROM Person WHERE id = ?";
    	Member member;
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setLong(1, id);
				
				ResultSet rs = stmt.executeQuery();
				
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				String phoneNo = rs.getString("phoneNo");
				String username = rs.getString("username");
				String password = rs.getString("password");
				int role = rs.getInt("role");
				long ssn = rs.getLong("ssn");
				long addressID = rs.getLong("addressID");
				Address address = findAddress(connection, addressID);
				
				member = new Member(firstName, lastName, address, email, phoneNo, role, username, password, ssn);
		}
    	return member;
    }
    
    private Address findAddress(Connection connection, long addressID) throws SQLException {
    	String sql = "SELECT * FROM Address WHERE addressID = ?";
    	Address address;
    	try (PreparedStatement stmt = connection.prepareStatement(sql)) {
    		stmt.setLong(1, addressID);
    		
    		ResultSet rs = stmt.executeQuery();
    		
    		String zipCode = rs.getString("zipCode");
    		String houseNumber = rs.getString("address");
    		String city = rs.getString("country");
    		String street = rs.getString("street");
    		
    		address = new Address(zipCode, city, street, houseNumber);
    	}
    	return address;
    }

	private boolean insertPerson(Connection connection, Member member) throws SQLException {
		String sql = "INSERT INTO Person (firstName, lastName, email, ssn, role, phoneNo, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, member.getFirstName());
			stmt.setString(2, member.getLastName());
			stmt.setString(3, member.getEmail());
			stmt.setLong(4, member.getSsn());
			stmt.setInt(5, member.getRole());
			stmt.setString(6, member.getPhoneNumber());
			stmt.setString(7, member.getUsername());
			stmt.setString(8, member.getPassword());

			return stmt.executeUpdate() > 0;
		}
	}

	private boolean insertAddress(Connection connection, Address address) throws SQLException {
		String sql = "INSERT INTO Address (zipCode, address, country, street) VALUES (?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, address.getZipCode());
			stmt.setString(2, address.getHouseNumber());
			stmt.setString(3, address.getCity());
			stmt.setString(4, address.getStreet());

			return stmt.executeUpdate() > 0;
		}
	}
}
