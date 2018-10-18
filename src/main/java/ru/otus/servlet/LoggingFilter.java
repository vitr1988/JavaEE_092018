package ru.otus.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoggingFilter", urlPatterns = "/*", asyncSupported = true)
public class LoggingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = null;
        if (req instanceof HttpServletRequest) {
            request = (HttpServletRequest) req;
        }
        HttpServletResponse response = (HttpServletResponse) resp;
        //TODO: preprocessing
        chain.doFilter(req, resp);
        //TODO: postprocessing
    }
}
