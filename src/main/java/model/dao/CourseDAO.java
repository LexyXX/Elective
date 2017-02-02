package model.dao;

import model.dao.factory.FactoryDAO;
import model.dao.factory.FactoryDAOImpl;
import model.dao.util.DBHelper;
import model.instances.CategoryDTO;
import model.instances.CourseDTO;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lexy on 02.01.17.
 */
public class CourseDAO {
    private final static Logger logger = Logger.getLogger(CourseDAO.class);

    public void insert(String name, Date startDate, Date endDate, int teacherId, int categoryId) throws SQLException {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(DBHelper.getQuery("add.course"), Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statement1 =
                     connection.prepareStatement(DBHelper.getQuery("add.course.to.category"))) {

            connection.setAutoCommit(false);

            statement.setString(1, name);
            statement.setDate(2, startDate);
            statement.setDate(3, endDate);
            statement.setInt(4, teacherId);

            statement.executeUpdate();

            //adds the last inserted course to the corresponding category

            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            int courseId = rs.getInt(1);

            statement1.setInt(1, courseId);
            statement1.setInt(2, categoryId);

            statement1.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public void insertCategory(String name, String descr) throws SQLException {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("add.category"))) {

            statement.setString(1, name);
            statement.setString(2, descr);

            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public void update(int courseId, String name, Date startDate, Date endDate, int teacherId) throws SQLException {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("update.course"))) {

            statement.setString(1, name);
            statement.setDate(2, startDate);
            statement.setDate(3, endDate);
            statement.setInt(4, teacherId);
            statement.setInt(5, courseId);

            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public CourseDTO read(int id) throws SQLException {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.course.by.id"))) {

            connection.setAutoCommit(false);

            statement.setInt(1, id);

            FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
            UserDAO userDAO = factoryDAO.getUserDao();

            ResultSet res = statement.executeQuery();

            CourseDTO course = null;
            if (res.next()) {
                course = new CourseDTO(res.getInt(1), res.getString(2),
                        res.getDate(3), res.getDate(4), userDAO.read(res.getInt(5)));
            }

            connection.commit();

            return course;

        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public List<CourseDTO> readByName(String name) throws SQLException {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.all.courses.by.name"))) {

            connection.setAutoCommit(false);

            statement.setString(1, name);

            ResultSet res = statement.executeQuery();

            FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
            UserDAO userDAO = factoryDAO.getUserDao();

            List<CourseDTO> courses = new ArrayList<>();
            while (res.next()) {
                courses.add(new CourseDTO(res.getInt(1), res.getString(2),
                        res.getDate(3), res.getDate(4), userDAO.read(res.getInt(5))));
            }

            connection.commit();

            return courses;

        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public List<CourseDTO> readByUserId(int studentId) throws SQLException {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.all.courses.by.user.id"))) {

            connection.setAutoCommit(false);

            statement.setInt(1, studentId);

            FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
            UserDAO userDAO = factoryDAO.getUserDao();

            ResultSet res = statement.executeQuery();

            List<CourseDTO> courses = new ArrayList<>();
            while (res.next()) {
                courses.add(new CourseDTO(res.getInt(1), res.getString(2),
                        res.getDate(3), res.getDate(4), userDAO.read(res.getInt(5))));
            }

            connection.commit();

            return courses;

        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public List<CourseDTO> readAllForCategory(int categoryId) throws SQLException {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.all.courses.for.category.before.today"))) {

            connection.setAutoCommit(false);

            statement.setInt(1, categoryId);
            ResultSet res = statement.executeQuery();

            FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
            UserDAO userDAO = factoryDAO.getUserDao();

            List<CourseDTO> courses = new ArrayList<>();
            while (res.next()) {
                courses.add(new CourseDTO(res.getInt(1), res.getString(2),
                        res.getDate(3), res.getDate(4), userDAO.read(res.getInt(5))));
            }

            connection.commit();

            return courses;

        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public List<CourseDTO> readAll() throws SQLException {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.all.courses"))) {

            connection.setAutoCommit(false);

            ResultSet res = statement.executeQuery();

            FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
            UserDAO userDAO = factoryDAO.getUserDao();

            List<CourseDTO> courses = new ArrayList<>();
            while (res.next()) {
                courses.add(new CourseDTO(res.getInt(1), res.getString(2),
                        res.getDate(3), res.getDate(4), userDAO.read(res.getInt(5))));
            }

            connection.commit();
            return courses;

        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public List<CategoryDTO> readAllCategories() throws SQLException {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.all.categories"))) {

            ResultSet res = statement.executeQuery();

            List<CategoryDTO> categories = new ArrayList<>();
            while (res.next()) {
                categories.add(new CategoryDTO(res.getInt(1), res.getString(2), res.getString(3)));
            }

            return categories;

        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    public List<CategoryDTO> readCategoriesLimited(int offset, int amount) throws SQLException {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.all.categories.limited"))) {

            statement.setInt(1, offset);
            statement.setInt(2, amount);

            ResultSet res = statement.executeQuery();

            List<CategoryDTO> categories = new ArrayList<>();
            while (res.next()) {
                categories.add(new CategoryDTO(res.getInt(1), res.getString(2), res.getString(3)));
            }

            return categories;

        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }
}
