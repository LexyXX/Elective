package controller.wrappers;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lexy on 25.01.17.
 */
public class RequestWrapper {
    private HttpServletRequest request;

    public RequestWrapper(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setSessionAttribute(String attrName, Object attrValue){
        request.getSession().setAttribute(attrName, attrValue);
    }

    public void removeSessionAttribute(String attrName){
        request.getSession().removeAttribute(attrName);
    }

    public Object getSessionAttribute(String name){
        return request.getSession().getAttribute(name);
    }

    public void invalidateSession(){
        request.getSession().invalidate();
    }

    public String getRequestURI(){
        return request.getRequestURI();
    }

    public String getParameter(String name){
        return request.getParameter(name);
    }
}
