package controller.wrappers;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lexy on 25.01.17.
 */
public class ResponseWrapper {
    private HttpServletResponse response;

    public ResponseWrapper(HttpServletResponse response) {
        this.response = response;
    }

    public void sendRedirect(String url) throws IOException {
        response.sendRedirect(url);
    }
}
