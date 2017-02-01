package model.commands;

import controller.wrappers.RequestWrapper;
import model.instances.UserDTO;
import model.services.UserService;
import model.services.factory.ServiceFactory;
import model.services.factory.ServiceFactoryImpl;
import org.mindrot.BCrypt;

import javax.servlet.ServletException;
import java.io.IOException;
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

        UserDTO user = userService.verify(login);

        if (user == null || !BCrypt.checkpw(password, user.getPassword())) {
            log.info("Login or password are incorrect");
            request.setSessionAttribute("infoMessage", "login_or_password_are_incorrect");
            return "/view/index.jsp";
        }

        if (user.isAdmin()) {
            request.setSessionAttribute("user", user);
            log.info(user.getLogin() + "authorized");
            return "/view/admin/admin_home.jsp";
        }

        if (user.isTeacher()) {
            request.setSessionAttribute("user", user);
            log.info(user.getLogin() + "authorized");
            return "/view/teacher/teacher_home.jsp";
        }

        request.setSessionAttribute("user", user);
        log.info(user.getLogin() + "authorized");
        return "/view/student/student_home.jsp";
    }

    public String logout(RequestWrapper request) throws ServletException, IOException {
        request.invalidateSession();
        return "/view/index.jsp";
    }

    public String goToMyPage(RequestWrapper request) throws ServletException, IOException {
        UserDTO user = (UserDTO) request.getSessionAttribute("user");

        if (user.isTeacher()) {
            return "/view/teacher/my_page.jsp";
        }
        if (user.isAdmin()) {
            return "/view/admin/my_page.jsp";
        }
        return "/view/student/my_page.jsp";
    }

    public String goToRegistrationPage() {
        return "/view/student/registration.jsp";
    }

    public String goToStudentHomePage() {
        return "/view/student/student_home.jsp";
    }

    public String goToTeacherHomePage() {
        return "/view/teacher/teacher_home.jsp";
    }

    public String goToAdminHomePage() {
        return "/view/admin/admin_home.jsp";
    }

    public String goToUsers() {
        return "/view/admin/students.jsp";
    }

    public String register(RequestWrapper request) {
        try {
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            userService.add(fname, lname, login, password);
            return "/view/index.jsp";

        } catch (IllegalArgumentException e) {
            log.severe(e.toString());
            return "/view/error.jsp";
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
                return "/view/teacher/my_page.jsp";
            }
            if (user.isAdmin()) {
                return "/view/admin/my_page.jsp";
            }
            return "/view/student/my_page.jsp";

        } catch (IllegalArgumentException e) {
            log.severe(e.toString());
            return "/view/error.jsp";
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
            return "/view/admin/students.jsp";

        } catch (IllegalArgumentException e) {
            log.severe(e.toString());
            return "/view/error.jsp";
        }
    }

    public String getAllUsers(RequestWrapper request) throws ServletException, IOException {
        List<UserDTO> users = userService.getAllUsers();
        request.setSessionAttribute("users", users);
        return "/view/admin/students.jsp";
    }

    public String getAllTeachers(RequestWrapper request) throws ServletException, IOException {
        List<UserDTO> teachers = userService.getAllTeachers();
        request.setSessionAttribute("teachers", teachers);
        return "/view/admin/courses.jsp";
    }
}