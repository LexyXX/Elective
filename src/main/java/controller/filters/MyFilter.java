package controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by lexy on 04.01.17.
 */
@WebFilter(filterName = "MyFilter")
public class MyFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();

        if (!uri.equals("/") && !uri.startsWith("/Controller")
                && !uri.startsWith("/view") && !uri.startsWith("/resources")){
            req.getRequestDispatcher("/view/error.jsp").forward(req, resp);
        }
        else {
            if (uri.startsWith("/Controller/Language")) {
                String locale = uri.contains("en") ? "en_US" : "ru_RU";
                request.getSession().setAttribute("locale", locale);

                req.getRequestDispatcher("/").forward(req, resp);
            } else {
                chain.doFilter(req, resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
