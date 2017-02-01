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

    public void insert(String name, Date startDate, Date endDate, int teacherId) {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("add.course"))) {

            statement.setString(1, name);
            statement.setDate(2, startDate);
            statement.setDate(3, endDate);
            statement.setInt(4, teacherId);

            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public void update(int courseId, String name, Date startDate, Date endDate, int teacherId) {
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
        }
    }

    public CourseDTO read(int id) {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.course.by.id"))) {

            statement.setInt(1, id);

            FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
            UserDAO userDAO = factoryDAO.getUserDao();

            ResultSet res = statement.executeQuery();

            CourseDTO course = null;
            if (res.next()) {
                course = new CourseDTO(res.getInt(1), res.getString(2),
                        res.getDate(3), res.getDate(4), userDAO.read(res.getInt(5)));
            }

            return course;

        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

    public List<CourseDTO> readByName(String name) {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.all.courses.by.name"))) {
            statement.setString(1, name);

            ResultSet res = statement.executeQuery();

            FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
            UserDAO userDAO = factoryDAO.getUserDao();

            List<CourseDTO> courses = new ArrayList<>();
            while (res.next()) {
                courses.add(new CourseDTO(res.getInt(1), res.getString(2),
                        res.getDate(3), res.getDate(4), userDAO.read(res.getInt(5))));
            }

            return courses;

        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

    public List<CourseDTO> readByUserId(int studentId) {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.all.courses.by.user.id"))) {
            statement.setInt(1, studentId);

            FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
            UserDAO userDAO = factoryDAO.getUserDao();

            ResultSet res = statement.executeQuery();

            List<CourseDTO> courses = new ArrayList<>();
            while (res.next()) {
                courses.add(new CourseDTO(res.getInt(1), res.getString(2),
                        res.getDate(3), res.getDate(4), userDAO.read(res.getInt(5))));
            }

            return courses;

        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

    public List<CourseDTO> readAllForCategory(int categoryId) {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     DBHelper.getQuery("find.all.courses.for.category.before.today"))) {

            statement.setInt(1, categoryId);
            ResultSet res = statement.executeQuery();

            FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
            UserDAO userDAO = factoryDAO.getUserDao();

            List<CourseDTO> courses = new ArrayList<>();
            while (res.next()) {
                courses.add(new CourseDTO(res.getInt(1), res.getString(2),
                        res.getDate(3), res.getDate(4), userDAO.read(res.getInt(5))));
            }

            return courses;

        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

    public List<CourseDTO> readAll() {
        try (Connection connection = DBHelper.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(DBHelper.getQuery("find.all.courses"))) {

            ResultSet res = statement.executeQuery();

            FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
            UserDAO userDAO = factoryDAO.getUserDao();

            List<CourseDTO> courses = new ArrayList<>();
            while (res.next()) {
                courses.add(new CourseDTO(res.getInt(1), res.getString(2),
                        res.getDate(3), res.getDate(4), userDAO.read(res.getInt(5))));
            }

            return courses;

        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

    public List<CategoryDTO> readAllCategories() {
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
            return null;
        }
    }
}
