package model.dao.factory;

import model.dao.ArchiveDAO;
import model.dao.CourseDAO;
import model.dao.UserDAO;

/**
 * Created by lexy on 08.12.16.
 */
public abstract class FactoryDAO {
    public abstract UserDAO getUserDao();
    public abstract CourseDAO getCourseDao();
    public abstract ArchiveDAO getArchiveDao();
}
