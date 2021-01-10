package by.training.testing.service;

import by.training.testing.service.exception.ServiceException;
import by.training.testing.bean.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestions(int testId) throws ServiceException;
    boolean addQuestion(int testId, String question) throws ServiceException;
    boolean editQuestion(int questionId, String question) throws ServiceException;
    boolean deleteQuestion(int questionId) throws ServiceException;
}
