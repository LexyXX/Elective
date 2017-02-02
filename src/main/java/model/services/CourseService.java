package model.services;

import model.dao.CourseDAO;
import model.dao.factory.FactoryDAO;
import model.dao.factory.FactoryDAOImpl;
import model.instances.CategoryDTO;
import model.instances.CourseDTO;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by lexy on 02.01.17.
 */
public class CourseService {
    private FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
    private CourseDAO courseDao = factoryDAO.getCourseDao();

    public List<CourseDTO> search(String name) throws SQLException {
        return courseDao.readByName(name);
    }

    public List<CourseDTO> searchByUserId(int studentId) throws SQLException {
        return courseDao.readByUserId(studentId);
    }

    public void updateCourse(int courseId, String name, Date startDate, Date endDate, int teacherId) throws SQLException {
        courseDao.update(courseId, name, startDate, endDate, teacherId);
    }

    public void add( String name, Date startDate, Date endDate, int teacherId, int categoryId) throws SQLException {
        courseDao.insert(name, startDate, endDate, teacherId, categoryId);
    }

    public void addCategory(String name, String descr) throws SQLException {
        courseDao.insertCategory(name, descr);
    }

    public List<CourseDTO> getAllCourses() throws SQLException {
        return courseDao.readAll();
    }

    public List<CourseDTO> getCoursesForCategory(int categoryId) throws SQLException {
        return courseDao.readAllForCategory(categoryId);
    }

    public List<CategoryDTO> getAllCategories() throws SQLException {
        return courseDao.readAllCategories();
    }

    public List<CategoryDTO> getCategoriesLimited(int offset, int amount) throws SQLException {
        return courseDao.readCategoriesLimited(offset, amount);
    }
}
