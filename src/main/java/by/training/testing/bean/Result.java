package by.training.testing.bean;

import java.io.Serializable;
import java.util.Objects;

public class Result implements Serializable {

    private final int testId;
    private final int userId;
    private final int points;

    public Result(int testId, int userId, int points) {
        this.testId = testId;
        this.userId = userId;
        this.points = points;
    }

    public int getTestId() {
        return testId;
    }

    public int getUserId() {
        return userId;
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
                userId == result.userId &&
                points == result.points;
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, userId, points);
    }

    @Override
    public String toString() {
        return "Result{" +
                "testId=" + testId +
                ", userId=" + userId +
                ", points=" + points +
                '}';
    }
}
