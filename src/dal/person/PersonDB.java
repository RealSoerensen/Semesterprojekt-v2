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
	*
	* @return A boolean value to indicate whether the person was inserted into the database or not
	*/
	@Override
	public boolean create(Person obj) throws SQLException {
		boolean result = false;
		String sql = "INSERT INTO Person (firstName, lastName, email, ssn, role, phoneNo, password, addressID) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, obj.getFirstName());
			stmt.setString(2, obj.getLastName());
			stmt.setString(3, obj.getEmail());
			stmt.setLong(4, obj.getSsn());
			stmt.setString(6, obj.getPhoneNumber());
			stmt.setString(8, obj.getPassword());
			stmt.setLong(9, obj.getAddress().getAddressID());
			result = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
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
			String sql = "SELECT * FROM Person WHERE ssn = " + id;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			person = createPersonFromRS(rs);
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
		String sql = "UPDATE Person SET firstName = ?, lastName = ?, email = ?, phone = ?, addressId = ? WHERE ssn = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, obj.getFirstName());
			stmt.setString(2, obj.getLastName());
			stmt.setString(3, obj.getEmail());
			stmt.setString(4, obj.getPhoneNumber());
			stmt.setLong(5, obj.getAddress().getAddressID());
			stmt.setLong(6, obj.getSsn());
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

	private Person createPersonFromRS(ResultSet rs) throws SQLException {
		Person person = null;
		if (rs.next()) {
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String email = rs.getString("email");
			String phoneNo = rs.getString("phoneNo");
			String password = rs.getString("password");
			int role = rs.getInt("role");
			long ssn = rs.getLong("ssn");
			long addressID = rs.getLong("addressID");
			Address address = getAddress(addressID);

			person = new Person(firstName, lastName, address, email, phoneNo, role, password, ssn);
		}
		return person;
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

	/**
	* The createAddress function takes an Address object as a parameter and creates a new address in the database.
	* It returns the ID of the newly created address.
	*
	* @param address Pass the address object to the createAddress function
	*
	* @return The id of the address that was created in the database
	*/
	private long createAddress(Address address) throws SQLException {
		AddressDB addressDB = new AddressDB();
		return addressDB.createAddressAndGetID(address);
	}

	@Override
	public boolean isSsnUnique(long ssn) {
		List<Person> allPeople = getAll();
		boolean unique = true;
		for(int i = 0; i < allPeople.size() && unique; i++) {
            if(allPeople.get(i).getSsn() == ssn) {
            	unique = false;
            }
        }
		return unique;
	}
}
