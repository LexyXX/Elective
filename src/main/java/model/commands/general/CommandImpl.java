package model.commands.general;

import controller.wrappers.RequestWrapper;
import controller.wrappers.ResponseWrapper;
import model.commands.ArchiveCommand;
import model.commands.CourseCommand;
import model.commands.UserCommand;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by lexy on 08.12.16.
 */
public class CommandImpl implements ICommand {
    private static CommandImpl instance = null;

    private UserCommand userCommand = new UserCommand();
    private CourseCommand courseCommand = new CourseCommand();
    private ArchiveCommand archiveCommand = new ArchiveCommand();


    private CommandImpl() {
    }

    public synchronized static CommandImpl getInstance() {
        if (instance == null) {
            instance = new CommandImpl();
        }
        return instance;
    }


    @Override
    public void execute(String command, RequestWrapper request, ResponseWrapper response)
            throws ServletException, IOException {

        String page = null;

        switch (command) {
            case "Login":
                page = userCommand.verifyLoginCredentials(request);
                break;
            case "Register":
                page = userCommand.register(request);
                break;
            case "ChangeMyInfo":
                page = userCommand.changeMyInfo(request);
                break;
            case "Enroll":
                page = archiveCommand.enroll(request);
                break;
            case "SearchCourse":
                page = courseCommand.search(request);
                break;
            case "PutMark":
                page = archiveCommand.putMark(request);
                break;
            case "SearchStudent":
                page = archiveCommand.search(request);
                break;
            case "ChangeCourse":
                page = courseCommand.updateCourse(request);
                break;
            case "ChangeUser":
                page = userCommand.changeUser(request);
                break;
            case "RemoveRecord":
                page = archiveCommand.remove(request);
                break;
            case "AddCourse":
                page = courseCommand.add(request);
                break;
            case "Registration":
                page = userCommand.goToRegistrationPage();
                break;
            case "Logout":
                page = userCommand.logout(request);
                break;
            case "MyInfo":
                page = userCommand.goToMyPage(request);
                break;
            case "StudentHome":
                page = userCommand.goToStudentHomePage();
                break;
            case "TeacherHome":
                page = userCommand.goToTeacherHomePage();
                break;
            case "AdminHome":
                page = userCommand.goToAdminHomePage();
                break;
            case "ChosenCourses":
                page = courseCommand.goToChosenCourses(request);
                break;
            case "Results":
                page = archiveCommand.getResults(request);
                break;
            case "AllCourses":
                page = courseCommand.goToCourses();
                break;
            case "GetAllCourses":
                page = courseCommand.getAllCourses(request);
                break;
            case "AllStudents":
                page = userCommand.goToUsers();
                break;
            case "GetArchivesForTeacher":
                page = archiveCommand.getArchivesForTeacher(request);
                break;
            case "GetAllUsers":
                page = userCommand.getAllUsers(request);
                break;
            case "GetAllArchives":
                page = archiveCommand.getAllArchives(request);
                break;
            case "GetAllTeachers":
                page = userCommand.getAllTeachers(request);
                break;
            case "CheckEnrolled":
                page = archiveCommand.checkEnrolled(request);
                break;
            case "GetAllCategories":
                page = courseCommand.getAllCategories(request);
                break;
            case "CoursesForCategory":
                page = courseCommand.getCoursesForCategory(request);
                break;
            case "ArchivesForCategory":
                page = archiveCommand.getArchivesForCategory(request);
                break;
        }

        if (page != null)
            response.sendRedirect(page);
    }

}
