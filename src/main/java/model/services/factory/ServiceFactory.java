package model.services.factory;

import model.services.ArchiveService;
import model.services.CourseService;
import model.services.UserService;

/**
 * Created by lexy on 10.12.16.
 */
public abstract class ServiceFactory {
    public abstract ArchiveService getArchiveService();
    public abstract CourseService getCourseService();
    public abstract UserService getUserService();
}
