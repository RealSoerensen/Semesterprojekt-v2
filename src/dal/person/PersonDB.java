package dal.person;

import dal.address.AddressDB;
import dal.DBConnection;
import model.Address;
import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDB implements PersonDataAccessIF {
	private final Connection connection;

	public PersonDB() throws SQLException {
		DBConnection dbConnection = DBConnection.getInstance();
		connection = dbConnection.getConnection();
	}

	@Override
	public boolean create(Person obj) {
		boolean result;
		try {
			result = insertPerson(obj);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return result;
	}

	@Override
	public Person get(long id) {
		Person person;
		try {
			String sql = "SELECT * FROM Person WHERE ssn = " + id;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			person = createPersonFromRS(rs);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return person;
	}

	@Override
	public List<Person> getAll() {
		List<Person> people = new ArrayList<>();
		try {
			String sql = "SELECT * FROM Person";
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Person person = createPersonFromRS(rs);
					people.add(person);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return people;
	}

	@Override
	public boolean update(long id, Person obj) throws SQLException {
		boolean result;
		result = updatePerson(id, obj);
		if (result) {
			result = updateAddress(id, obj);
		}

		return result;
	}

	@Override
	public boolean delete(long id) throws SQLException {
		String sql = " DELETE FROM Person WHERE ssn = ? ";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, id);
			return stmt.executeUpdate() > 0;
		}
	}

	private boolean updatePerson(long id, Person obj) throws SQLException {
		String sql = "UPDATE Person SET firstName = ?, lastName = ?, email = ?, "
				+ "phoneNo = ?, username = ?, password = ?, role = ?" +
				" WHERE ssn = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
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

	private boolean updateAddress(long id, Person obj) throws SQLException {
		boolean result = false;
		String sql = "SELECT addressID FROM Person WHERE ssn = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery(); // Finds the address ID from the person ssn
			if (rs.next()) {
				long addressID = rs.getLong("addressID");
				result = new AddressDB().update(addressID, obj.getAddress());
			}
		}
		return result;
	}

	private Person createPersonFromRS(ResultSet rs) throws SQLException {
		Person person = null;
		if (rs.next()) {
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String email = rs.getString("email");
			String phoneNo = rs.getString("phoneNo");
			String username = rs.getString("username");
			String password = rs.getString("password");
			int role = rs.getInt("role");
			long ssn = rs.getLong("ssn");
			long addressID = rs.getLong("addressID");

			AddressDB addressDB = new AddressDB();
			Address address = addressDB.get(addressID);

			person = new Person(firstName, lastName, address, email, phoneNo, role, username, password, ssn);
		}

		return person;
	}

	private boolean insertPerson(Person person) throws SQLException {
		long id = createAddress(person.getAddress());

		String sql = "INSERT INTO Person (firstName, lastName, email, ssn, role, phoneNo, username, password, addressID) "
				+
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, person.getFirstName());
			stmt.setString(2, person.getLastName());
			stmt.setString(3, person.getEmail());
			stmt.setLong(4, person.getSsn());
			stmt.setInt(5, person.getRole());
			stmt.setString(6, person.getPhoneNumber());
			stmt.setString(7, person.getUsername());
			stmt.setString(8, person.getPassword());
			stmt.setLong(9, id);

			return stmt.executeUpdate() > 0;
		}
	}

	private Address getAddress(long addressID) throws SQLException {
		AddressDB addressDB = new AddressDB();
		return addressDB.get(addressID);
	}

	private long createAddress(Address address) throws SQLException {
		AddressDB addressDB = new AddressDB();
		return addressDB.createAddressAndGetID(address);
	}
}
