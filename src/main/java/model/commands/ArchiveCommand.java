package model.commands;

import controller.wrappers.RequestWrapper;
import model.instances.ArchiveDTO;
import model.instances.UserDTO;
import model.services.ArchiveService;
import model.services.factory.ServiceFactory;
import model.services.factory.ServiceFactoryImpl;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by lexy on 02.01.17.
 */
public class ArchiveCommand {
    private static final Logger log = Logger.getLogger(String.valueOf(ArchiveCommand.class));

    private ServiceFactory factory = ServiceFactoryImpl.getInstance();
    private ArchiveService archiveService = factory.getArchiveService();

    public String enroll(RequestWrapper request) throws ServletException, IOException {
        try {
            String requestURL = request.getRequestURI();
            int courseId = Integer.parseInt(requestURL.split("/")[3]);
            int studentId = ((UserDTO) request.getSessionAttribute("user")).getId();

            archiveService.enroll(courseId, studentId);
            return "student/student_home.jsp";

        } catch (NumberFormatException | SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

    public String putMark(RequestWrapper request) throws ServletException, IOException {
        try {
            String requestURL = request.getRequestURI();
            int archiveId = Integer.parseInt(requestURL.split("/")[3]);
            int mark = Integer.parseInt(request.getParameter("mark"));

            archiveService.putMark(archiveId, mark);
            request.removeSessionAttribute("archives");
            return "teacher/teacher_home.jsp";

        } catch (IllegalArgumentException | SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

    public String remove(RequestWrapper request) throws ServletException, IOException {
        try {
            String requestURL = request.getRequestURI();
            int archiveId = Integer.parseInt(requestURL.split("/")[3]);

            archiveService.remove(archiveId);
            return "admin/admin_home.jsp";

        } catch (IllegalArgumentException | SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

    public String search(RequestWrapper request) throws ServletException, IOException {
        String name = request.getParameter("name");
        int teacherId = ((UserDTO) request.getSessionAttribute("user")).getId();
        try {
            List<ArchiveDTO> searchedArchives = archiveService.search(name, teacherId);
            request.setSessionAttribute("searched_archive", searchedArchives);
            return "teacher/searched_students.jsp";

        } catch (SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

    public String getResults(RequestWrapper request) throws ServletException, IOException {
        int studentId = ((UserDTO) request.getSessionAttribute("user")).getId();
        try {
            List<ArchiveDTO> archives = archiveService.getResults(studentId);
            request.setSessionAttribute("archive", archives);
            return "student/results.jsp";

        } catch (SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

    public String getArchivesForTeacher(RequestWrapper request) throws ServletException, IOException {
        int teacherId = ((UserDTO) request.getSessionAttribute("user")).getId();
        try {
            List<ArchiveDTO> archives = archiveService.getArchiveForTeacher(teacherId);
            request.setSessionAttribute("archives", archives);
            return "teacher/teacher_home.jsp";

        } catch (SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

    public String getArchivesForCategory(RequestWrapper request) throws ServletException, IOException {
        try {
            String requestURL = request.getRequestURI();
            int categoryId = Integer.parseInt(requestURL.split("/")[3]);

            List<ArchiveDTO> archives = archiveService.getArchiveForCategory(categoryId);
            request.setSessionAttribute("archives", archives);
            return "admin/archives.jsp";

        } catch (IllegalArgumentException | SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

    public String getAllArchives(RequestWrapper request) throws ServletException, IOException {
        try {
            List<ArchiveDTO> archives = archiveService.getAllArchives();
            request.setSessionAttribute("archives", archives);
            return "admin/admin_home.jsp";

        } catch (SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }

    public String checkEnrolled(RequestWrapper request) throws ServletException, IOException {
        try {
            String requestURL = request.getRequestURI();
            int courseId = Integer.parseInt(requestURL.split("/")[3]);
            int userId = Integer.parseInt(requestURL.split("/")[4]);

            boolean hasEnrolled = archiveService.hasEnrolled(courseId, userId);
            request.setSessionAttribute("course" + courseId, hasEnrolled);
            return "student/searched_courses.jsp";

        } catch (IllegalArgumentException | SQLException e) {
            log.severe(e.toString());
            request.setSessionAttribute("error", e.toString());
            return "error.jsp";
        }
    }
}
