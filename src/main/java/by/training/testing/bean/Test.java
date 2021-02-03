package by.training.testing.bean;

import java.io.Serializable;
import java.util.Objects;

public class Test implements Serializable {
    private final int testId;
    private final int subjectId;
    private final String title;

    public Test(int testId, int subjectId, String title) {
        this.testId = testId;
        this.subjectId = subjectId;
        this.title = title;
    }

    public int getTestId() {
        return testId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Test that = (Test)obj;
        return Objects.equals(subjectId, that.subjectId) &&
                Objects.equals(testId, that.testId) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId, testId, title);
    }

    @Override
    public String toString() {
        return "Test{" +
                "testId='" + testId + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
