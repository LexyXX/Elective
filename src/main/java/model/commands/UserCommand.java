package model.commands;

import controller.wrappers.RequestWrapper;
import model.instances.UserDTO;
import model.services.UserService;
import model.services.factory.ServiceFactory;
import model.services.factory.ServiceFactoryImpl;
import org.mindrot.BCrypt;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by lexy on 08.12.16.
 */
public class UserCommand {
    private static final Logger log = Logger.getLogger(String.valueOf(UserCommand.class));

    private ServiceFactory factory = ServiceFactoryImpl.getInstance();
    private UserService userService = factory.getUserService();

    public String verifyLoginCredentials(RequestWrapper request) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            UserDTO user = userService.verify(login);


            if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
                log.info("Login or password are incorrect");
                request.setSessionAttribute("infoMessage", "login_or_password_are_incorrect");
                return "index.jsp";
            }

            if (user.isAdmin()) {
                request.setSessionAttribute("user", user);
                log.info(user.getLogin() + "authorized");
                return "admin/admin_home.jsp";
            }

            if (user.isTeacher()) {
                request.setSessionAttribute("user", user);
                log.info(user.getLogin() + "authorized");
                return "teacher/teacher_home.jsp";
            }

            request.setSessionAttribute("user", user);
            log.info(user.getLogin() + "authorized");
            return "student/student_home.jsp";

        } catch (SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

    public String logout(RequestWrapper request) throws ServletException, IOException {
        request.invalidateSession();
        return "index.jsp";
    }

    public String goToMyPage(RequestWrapper request) throws ServletException, IOException {
        UserDTO user = (UserDTO) request.getSessionAttribute("user");

        if (user.isTeacher()) {
            return "teacher/my_page.jsp";
        }
        if (user.isAdmin()) {
            return "admin/my_page.jsp";
        }
        return "student/my_page.jsp";
    }

    public String goToRegistrationPage() {
        return "student/registration.jsp";
    }

    public String goToStudentHomePage() {
        return "student/student_home.jsp";
    }

    public String goToTeacherHomePage() {
        return "teacher/teacher_home.jsp";
    }

    public String goToAdminHomePage() {
        return "admin/admin_home.jsp";
    }

    public String goToUsers() {
        return "admin/students.jsp";
    }

    public String register(RequestWrapper request) {
        try {
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            userService.add(fname, lname, login, password);
            return "index.jsp";

        } catch (IllegalArgumentException | SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

    public String changeMyInfo(RequestWrapper request) throws ServletException, IOException {
        try {
            UserDTO user = (UserDTO) request.getSessionAttribute("user");
            int id = user.getId();
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            user = userService.changeMyInfo(id, fname, lname, login, password, user.getPassword());
            request.setSessionAttribute("user", user);

            if (user.isTeacher()) {
                return "teacher/my_page.jsp";
            }
            if (user.isAdmin()) {
                return "admin/my_page.jsp";
            }
            return "student/my_page.jsp";

        } catch (IllegalArgumentException | SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

    public String changeUser(RequestWrapper request) throws ServletException, IOException {
        try {
            String requestURL = request.getRequestURI();
            int courseId = Integer.parseInt(requestURL.split("/")[3]);

            String isTeacher = request.getParameter("is_teacher");
            String isAdmin = request.getParameter("is_admin");

            userService.updateUserStatus(courseId, isTeacher != null, isAdmin != null);
            request.removeSessionAttribute("users");
            return "admin/students.jsp";

        } catch (IllegalArgumentException | SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

    public String getAllUsers(RequestWrapper request) throws ServletException, IOException {
        try {
            List<UserDTO> users = userService.getAllUsers();
            request.setSessionAttribute("users", users);
            return "admin/students.jsp";

        } catch (SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

    public String getAllTeachers(RequestWrapper request) throws ServletException, IOException {
        try {
            List<UserDTO> teachers = userService.getAllTeachers();
            request.setSessionAttribute("teachers", teachers);
            return "admin/courses.jsp";

        } catch (SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }
}