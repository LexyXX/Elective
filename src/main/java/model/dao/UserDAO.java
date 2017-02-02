package model.dao;

import model.dao.util.DBHelper;
import model.instances.UserDTO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandra on 21.02.2016.
 */
public class UserDAO {
    private final static Logger logger = Logger.getLogger(UserDAO.class);

    public void insert(String fname, String lname, String login, String password) throws SQLException {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("insert.user"))) {

            statement.setString(1, fname);
            statement.setString(2, lname);
            statement.setString(3, login);
            statement.setString(4, password);

            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public UserDTO update(int id, String fname, String lname, String login, String password) throws SQLException {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("update.user"))) {

            statement.setString(1, fname);
            statement.setString(2, lname);
            statement.setString(3, login);
            statement.setString(4, password);
            statement.setInt(5, id);

            statement.executeUpdate();

            return read(id);

        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public void updateStatus(int id, boolean isTeacher, boolean isAdmin) throws SQLException {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("update.user.status"))) {

            statement.setBoolean(1, isTeacher);
            statement.setBoolean(2, isAdmin);
            statement.setInt(3, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public UserDTO read(int id) throws SQLException {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.user.by.id"))) {

            statement.setInt(1, id);

            ResultSet res = statement.executeQuery();

            UserDTO client = null;
            if (res.next()) {
                client = new UserDTO(res.getInt(1), res.getString(2),
                        res.getString(3), res.getString(4), res.getString(5), res.getBoolean(6), res.getBoolean(7));
            }

            return client;

        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public UserDTO findByLogin(String login) throws SQLException {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.user.by.login"))) {

            statement.setString(1, login);

            ResultSet res = statement.executeQuery();

            UserDTO user = null;
            if (res.next()) {
                user = new UserDTO(res.getInt(1), res.getString(2),
                        res.getString(3), res.getString(4), res.getString(5), res.getBoolean(6), res.getBoolean(7));
            }

            return user;

        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public List<UserDTO> readAllTeachers() throws SQLException {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.all.teachers"))) {

            ResultSet res = statement.executeQuery();

            List<UserDTO> teachers = new ArrayList<>();
            while (res.next()) {
                teachers.add(new UserDTO(res.getInt(1), res.getString(2),
                        res.getString(3), res.getString(4), res.getString(5), res.getBoolean(6), res.getBoolean(7)));
            }

            return teachers;

        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public List<UserDTO> readAll() throws SQLException {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.all.users"))) {

            ResultSet res = statement.executeQuery();

            List<UserDTO> students = new ArrayList<>();
            while (res.next()) {
                students.add(new UserDTO(res.getInt(1), res.getString(2),
                        res.getString(3), res.getString(4), res.getString(5), res.getBoolean(6), res.getBoolean(7)));
            }

            return students;

        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }
}
