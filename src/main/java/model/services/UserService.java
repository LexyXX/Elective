package model.services;

import model.dao.UserDAO;
import model.dao.factory.FactoryDAO;
import model.dao.factory.FactoryDAOImpl;
import model.instances.UserDTO;
import org.mindrot.BCrypt;

import java.util.List;

/**
 * Created by lexy on 04.12.16.
 */
public class UserService {
    private FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
    private UserDAO userDao = factoryDAO.getUserDao();

    public UserDTO verify(String login) {
        return userDao.findByLogin(login);
    }

    public void add(String fname, String lname, String login, String password) {
        password = BCrypt.hashpw(password, BCrypt.gensalt());
        userDao.insert(fname, lname, login, password);
    }

    public UserDTO changeMyInfo(int id, String fname, String lname, String login, String password, String prevPass) {
        if (!password.trim().isEmpty()) {
            password = BCrypt.hashpw(password, BCrypt.gensalt());
            return userDao.update(id, fname, lname, login, password);
        }
        else{
            return userDao.update(id, fname, lname, login, prevPass);
        }
    }

    public void updateUserStatus(int id, boolean isTeacher, boolean isAdmin){
        userDao.updateStatus(id, isTeacher, isAdmin);
    }

    public List<UserDTO> getAllTeachers(){
        FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
        UserDAO userDao = factoryDAO.getUserDao();

        return userDao.readAllTeachers();
    }

    public List<UserDTO> getAllUsers(){
        FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
        UserDAO userDao = factoryDAO.getUserDao();

        return userDao.readAll();
    }
}
