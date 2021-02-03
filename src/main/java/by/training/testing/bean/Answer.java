package by.training.testing.bean;

import java.io.Serializable;
import java.util.Objects;

public class Answer implements Serializable {
    private final int answerId;
    private final int questionId;
    private final String answer;
    private final boolean right;

    public Answer(int answerId, int questionId, String answer, boolean isRight) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.answer = answer;
        this.right = isRight;
    }

    public int getAnswerId() {
        return answerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean getRight() {
        return right;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Answer that = (Answer)obj;
        return Objects.equals(answerId, that.answerId) &&
                Objects.equals(questionId, that.questionId) &&
                Objects.equals(answer, that.answer) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId, questionId, answer, right);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId='" + answerId + '\'' +
                ", questionId='" + questionId + '\'' +
                ", answer='" + answer + '\'' +
                ", isRight='" + right + '\'' +
                '}';
    }
}
