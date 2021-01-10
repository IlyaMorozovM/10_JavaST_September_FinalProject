package by.training.testing.service;

import by.training.testing.bean.Answer;
import by.training.testing.service.exception.ServiceException;

import java.util.List;

public interface AnswerService {
    List<Answer> getAnswers(int questionId) throws ServiceException;
    boolean addAnswer(int questionId, String answer, boolean isRight) throws ServiceException;
    boolean editAnswer(int answerId, String answer, boolean isRight) throws ServiceException;
    boolean deleteAnswer(int answerId) throws ServiceException;
}
