package dal.member;

import dal.DBConnection;
import dal.person.PersonContainer;
import dal.person.PersonDataAccessIF;
import model.Member;
import model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDB implements MemberDataAccessIF {
	private final Connection connection;

	/**
	* The MemberDB function is a constructor that creates an instance of the MemberDB class.
	* It also establishes a connection to the database using DBConnection.getInstance().getConnection()
	*/
	public MemberDB() throws SQLException {
		DBConnection dbConnection = DBConnection.getInstance();
		connection = dbConnection.getConnection();
	}

	/**
	* The create function takes a Member object and inserts it into the database.
	*
	* @param obj Pass in the member object that is being updated
	*
	* @return A boolean value to indicate whether the member was inserted into the database or not
	*/
	@Override
	public boolean create(Member obj) {
		boolean result = false;
		String sql = "INSERT INTO Member (ssn) VALUES (?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, obj.getSsn());
			result = stmt.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	* The get function takes in a long id and returns the member with that id.
	*
	* @param id Set the ssn of the member to be deleted
	*
	* @return A member object
	*/
	@Override
	public Member get(long id) {
		Member member = null;
		String sql = "SELECT * FROM Member WHERE ssn = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, id);
			stmt.executeQuery();
			ResultSet memberRS = stmt.getResultSet();
			if (memberRS.next()) {
				Person person = getPerson(memberRS.getLong("ssn"));
				member = new Member(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}

	/**
	* The getAll function returns a list of all members in the database.
	*
	* @return A list of all members in the database
	*/
	@Override
	public List<Member> getAll() {
		List<Member> members = new ArrayList<>();
		String sql = "SELECT * FROM Member";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.executeQuery();
			ResultSet memberRS = stmt.getResultSet();
			while (memberRS.next()) {
				Person person = getPerson(memberRS.getLong("ssn"));
				Member member = new Member(person);
				members.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}

	/**
	* The update function updates the database with a new member object.
	*
	* @param id Identify the row to be deleted
	* @param obj Update the database with new information
	*
	* @return A boolean value that indicates whether the update was successful
	*/
	@Override
	public boolean update(long id, Member obj) {
		boolean updated = false;
		String sql = "UPDATE Member SET ssn = ? WHERE ssn = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, obj.getSsn());
			stmt.setLong(2, id);
			updated = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return updated;
	}

	/**
	* The delete function deletes a member from the database.
	*
	* @param id Identify the member to be deleted
	*
	* @return A boolean value that indicates whether the deletion was successful
	*/
	@Override
	public boolean delete(long id) {
		boolean deleted = false;
		String sql = "DELETE FROM Member WHERE ssn = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setLong(1, id);
			deleted = stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deleted;
	}

	/**
	* The getPerson function is used to retrieve a person from the database.
	*
	* @param ssn Get the person from the database
	*
	* @return A person with the given ssn from the database
	*/
	private Person getPerson(long ssn) throws SQLException {
		PersonDataAccessIF personDB = PersonContainer.getInstance();
		return personDB.get(ssn);
	}
}
