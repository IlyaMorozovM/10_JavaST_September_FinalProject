package by.training.testing.bean;

import java.io.Serializable;
import java.util.Objects;

public class Result implements Serializable {

    private final int testId;
    private final String studentLogin;
    private final int points;

    public Result(int testId, String studentLogin, int points) {
        this.testId = testId;
        this.studentLogin = studentLogin;
        this.points = points;
    }

    public int getTestId() {
        return testId;
    }

    public String getStudentLogin() {
        return studentLogin;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return testId == result.testId &&
                points == result.points &&
                studentLogin.equals(result.studentLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, studentLogin, points);
    }

    @Override
    public String toString() {
        return "Result{" +
                "testId=" + testId +
                ", studentLogin='" + studentLogin + '\'' +
                ", points=" + points +
                '}';
    }
}
