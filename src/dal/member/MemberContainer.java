package dal.member;

import model.Member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberContainer implements MemberDataAccessIF{
    private MemberContainer instance;
    private final List<Member> members;

    public MemberContainer() {
        members = new ArrayList<>();
    }

    public MemberContainer getInstance() {
        if (instance == null) {
            instance = new MemberContainer();
        }
        return instance;
    }

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
