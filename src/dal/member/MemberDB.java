package dal.member;

import dal.person.PersonDB;
import model.Member;

import java.sql.SQLException;
import java.util.List;

public class MemberDB implements MemberDataAccessIF {

	@Override
	public boolean create(Member obj) throws SQLException {
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
	public boolean update(long id, Member obj) throws SQLException {
		return false;
	}

	@Override
	public boolean delete(long id) throws SQLException {
		return false;
	}
}
