package dal.instructor;

import dal.DBConnection;
import dal.person.PersonContainer;
import dal.person.PersonDataAccessIF;
import dal.subject.SubjectContainer;
import dal.subject.SubjectDataAccessIF;
import model.Instructor;
import model.Person;
import model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorDB implements InstructorDataAccessIF {
    private final Connection connection;


    /**
     * The InstructorDB function is used to create a new InstructorDB object.
     * It also establishes a connection to the database using DBConnection.getInstance().getConnection()
     */
    public InstructorDB() throws SQLException {
        DBConnection dbConnection = DBConnection.getInstance();
        connection = dbConnection.getConnection();
    }

    /**
     * The create function takes an Instructor object and inserts it into the database.
     * @param obj Pass in the instructor object that is being updated
     * @return A boolean value to indicate whether the instructor was inserted into the database or not
     */
    @Override
    public boolean create(Instructor obj) {
        boolean result = false;
        String sql = " INSERT INTO instructor (ssn) VALUES (?) ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, obj.getSsn());
            result = stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * The get function takes in a long id and returns the instructor with that id.
     * @param id Set the ssn of the instructor to be deleted
     * @return An instructor object
     */
    @Override
    public Instructor get(long id) throws SQLException {
        Instructor instructor = null;
        String sql = " SELECT * FROM instructor WHERE ssn = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeQuery();
            ResultSet instructorRS = stmt.getResultSet();
            if (instructorRS.next()) {
                Person person = getPerson(instructorRS.getLong("ssn"));
                instructor = new Instructor(person);
            }
        }
        return instructor;
    }

    /**
     * The getAll function returns a list of all instructors in the database.
     * @return A list of all instructors in the database
     */
    @Override
    public List<Instructor> getAll() {
        List<Instructor> instructors = new ArrayList<>();
        String sql = " SELECT * FROM instructor ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeQuery();
            ResultSet instructorRS = stmt.getResultSet();
            while (instructorRS.next()) {
                Person person = getPerson(instructorRS.getLong("ssn"));
                Instructor instructor = new Instructor(person);
                instructors.add(instructor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return instructors;
    }

    /**
     * The getPerson function takes in a long id and returns the person with that id.
     * @param id Set the ssn of the person to be deleted
     * @param obj Pass in the instructor object that is being updated
     * @return A boolean value to indicate whether the instructor was updated in the database or not
     */
    @Override
    public boolean update(long id, Instructor obj) {
        String sql = " UPDATE instructor SET ssn = ? WHERE ssn = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, obj.getSsn());
            stmt.setLong(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The delete function takes in a long id and deletes the instructor with that id.
     * @param id Set the ssn of the instructor to be deleted
     * @return A boolean value to indicate whether the instructor was deleted from the database or not
     */
    @Override
    public boolean delete(long id) {
        String sql = " DELETE FROM instructor WHERE ssn = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * The getInstructorSubjects function takes in a long id and returns a list of all subjects that the instructor with that id teaches.
     * @param id Set the ssn of the instructor to be deleted
     * @return A list of all subjects that the instructor with that id teaches
     */
    public List<Subject> getInstructorSubjects(long id) {
        List<Subject> subjects = new ArrayList<>();
        String sql = " SELECT * FROM instructorSubjects WHERE ssn = ? ";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet instructorSubjectRS = stmt.executeQuery();
            while (instructorSubjectRS.next()) {
                long subjectId = instructorSubjectRS.getLong("subjectID");
                subjects.add(getSubject(subjectId));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return subjects;
    }

    /**
     * The getSubject function takes in a long id and returns the subject with that id.
     * @param id Set the id of the subject to be deleted
     * @return A subject object
     */
    private Subject getSubject(long id) throws SQLException {
        SubjectDataAccessIF subjectDB = SubjectContainer.getInstance();
        return subjectDB.get(id);
    }

    /**
     * The getPerson function takes in a long id and returns the person with that id.
     * @param ssn Set the ssn of the person to be deleted
     * @return A person object
     */
    private Person getPerson(long ssn) throws SQLException {
        PersonDataAccessIF personDB = PersonContainer.getInstance();
        return personDB.get(ssn);
    }
}
