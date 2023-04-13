package dal.member;

import model.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberContainer implements MemberDataAccessIF{
    private static MemberContainer instance;
    private final List<Member> container;

    /**
     * The MemberContainer function adds a member to the container.
     */
    public MemberContainer() {
    	container = new ArrayList<>();
    }

    /**
     * The getInstance function is a static function that returns the singleton instance of MemberContainer.
     * If no instance exists, it creates one and then returns it.
     *
     * @return The instance of the class memberContainer
     */
    public static MemberContainer getInstance() {
        if (instance == null) {
            instance = new MemberContainer();
        }
        return instance;
    }

    /**
     * The create function adds a new member to the container.
     *
     * @param obj Add the member to the container
     *
     * @return A boolean, so the test should be:
     */
    @Override
    public boolean create(Member obj) {
    	return container.add(obj);
    }

    /**
     * The get function is used to retrieve a member from the container.
     *
     * @param id Identify the member to be removed
     *
     * @return The member with the specified id
     */
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

    /**
     * The getAll function returns a list of all the members in the container.
     *
     * @return The container list
     */
    @Override
    public List<Member> getAll() {
    	return container;
    }

    /**
     * The update function is used to update a member in the container.
     *
     * @param id Find the object in the container to be deleted
     * @param obj Update the object in the container
     *
     * @return True if the update was successful, false otherwise
     */
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

    /**
     * The delete function is used to delete a member from the container.
     *
     * @param id Find the object in the container to be deleted
     * @return True if the deletion was successful, false otherwise
     */
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
