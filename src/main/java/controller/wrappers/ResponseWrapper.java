package controller.wrappers;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by lexy on 25.01.17.
 */
public class ResponseWrapper {
    private HttpServletResponse response;

    public ResponseWrapper(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletResponse getResponse(){
        return response;
    }
}
