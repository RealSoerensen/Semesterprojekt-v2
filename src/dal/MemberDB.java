package dal;

import model.Address;
import model.Member;

import java.sql.*;
import java.util.List;

public class MemberDB implements CRUD<Member>{
	private final DBConnection dbConnection;
	private final Connection connection;

	public MemberDB() throws SQLException {
		dbConnection = DBConnection.getInstance();
		connection = dbConnection.getConnection();
	}

	@Override
    public boolean create(Member obj) {
		boolean result;
		try {
			result = insertPerson(obj);
			if (result) {
				result = insertAddress(connection, obj.getAddress());
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return result;
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

	private boolean insertPerson(Member member) throws SQLException {
		String sql = "INSERT INTO Person (firstName, lastName, email, ssn, role, phoneNo, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, member.getFirstName());
			stmt.setString(2, member.getLastName());
			stmt.setString(3, member.getEmail());
			stmt.setInt(4, member.getSsn());
			stmt.setInt(5, member.getRole());
			stmt.setString(6, member.getPhoneNumber());
			stmt.setString(7, member.getUsername());
			stmt.setString(8, member.getPassword());

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
