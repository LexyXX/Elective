package model.services;

import model.dao.ArchiveDAO;
import model.dao.factory.FactoryDAO;
import model.dao.factory.FactoryDAOImpl;
import model.instances.ArchiveDTO;

import java.util.List;

/**
 * Created by lexy on 02.01.17.
 */
public class ArchiveService {
    private FactoryDAO factoryDAO = FactoryDAOImpl.getInstance();
    private ArchiveDAO archiveDAO = factoryDAO.getArchiveDao();

    public void enroll(int courseId, int studentId){
        archiveDAO.insert(courseId, studentId);
    }

    public void putMark(int archiveId, int mark){
        archiveDAO.updateMark(archiveId, mark);
    }

    public List<ArchiveDTO> search(String name, int teacherId){
        return archiveDAO.readArchiveByUserName(name, teacherId);
    }

    public List<ArchiveDTO> getResults(int studentId){
        return archiveDAO.readAllForUserId(studentId);
    }

    public void remove(int id){
        archiveDAO.remove(id);
    }

    public boolean hasEnrolled(int courseId, int studentId){
        return archiveDAO.read(courseId, studentId)!=null;
    }

    public List<ArchiveDTO> getArchiveForTeacher(int teacherId){
        return archiveDAO.readAllForTeacherId(teacherId);
    }

    public List<ArchiveDTO> getArchiveForCategory(int categoryId){
        return archiveDAO.readAllForCategoryId(categoryId);
    }

    public List<ArchiveDTO> getAllArchives(){
        return archiveDAO.readAll();
    }


}
