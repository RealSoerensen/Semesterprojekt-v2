package dal.subject;

import dal.CRUD;
import dal.DBConnection;
import model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SubjectDB implements SubjectDataAccessIF {
    private final Connection connection;

    public SubjectDB() throws SQLException {
        DBConnection dbConnection = DBConnection.getInstance();
        connection = dbConnection.getConnection();
    }

    @Override
    public boolean create(Subject obj) {
        return false;
    }

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
                subject = new Subject(name, description);
            }
        }
        return subject;
    }

    @Override
    public List<Subject> getAll() {
        return null;
    }

    @Override
    public boolean update(long id, Subject obj) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
