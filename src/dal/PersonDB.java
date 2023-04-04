package dal;

import model.Address;
import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDB implements CRUD<Person>{
	private final DBConnection dbConnection;
	private final Connection connection;

	public PersonDB() throws SQLException {
		dbConnection = DBConnection.getInstance();
		connection = dbConnection.getConnection();
	}

	@Override
    public boolean create(Person obj) {
		boolean result;
		try {
			result = insertPerson(obj);
			if (result) {
				result = insertAddress(obj.getAddress());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return result;
    }

    @Override
    public boolean create(Person obj, long id) throws SQLException {
        return false;
    }

    @Override
    public Person get(long id) throws SQLException {
    	Person person;
    	try {
    		person = findPerson(id);
    	} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    	
        return person;
    }

    @Override
    public List<Person> getAll() throws SQLException {
    	List<Person> people;
    	try {
    		people = findAllPeople(connection);
    	} catch (SQLException e) {
			throw new RuntimeException(e);
		}

        return people;
    }

    @Override
    public boolean update(long id, Person obj) throws SQLException {
    	String sql = "UPDATE Person SET firstName = ?, lastName = ?, email = ?, "
    			+ "phoneNo = ?, username = ?, password = ?, role = ?" +
                " WHERE ssn = ?";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, obj.getFirstName());
			stmt.setString(2, obj.getLastName());
			stmt.setString(3, obj.getEmail());
			stmt.setString(4, obj.getPhoneNumber());
			stmt.setString(5, obj.getUsername());
			stmt.setString(6, obj.getPassword());
			stmt.setInt(7, obj.getRole());
			stmt.setLong(8, id);
			
			return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean delete(long id) throws SQLException {
    	String sql = " DELETE FROM Person WHERE ssn = ? ";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
    
    private List<Person> findAllPeople(Connection connection) throws SQLException {
    	List<Person> people = new ArrayList<>();
    	
    	String sql = "SELECT * FROM Person";
    	try (PreparedStatement stmt = connection.prepareStatement(sql)) {
    		ResultSet rs = stmt.executeQuery();
    		while(rs.next()) {
    			String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				String phoneNo = rs.getString("phoneNo");
				String username = rs.getString("username");
				String password = rs.getString("password");
				int role = rs.getInt("role");
				long ssn = rs.getLong("ssn");
				long addressID = rs.getLong("addressID");
				Address address = findAddress(addressID);
				
				people.add(new Person(firstName, lastName, address, email, phoneNo, role, username, password, ssn));
    		}
    	}
    	return people;
    }
    
    private Person findPerson(long id) throws SQLException {
    	String sql = "SELECT * FROM Person WHERE id = ?";
    	Person person;
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
				Address address = findAddress(addressID);
				
				person = new Person(firstName, lastName, address, email, phoneNo, role, username, password, ssn);
		}
    	return person;
    }
    
    private Address findAddress(long addressID) throws SQLException {
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

	private boolean insertPerson(Person person) throws SQLException {
		String sql = "INSERT INTO Person (firstName, lastName, email, ssn, role, phoneNo, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, person.getFirstName());
			stmt.setString(2, person.getLastName());
			stmt.setString(3, person.getEmail());
			stmt.setLong(4, person.getSsn());
			stmt.setInt(5, person.getRole());
			stmt.setString(6, person.getPhoneNumber());
			stmt.setString(7, person.getUsername());
			stmt.setString(8, person.getPassword());

			return stmt.executeUpdate() > 0;
		}
	}

	private boolean insertAddress(Address address) throws SQLException {
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
