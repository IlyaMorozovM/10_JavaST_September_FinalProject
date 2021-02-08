package by.training.testing.dao;

import by.training.testing.bean.Subject;
import by.training.testing.dao.exception.DAOException;

import java.util.List;

public interface SubjectDAO {
    List<Subject> getSubjects() throws DAOException;
    List<Subject> getSubjectsFromTo(int limit, int offset) throws DAOException;
    void addSubject(String name) throws DAOException;
    void editSubject(int id, String name) throws DAOException;
    void deleteSubject(int id) throws DAOException;
}
