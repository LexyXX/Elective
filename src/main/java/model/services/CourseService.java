package model.services;

import model.dao.CourseDAO;
import model.dao.factory.FactoryDAO;
import model.dao.factory.FactoryDAOImpl;
import model.instances.CategoryDTO;
import model.instances.CourseDTO;

import java.sql.Date;
import java.util.List;

/**
 * Created by lexy on 02.01.17.
 */
public class CourseService {
    private FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
    private CourseDAO courseDao = factoryDAO.getCourseDao();

    public List<CourseDTO> search(String name){
        return courseDao.readByName(name);
    }

    public List<CourseDTO> searchByUserId(int studentId){
        return courseDao.readByUserId(studentId);
    }

    public void updateCourse(int courseId, String name, Date startDate, Date endDate, int teacherId){
        courseDao.update(courseId, name, startDate, endDate, teacherId);
    }

    public void add( String name, Date startDate, Date endDate, int teacherId){
        courseDao.insert(name, startDate, endDate, teacherId);
    }

    public List<CourseDTO> getAllCourses(){
        return courseDao.readAll();
    }

    public List<CourseDTO> getCoursesForCategory(int categoryId){
        return courseDao.readAllForCategory(categoryId);
    }

    public List<CategoryDTO> getAllCategories(){
        return courseDao.readAllCategories();
    }
}
