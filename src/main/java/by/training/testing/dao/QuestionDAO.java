package by.training.testing.dao;

import by.training.testing.bean.Question;
import by.training.testing.dao.exception.DAOException;

import java.util.List;

public interface QuestionDAO {
    List<Question> getQuestions(int testId) throws DAOException;
    void addQuestion(int testId, String question) throws DAOException;
    void editQuestion(int questionId, String question) throws DAOException;
    void deleteQuestion(int questionId) throws DAOException;
}
