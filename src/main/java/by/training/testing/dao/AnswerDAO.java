package by.training.testing.dao;

import by.training.testing.bean.Answer;
import by.training.testing.dao.exception.DAOException;

import java.util.List;

public interface AnswerDAO {
    List<Answer> getAnswers(int questionId) throws DAOException;
    void addAnswer(int questionId, String answer, boolean isRight) throws DAOException;
    void editAnswer(int answerId, String answer, boolean isRight) throws DAOException;
    void deleteAnswer(int answerId) throws DAOException;
}
