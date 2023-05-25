package dal.subject;

import dal.DBConnection;
import model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*

 */
public class SubjectDB implements SubjectDataAccessIF {
    private final Connection connection;

    /**
     * The SubjectDB function is a constructor that creates a connection to the database.
     */
    public SubjectDB() throws SQLException {
        connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public Subject create(Subject obj) {
        String sql = "INSERT INTO Subject(name, description) VALUES(?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, obj.getName());
            stmt.setString(2, obj.getDescription());
            stmt.executeUpdate();
            ResultSet subjectRS = stmt.getGeneratedKeys();
            if (subjectRS.next()) {
                obj.setSubjectID(subjectRS.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }


    /**
     * The get function takes in a long id and returns the subject with that id.
     *
     * @param id Identify the subject that is being updated
     *
     * @return The subject object with the given id
     */
    @Override
    public Subject get(long id) throws SQLException {
        Subject subject = null;
        String sql = " SELECT * FROM Subject WHERE subjectID = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeQuery();
            ResultSet subjectRS = stmt.getResultSet();
            if (subjectRS.next()) {
                String name = subjectRS.getString("name");
                String description = subjectRS.getString("description");
                subject = new Subject(id, name, description);
            }
        }
        return subject;
    }

    /**
     * The getAll function returns a list of all subjects in the database.
     *
     * @return A list of all the subjects in the database
     */
    @Override
    public List<Subject> getAll() {
        List<Subject> subjects = new ArrayList<>();
        String sql = " SELECT * FROM Subject ";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeQuery();
            ResultSet subjectRS = stmt.getResultSet();
            while (subjectRS.next()) {
                long subjectID = subjectRS.getLong("subjectID");
                String name = subjectRS.getString("name");
                String description = subjectRS.getString("description");
                Subject subject = new Subject(subjectID, name, description);
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

    /**
     * The update function updates the name and description of a subject in the database.
     *
     * @param obj Pass the subject object to be updated in the database
     *
     * @return A boolean value to indicate whether the subject was updated or not
     */
    @Override
    public boolean update(Subject obj) {
        boolean updated = false;
        String sql = " UPDATE Subject SET name = ?, description = ? WHERE subjectID = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, obj.getName());
            stmt.setString(2, obj.getDescription());
            stmt.setLong(3, obj.getSubjectID());
            updated = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    /**
     * The delete function deletes a subject from the database.
     *
     * @param obj Pass the subject object to be deleted from the database
     *
     * @return True or false depending on whether the deletion was successful or not
     */
    @Override
    public boolean delete(Subject obj) {
        boolean deleted = false;
        String sql = " DELETE FROM Subject WHERE subjectID = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, obj.getSubjectID());
            deleted = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deleted;
    }
}
