package model.dao.factory;

import model.dao.ArchiveDAO;
import model.dao.CourseDAO;
import model.dao.UserDAO;

/**
 * Created by lexy on 08.12.16.
 */
public class FactoryDAOImpl extends FactoryDAO {
    private static FactoryDAOImpl instance = null;
    private UserDAO userDAO = new UserDAO();
    private CourseDAO courseDAO = new CourseDAO();
    private ArchiveDAO archiveDAO = new ArchiveDAO();

    private FactoryDAOImpl() {
    }

    public synchronized static FactoryDAOImpl getInstance() {
        if (instance == null){
            instance = new FactoryDAOImpl();
        }
        return instance;
    }

    @Override
    public UserDAO getUserDao() {
        return userDAO;
    }

    @Override
    public CourseDAO getCourseDao() {
        return courseDAO;
    }

    @Override
    public ArchiveDAO getArchiveDao() {
        return archiveDAO;
    }


}
