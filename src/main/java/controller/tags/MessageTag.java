package controller.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by lexy on 28.01.17.
 */
public class MessageTag extends SimpleTagSupport {
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void doTag() throws JspException, IOException {
        if (text!=null) {
            JspWriter out = getJspContext().getOut();
            out.println("Hello, "+text);
        }
    }
}
