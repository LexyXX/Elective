package controller.tags;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by lexy on 28.01.17.
 */
public class PaginationTag extends TagSupport {
    private PageContext pageContext;
    private String action;
    private int pageCount;

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int doStartTag() throws JspException {
        return Tag.SKIP_BODY;
    }

    public int doEndTag() throws JspException {
        ServletRequest request = pageContext.getRequest();
        String page = request.getParameter("page");

        try(JspWriter out = pageContext.getOut()) {

            for (int i = 1; i <= pageCount+1; i++) {

                //checks whether current page is selected
                if ((page == null && i == 1) || (page != null && i == (Integer.parseInt(page)))) {
                    out.write(getLink(action, i, true, String.valueOf(i)));
                } else {
                    out.write(getLink(action, i, false, String.valueOf(i)));
                }
            }

        } catch (IOException e) {
            throw new JspException(e);
        }
        return Tag.EVAL_PAGE;
    }

    private String getLink(final String action, final int page, final boolean isCurrentPage, final String num) {
        StringBuilder link = new StringBuilder();
        link.append("<a href='");
        link.append(action);
        link.append("?page=");
        link.append(page);
        link.append("'><font color='" + (isCurrentPage ? "green" : "black") + "'>");
        link.append(num);
        link.append("</font></a>&nbsp;");
        return link.toString();
    }

    public void release() {

    }
}
