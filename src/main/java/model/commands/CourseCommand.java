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
import java.sql.SQLException;
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
        try {
            List<CourseDTO> courseDTOs = courseService.search(name);
            request.setSessionAttribute("searched_courses", courseDTOs);
            return "student/searched_courses.jsp";

        } catch (SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

    public String getCategoriesLimited(RequestWrapper request) throws ServletException, IOException {
        try {
            int offset = 0;
            UserDTO userDTO = (UserDTO) request.getSessionAttribute("user");

            final int maxOnPage = userDTO.isAdmin() ? 4 : 8;

            String page = request.getParameter("page");
            if (page != null) {
                offset = (Integer.parseInt(page) - 1) * maxOnPage;
            }

            List<CategoryDTO> categories = courseService.getCategoriesLimited(offset, maxOnPage);
            request.setSessionAttribute("categories", categories);


            if (userDTO.isAdmin()) {
                return "admin/admin_home.jsp";
            }

            return "student/student_home.jsp";

        } catch (SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

    public String getAllCategories(RequestWrapper request) throws ServletException, IOException {
        try {
            List<CategoryDTO> categories = courseService.getAllCategories();
            request.setSessionAttribute("categories", categories);
            return "teacher/teacher_home.jsp";

        } catch (SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }


    public String getCoursesForCategory(RequestWrapper request) throws ServletException, IOException {
        try {
            String requestURL = request.getRequestURI();
            int categoryId = Integer.parseInt(requestURL.split("/")[3]);

            List<CourseDTO> courses = courseService.getCoursesForCategory(categoryId);
            request.setSessionAttribute("searched_courses", courses);
            return "student/searched_courses.jsp";

        } catch (IllegalArgumentException | SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

    public String getAllCourses(RequestWrapper request) throws ServletException, IOException {
        try {
            List<CourseDTO> courses = courseService.getAllCourses();
            request.setSessionAttribute("courses", courses);
            return "admin/courses.jsp";

        } catch (SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

    public String goToCourses() throws ServletException, IOException {
        return "admin/courses.jsp";
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
            return "admin/courses.jsp";

        } catch (IllegalArgumentException | SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

    public String add(RequestWrapper request) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            Date startDate = Date.valueOf(request.getParameter("start_date"));
            Date endDate = Date.valueOf(request.getParameter("end_date"));
            int teacherId = ((UserDTO) request.getSessionAttribute("user")).getId();
            int categoryId = Integer.parseInt(request.getParameter("category"));

            if (startDate.after(endDate) || startDate.before(new Date(System.currentTimeMillis()))) {
                throw new WrongDatesException("Wrong dates");
            }

            courseService.add(name, startDate, endDate, teacherId, categoryId);
            return "teacher/teacher_home.jsp";

        } catch (IllegalArgumentException | WrongDatesException | SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }
    public String addCategory(RequestWrapper request) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String descr = request.getParameter("descr");

            courseService.addCategory(name, descr);
            request.removeSessionAttribute("categories");
            return "admin/admin_home.jsp";

        } catch (SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

    public String goToChosenCourses(RequestWrapper request) throws ServletException, IOException {
        int studentId = ((UserDTO) request.getSessionAttribute("user")).getId();
        try {
            List<CourseDTO> courses = courseService.searchByUserId(studentId);
            request.setSessionAttribute("searched_courses", courses);
            return "student/searched_courses.jsp";

        } catch (SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

}
