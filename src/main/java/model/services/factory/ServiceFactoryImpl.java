package model.services.factory;

import model.services.ArchiveService;
import model.services.CourseService;
import model.services.UserService;

/**
 * Created by lexy on 10.12.16.
 */
public class ServiceFactoryImpl extends ServiceFactory {
    private static ServiceFactoryImpl instance = null;
    private ArchiveService archiveService = new ArchiveService();
    private CourseService courseService = new CourseService();
    private UserService userService = new UserService();

    private ServiceFactoryImpl() {
    }

    public synchronized static ServiceFactoryImpl getInstance() {
        if (instance == null){
            instance = new ServiceFactoryImpl();
        }
        return instance;
    }

    @Override
    public ArchiveService getArchiveService() {
        return archiveService;
    }

    @Override
    public CourseService getCourseService() {
        return courseService;
    }

    @Override
    public UserService getUserService() {
        return userService;
    }
}
