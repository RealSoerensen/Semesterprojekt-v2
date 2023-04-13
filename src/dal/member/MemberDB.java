package dal.member;

import model.Member;

import java.util.List;

public class MemberDB implements MemberDataAccessIF {

	@Override
	public boolean create(Member obj) {
		return false;
	}

	@Override
	public Member get(long id) {
		return null;
	}

	@Override
	public List<Member> getAll() {
		return null;
	}

	@Override
	public boolean update(long id, Member obj) {
		return false;
	}

	@Override
	public boolean delete(long id) {
		return false;
	}
}
