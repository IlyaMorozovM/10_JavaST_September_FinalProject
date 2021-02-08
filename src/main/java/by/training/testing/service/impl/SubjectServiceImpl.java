package by.training.testing.service.impl;

import by.training.testing.dao.SubjectDAO;
import by.training.testing.dao.exception.DAOException;
import by.training.testing.dao.factory.DAOFactory;
import by.training.testing.service.SubjectService;
import by.training.testing.service.exception.ServiceException;
import by.training.testing.bean.Subject;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {

    SubjectDAO subjectDAO;

    public SubjectServiceImpl() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        subjectDAO = daoFactory.getSubjectDao();
    }

    @Override
    public List<Subject> getSubjects() throws ServiceException {
        try {
            return subjectDAO.getSubjects();
        }
        catch (DAOException e) {
            throw new ServiceException("Error while getting subjects", e);
        }
    }

    @Override
    public List<Subject> getSubjectsFromTo(int limit, int offset) throws ServiceException {
        try {
            return subjectDAO.getSubjectsFromTo(limit, offset);
        }
        catch (DAOException e) {
            throw new ServiceException("Error while getting subjects", e);
        }
    }

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
