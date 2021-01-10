package by.training.testing.service;

import by.training.testing.service.exception.ServiceException;
import by.training.testing.bean.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getSubjects() throws ServiceException;
    boolean addSubject(String name) throws ServiceException;
    boolean editSubject(int id, String name) throws ServiceException;
    boolean deleteSubject(int id) throws ServiceException;
}
