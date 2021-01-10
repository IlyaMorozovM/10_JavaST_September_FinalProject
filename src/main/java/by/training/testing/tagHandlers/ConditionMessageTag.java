package by.training.testing.tagHandlers;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class ConditionMessageTag extends SimpleTagSupport {
    private boolean condition;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    @Override
    public void doTag() throws IOException {
        if(condition) {
            JspWriter out = getJspContext().getOut();
            out.println("<h2>" + message + "</h2>");
        }
    }
}
