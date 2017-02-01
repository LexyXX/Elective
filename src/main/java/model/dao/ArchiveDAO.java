package model.dao;

import model.dao.factory.FactoryDAO;
import model.dao.factory.FactoryDAOImpl;
import model.dao.util.DBHelper;
import model.instances.ArchiveDTO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lexy on 02.01.17.
 */
public class ArchiveDAO {
    private final static Logger logger = Logger.getLogger(ArchiveDAO.class);

    public void insert(int courseId, int studentId) {
        try(Connection connection = DBHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("insert.archive"))) {

            statement.setInt(1,courseId);
            statement.setInt(2,studentId);

            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public List<ArchiveDTO> readArchiveByUserName(String name, int teacherId) {
        try(Connection connection = DBHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.archive.by.user.name"))) {

            statement.setString(1, name);
            statement.setString(2, name);
            statement.setInt(3, teacherId);

            ResultSet res = statement.executeQuery();

            FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
            UserDAO userDAO = factoryDAO.getUserDao();
            CourseDAO courseDAO = factoryDAO.getCourseDao();

            List<ArchiveDTO> archives = new ArrayList<>();
            while (res.next()) {
                archives.add(new ArchiveDTO(res.getInt(1), userDAO.read(res.getInt(2)),
                        courseDAO.read(res.getInt(3)), res.getInt(4)));
            }

            return archives;

        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

    public void updateMark(int id, int mark) {
        try(Connection connection = DBHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("update.archive.mark"))) {

            statement.setInt(1,mark);
            statement.setInt(2,id);

            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public void remove(int id) {
        try(Connection connection = DBHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("remove.archive"))) {

            statement.setInt(1,id);
            statement.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public ArchiveDTO read(int courseId, int studentId){
        try(Connection connection = DBHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.archive"))) {

            statement.setInt(1, courseId);
            statement.setInt(2, studentId);

            ResultSet res = statement.executeQuery();

            FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
            UserDAO userDAO = factoryDAO.getUserDao();
            CourseDAO courseDAO = factoryDAO.getCourseDao();

            ArchiveDTO archive = null;
            if (res.next()) {
                archive = new ArchiveDTO(res.getInt(1), userDAO.read(res.getInt(2)),
                        courseDAO.read(res.getInt(3)), res.getInt(4));
            }

            return archive;

        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

    public List<ArchiveDTO> readAllForUserId(int studentId){
        try(Connection connection = DBHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.archive.for.user.id"))) {

            statement.setInt(1, studentId);

            ResultSet res = statement.executeQuery();

            FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
            UserDAO userDAO = factoryDAO.getUserDao();
            CourseDAO courseDAO = factoryDAO.getCourseDao();

            List<ArchiveDTO> archives = new ArrayList<>();
            while (res.next()) {
                archives.add(new ArchiveDTO(res.getInt(1), userDAO.read(res.getInt(2)),
                        courseDAO.read(res.getInt(3)), res.getInt(4)));
            }

            return archives;

        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

    public List<ArchiveDTO> readAllForTeacherId(int teacherId){
        try(Connection connection = DBHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.archive.for.teacher.id"))) {

            statement.setInt(1, teacherId);

            ResultSet res = statement.executeQuery();

            FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
            UserDAO userDAO = factoryDAO.getUserDao();
            CourseDAO courseDAO = factoryDAO.getCourseDao();

            List<ArchiveDTO> archiveDTO = new ArrayList<>();
            while (res.next()) {
                archiveDTO.add(new ArchiveDTO(res.getInt(1), userDAO.read(res.getInt(2)),
                        courseDAO.read(res.getInt(3)), res.getInt(4)));
            }

            logger.info("Archives for teacher were found");

            return archiveDTO;

        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

    public List<ArchiveDTO> readAllForCategoryId(int categoryId){
        try(Connection connection = DBHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.archive.for.category.id"))) {

            statement.setInt(1, categoryId);

            ResultSet res = statement.executeQuery();

            FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
            UserDAO userDAO = factoryDAO.getUserDao();
            CourseDAO courseDAO = factoryDAO.getCourseDao();

            List<ArchiveDTO> archives = new ArrayList<>();
            while (res.next()) {
                archives.add(new ArchiveDTO(res.getInt(1), userDAO.read(res.getInt(2)),
                        courseDAO.read(res.getInt(3)), res.getInt(4)));
            }


            logger.info("Archives for category were found");

            return archives;

        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }

    public List<ArchiveDTO> readAll(){
        try(Connection connection = DBHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(DBHelper.getQuery("find.all.archives"))) {

            ResultSet res = statement.executeQuery();

            FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
            UserDAO userDAO = factoryDAO.getUserDao();
            CourseDAO courseDAO = factoryDAO.getCourseDao();

            List<ArchiveDTO> archiveDTO = new ArrayList<>();
            while (res.next()) {
                archiveDTO.add(new ArchiveDTO(res.getInt(1), userDAO.read(res.getInt(2)),
                        courseDAO.read(res.getInt(3)), res.getInt(4)));
            }

            logger.info("All archives were found");

            return archiveDTO;

        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }
}
