package dal.member;

import model.Member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberContainer implements MemberDataAccessIF{
    private MemberContainer instance;
    final List<Member> container;

    public MemberContainer() {
    	container = new ArrayList<>();
    }

    public MemberContainer getInstance() {
        if (instance == null) {
            instance = new MemberContainer();
        }
        return instance;
    }

    @Override
    public boolean create(Member obj) {
    	return container.add(obj);
    }

    @Override
    public Member get(long id) {
    	Member member = null;
        for(int i = 0; i < container.size() && member == null; i++) {
            if(container.get(i).getSsn() == id) {
            	member = container.get(i);
            }
        }
        return member;
    }

    @Override
    public List<Member> getAll() {
    	return container;
    }

    @Override
    public boolean update(long id, Member obj) {
    	boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getSsn() == id) {
                container.set(i, obj);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean delete(long id) {
    	boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getSsn() == id) {
                container.remove(i);
                result = true;
            }
        }
        return result;
    }
}
