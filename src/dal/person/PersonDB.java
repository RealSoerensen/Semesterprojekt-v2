package dal.person;

import dal.address.AddressDB;
import dal.DBConnection;
import model.Address;
import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * PersonDB class
 * Implements PersonDataAccessIF interface
 */
public class PersonDB implements PersonDataAccessIF {
	private final Connection connection;

	/**
	* The PersonDB function is a constructor that creates an instance of the PersonDB class.
	* It also establishes a connection to the database using DBConnection.getInstance().getConnection()
	*/
	public PersonDB() throws SQLException {
		connection = DBConnection.getInstance().getConnection();
	}

	/**
	 * The create function takes in a Person object and inserts it into the database.
	 *
	 * @param obj Pass the person object to be inserted into the database
	 * @return A boolean value to indicate whether the person was inserted into the database or not
	 */
	@Override
	public Person create(Person obj) {
		String sql = "INSERT INTO Person (firstName, lastName, email, ssn, role, phoneNo, password, addressID) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, obj.getFirstName());
			stmt.setString(2, obj.getLastName());
			stmt.setString(3, obj.getEmail());
			stmt.setLong(4, obj.getSsn());
			stmt.setInt(5, obj.getRole());
			stmt.setString(6, obj.getPhoneNumber());
			stmt.setString(7, obj.getPassword());
			stmt.setLong(8, obj.getAddress().getAddressID());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	* The get function takes in a long id and returns the person with that ssn.
	*
	* @param id Get the person with that id
	*
	* @return A person object with the given id
	*/
	@Override
	public Person get(long id) {
		Person person = null;
		try {
			String sql = "SELECT * FROM Person WHERE ssn = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String email = rs.getString("email");
				int role = rs.getInt("role");
				String phoneNo = rs.getString("phoneNo");
				String password = rs.getString("password");
				Address address = getAddress(rs.getLong("addressID"));
				person = new Person(firstName, lastName, address, email, phoneNo, role, password, id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}

	/**
	* The getAll function returns a list of all the people in the database.
	*
	* @return A list of all the people in the database
	*/
 	@Override
	public List<Person> getAll() {
		List<Person> people = new ArrayList<>();
		String sql = "SELECT * FROM Person";
		return getPeople(people, sql);
	}

	private List<Person> getPeople(List<Person> people, String sql) {
		try (Statement stmt = connection.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Person person = createPerson(rs);
				people.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return people;
	}

	/**
	  * The update function updates the person and address tables in the database.
	  *
	  * @param obj Pass the person object to be updated in the database
	  *
	  * @return A boolean value to indicate whether the person was updated in the database or not
	*/
	@Override
	public boolean update(Person obj) throws SQLException {
		boolean result;
		String sql = "UPDATE Person SET firstName = ?, lastName = ?, email = ?, phoneNo = ?, addressId = ?, role = ? WHERE ssn = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, obj.getFirstName());
			stmt.setString(2, obj.getLastName());
			stmt.setString(3, obj.getEmail());
			stmt.setString(4, obj.getPhoneNumber());
			stmt.setLong(5, obj.getAddress().getAddressID());
			stmt.setInt(6, obj.getRole());
			stmt.setLong(7, obj.getSsn());
			result = stmt.executeUpdate() > 0;
		}
		return result;
	}

	/**
	  * The delete function deletes a person from the database.
	  *
	  * @param obj Identify the person to be deleted
	  *
	  * @return True or false depending on whether the person was deleted or not
	*/
 	@Override
	public boolean delete(Person obj) {
		boolean result = false;
		String sql = " DELETE FROM Person WHERE ssn = ? ";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, obj.getSsn());
			result = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	* The getAddress function takes in a long addressID and returns an Address object.
	*
	* @param addressID Get the addressId from the database
	*
	* @return An address object with the values from the database
	*/
	private Address getAddress(long addressID) throws SQLException {
		AddressDB addressDB = new AddressDB();
		return addressDB.get(addressID);
	}

	@Override
	public boolean isSsnUnique(long ssn) {
		List<Person> allPeople = getAll();
		return allPeople.stream().noneMatch(person -> person.getSsn() == ssn);
	}

	@Override
	public Person login(long ssn, String password) {
		Person person = null;
		String sql = "SELECT * FROM Person WHERE ssn = ? AND password = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, ssn);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				person = createPerson(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}

	@Override
	public List<Person> getAllMembers() {
		String sql = "SELECT * FROM Person WHERE role = 1";
		List<Person> members = new ArrayList<>();
		return getPeople(members, sql);
	}

	@Override
	public List<Person> getAllInstructors() {
		String sql = "SELECT * FROM Person WHERE role = 2";
		List<Person> instructors = new ArrayList<>();
		return getPeople(instructors, sql);
	}

	@Override
	public List<Person> getAllAdmins() {
	String sql = "SELECT * FROM Person WHERE role = 3";
		List<Person> admins = new ArrayList<>();
		return getPeople(admins, sql);
	}

	private Person createPerson(ResultSet rs) throws SQLException {
		long ssn = rs.getLong("ssn");
		String firstName = rs.getString("firstName");
		String lastName = rs.getString("lastName");
		String email = rs.getString("email");
		int role = rs.getInt("role");
		String phoneNo = rs.getString("phoneNo");
		String password = rs.getString("password");
		Address address = getAddress(rs.getLong("addressID"));
		return new Person(firstName, lastName, address, email, phoneNo, role, password, ssn);
	}
}
