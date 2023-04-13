package dal.coursessessionmember;

import model.CourseSessionMember;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseSessionMemberContainer implements CourseSessionMemberDataAccessIF{
    private static CourseSessionMemberContainer instance;
    private final List<CourseSessionMember> container;

    /**
     * Constructor for CourseSessionMemberContainer class.
     * Initializes the container.
     * Private to prevent instantiation.
     * Use getInstance() to get the instance of the CourseSessionMemberContainer.
     */
    private CourseSessionMemberContainer() {
    	container = new ArrayList<>();
    }

    /**
     * Gets the instance of the CourseSessionMemberContainer.
     *
     * @return The instance of the CourseSessionMemberContainer.
     */
    public static CourseSessionMemberContainer getInstance() {
        if (instance == null) {
            instance = new CourseSessionMemberContainer();
        }
        return instance;
    }

    /**
     * Creates a new CourseSessionMember in the container.
     * @param obj The CourseSessionMember to be created.
     *
     * @return True if the CourseSessionMember was created, false otherwise.
     */
    @Override
    public boolean create(CourseSessionMember obj) throws SQLException {
        return container.add(obj);
    }

    /**
     * Gets a CourseSessionMember from the container.
     * @param id The ID of the CourseSessionMember to be retrieved.
     *
     * @return The CourseSessionMember with the given ID, null if no CourseSessionMember with the given ID was found.
     */
    @Override
    public CourseSessionMember get(long id) throws SQLException {
        CourseSessionMember courseSessionMember = null;
        for (int i = 0; i < container.size() && courseSessionMember == null; i++) {
            if (container.get(i).getMember().getSsn() == id) {
                courseSessionMember = container.get(i);
            }
        }
        return courseSessionMember;
    }

    /**
     * Gets all CourseSessionMembers from the container.
     *
     * @return A list of all CourseSessionMembers in the container.
     */
    @Override
    public List<CourseSessionMember> getAll() throws SQLException {
        return container;
    }

    /**
     * Updates a CourseSessionMember in the container.
     * @param id The ID of the CourseSessionMember to be updated.
     * @param obj The CourseSessionMember to be updated.
     *
     * @return True if the CourseSessionMember was updated, false otherwise.
     */
    @Override
    public boolean update(long id, CourseSessionMember obj) throws SQLException {
        boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getMember().getSsn() == id) {
                container.set(i, obj);
                result = true;
            }
        }
        return result;
    }

    /**
     * Deletes a CourseSessionMember from the container.
     * @param id The ID of the CourseSessionMember to be deleted.
     *
     * @return True if the CourseSessionMember was deleted, false otherwise.
     */
    @Override
    public boolean delete(long id) throws SQLException {
        boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getMember().getSsn() == id) {
                container.remove(i);
                result = true;
            }
        }
        return result;
    }
}
