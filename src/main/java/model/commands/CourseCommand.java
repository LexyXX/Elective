package model.commands;

import controller.wrappers.RequestWrapper;
import model.exceptions.WrongDatesException;
import model.instances.CategoryDTO;
import model.instances.CourseDTO;
import model.instances.UserDTO;
import model.services.CourseService;
import model.services.factory.ServiceFactory;
import model.services.factory.ServiceFactoryImpl;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by lexy on 02.01.17.
 */
public class CourseCommand {
    private static final Logger log = Logger.getLogger(String.valueOf(CourseCommand.class));

    private ServiceFactory factory = ServiceFactoryImpl.getInstance();
    private CourseService courseService = factory.getCourseService();

    public String search(RequestWrapper request) throws ServletException, IOException {
        String name = request.getParameter("name");
        List<CourseDTO> courseDTOs = courseService.search(name);

        request.setSessionAttribute("searched_courses", courseDTOs);
        return "/view/student/searched_courses.jsp";
    }

    public String getAllCategories(RequestWrapper request) throws ServletException, IOException {
        List<CategoryDTO> categories = courseService.getAllCategories();
        request.setSessionAttribute("categories", categories);

        UserDTO userDTO = (UserDTO) request.getSessionAttribute("user");
        if (userDTO.isAdmin()) {
            return "/view/admin/admin_home.jsp";
        }
        return "/view/student/student_home.jsp";
    }


    public String getCoursesForCategory(RequestWrapper request) throws ServletException, IOException {
        try {
            String requestURL = request.getRequestURI();
            int categoryId = Integer.parseInt(requestURL.split("/")[3]);

            List<CourseDTO> courses = courseService.getCoursesForCategory(categoryId);
            request.setSessionAttribute("searched_courses", courses);

            return "/view/student/searched_courses.jsp";

        } catch (IllegalArgumentException e) {
            log.severe(e.toString());
            return "/view/error.jsp";
        }
    }

    public String getAllCourses(RequestWrapper request) throws ServletException, IOException {
        List<CourseDTO> courses;

        courses = courseService.getAllCourses();
        request.setSessionAttribute("courses", courses);
        return "/view/admin/courses.jsp";
    }

    public String goToCourses() throws ServletException, IOException {
        return "/view/admin/courses.jsp";
    }

    public String updateCourse(RequestWrapper request) throws ServletException, IOException {
        try {
            String requestURL = request.getRequestURI();
            int courseId = Integer.parseInt(requestURL.split("/")[3]);
            String name = request.getParameter("name");
            Date startDate = Date.valueOf(request.getParameter("start_date"));
            Date endDate = Date.valueOf(request.getParameter("end_date"));
            int teacherId = Integer.parseInt(request.getParameter("teacher"));

            courseService.updateCourse(courseId, name, startDate, endDate, teacherId);
            request.removeSessionAttribute("courses");
            return "/view/admin/courses.jsp";

        } catch (IllegalArgumentException e) {
            log.severe(e.toString());
            return "/view/error.jsp";
        }
    }

    public String add(RequestWrapper request) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            Date startDate = Date.valueOf(request.getParameter("start_date"));
            Date endDate = Date.valueOf(request.getParameter("end_date"));
            int teacherId = ((UserDTO) request.getSessionAttribute("user")).getId();

            if (startDate.after(endDate) || startDate.before(new Date(System.currentTimeMillis()))){
                throw new WrongDatesException("Wrong dates");
            }

            courseService.add(name, startDate, endDate, teacherId);
            return "/view/teacher/teacher_home.jsp";

        } catch (IllegalArgumentException | WrongDatesException e) {
            log.severe(e.toString());
            return "/view/error.jsp";
        }
    }

    public String goToChosenCourses(RequestWrapper request) throws ServletException, IOException {
        int studentId = ((UserDTO) request.getSessionAttribute("user")).getId();
        List<CourseDTO> courses = courseService.searchByUserId(studentId);

        request.setSessionAttribute("searched_courses", courses);
        return "/view/student/searched_courses.jsp";
    }

}
