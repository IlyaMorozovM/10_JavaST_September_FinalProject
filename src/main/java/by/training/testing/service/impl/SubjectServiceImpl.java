package by.training.testing.service.impl;

import by.training.testing.dao.SubjectDAO;
import by.training.testing.dao.exception.DAOException;
import by.training.testing.dao.factory.DAOFactory;
import by.training.testing.service.SubjectService;
import by.training.testing.service.exception.ServiceException;
import by.training.testing.bean.Subject;

import java.util.List;

/**
 * This class contains methods, that interacts with entity "subject".
 *
 * @author Ilya Morozov
 * @version	1.0
 * @since	2020-12-14
 */
public class SubjectServiceImpl implements SubjectService {

    SubjectDAO subjectDAO;

    public SubjectServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        subjectDAO = daoFactory.getSubjectDao();
    }

    /**
     * Method that receives all subjects from DB.
     *
     * @return List of all subjects.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public List<Subject> getSubjects() throws ServiceException {
        try {
            return subjectDAO.getSubjects();
        }
        catch (DAOException e) {
            throw new ServiceException("Error while getting subjects", e);
        }
    }

    /**
     * Method that receives subjects from DB.
     *
     * @param limit Number of subjects returned.
     * @param offset Offset of records in the database.
     * @return List of subjects with size = limit.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public List<Subject> getSubjectsFromTo(int limit, int offset) throws ServiceException {
        try {
            return subjectDAO.getSubjectsFromTo(limit, offset);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while getting subjects", e);
        }
    }

    /**
     * Method that inserts subject with some value (name) into DB.
     *
     * @param name Name of subject, that editing.
     * @return True - if operation was successful, otherwise - false.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public boolean addSubject(String name) throws ServiceException {
        if(name.equals(""))
            return false;

        try {
            subjectDAO.addSubject(name);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while adding subject", e);
        }
        return true;
    }

    /**
     * Method that edits subject with some new values (id, name)
     * and inserts into DB.
     *
     * @param id Subject ID, to which subject relate.
     * @param name Name of subject, that editing.
     * @return True - if operation was successful, otherwise - false.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public boolean editSubject(int id, String name) throws ServiceException {
        if(name.equals("") || id == 0)
            return false;

        try {
            subjectDAO.editSubject(id, name);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while editing subject", e);
        }
        return true;
    }

    /**
     * Method that deletes subject by ID.
     *
     * @param id Subject ID, to which subject relate.
     * @return True - if operation was successful, otherwise - false.
     * @throws ServiceException Thrown when DAO exception is caught.
     */
    @Override
    public boolean deleteSubject(int id) throws ServiceException {
        if(id == 0)
            return false;

        try {
            subjectDAO.deleteSubject(id);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while deleting subject", e);
        }
        return true;
    }
}
