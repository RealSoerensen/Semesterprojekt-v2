package dal.course;

import dal.DBConnection;
import model.Course;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class CourseDB implements CourseDataAccessIF {
    Connection connection;

    /**
     * Constructor for CourseDB class.
     */
    public CourseDB() throws SQLException {
        connection = DBConnection.getInstance().getConnection();
    }

    /**
     * Creates a new Course in the database.
     *
     * @param obj The Course to be created.
     * @return True if the Course was created successfully, false otherwise.
     */
    @Override
    public boolean create(Course obj) {
        boolean result = false;
        String sql = " INSERT INTO course(name, price, description, startDate, endDate) " +
                " VALUES(?, ?, ?, ?) ";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, obj.getName());
            stmt.setDouble(2, obj.getPrice());
            stmt.setString(3, obj.getDescription());
            stmt.setDate(4, Date.valueOf(obj.getStartDate()));
            stmt.setDate(5, Date.valueOf(obj.getEndDate()));
            result = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Gets a Course from the database.
     *
     * @param id The id of the Course to be retrieved.
     * @return The Course with the given id.
     */
    @Override
    public Course get(long id) throws SQLException {
        String sql = " SELECT * FROM course WHERE courseID = ? ";
        ResultSet courseRS;
        Course course = null;
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            courseRS = stmt.executeQuery();
        }
        if (courseRS.next()) {
            course = new Course(
                    courseRS.getString("name"),
                    courseRS.getDouble("price"),
                    courseRS.getString("description"),
                    courseRS.getDate("startDate").toLocalDate(),
                    courseRS.getDate("endDate").toLocalDate()
            );
        }
        return course;
    }

    /**
     * Gets all Courses from the database.
     *
     * @return A list of all Courses.
     */
    @Override
    public List<Course> getAll() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = " SELECT * FROM course ";
        ResultSet courseRS;
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            courseRS = stmt.executeQuery();
        }
        while(courseRS.next()) {
            Course course = new Course(
                    courseRS.getString("name"),
                    courseRS.getDouble("price"),
                    courseRS.getString("description"),
                    courseRS.getDate("startDate").toLocalDate(),
                    courseRS.getDate("endDate").toLocalDate()
            );
            courses.add(course);
        }
        return courses;
    }

    /**
     * Updates a Course in the database.
     * @param obj The Course to be updated.
     * @return True if the Course was updated successfully, false otherwise.
     */
    @Override
    public boolean update(Course obj) {
        boolean result = false;
        String sql = " UPDATE course SET name = ?, price = ?, description = ?, startDate = ?, endDate = ? " +
                " WHERE courseID = ? ";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, obj.getName());
            stmt.setDouble(2, obj.getPrice());
            stmt.setString(3, obj.getDescription());
            stmt.setDate(4, Date.valueOf(obj.getStartDate()));
            stmt.setDate(5, Date.valueOf(obj.getEndDate()));
            result = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Deletes a Course from the database.
     *
     * @param obj The Course to be deleted.
     * @return True if the Course was deleted successfully, false otherwise.
     */
    @Override
    public boolean delete(Course obj) {
        boolean result = false;
        String sql = " DELETE FROM course WHERE courseID = ? ";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, obj.getCourseID());
            result = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteAll() {
        String sql = " DELETE * FROM course ";
        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
