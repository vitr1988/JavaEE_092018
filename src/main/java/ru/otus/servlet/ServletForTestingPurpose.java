package ru.otus.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

@WebServlet(name = "ServletForTestingPurpose", urlPatterns = {"/testing", "/test/*", "*.proto"})
public class ServletForTestingPurpose extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public void init() throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request){
//            @Override
//            public String getParameter(String name) {
//                String value = super.getParameter(name);
//                if (name.equalsIgnoreCase("url")) {
//                    value = "http://" + value;
//                }
//                return value;
//            }
//        };
//        request.getRequestDispatcher("/push").forward(requestWrapper, response);

        response.setContentType("text/html");
        try(PrintWriter pw = response.getWriter()) {
            pw.print("Hello World");
        }
    }

    private int index = 1;

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        index++;
        String url = request.getParameter("url");
        HttpSession session = request.getSession(false);
        if (session == null && !session.isNew()) {
            session = request.getSession();
        }
        session.setAttribute("", "");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Stream.of(cookies).forEach(c -> {
                String cookieName = c.getName();
                String cookieValue = c.getValue();
            });

        }
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.getWriter().println("Hello World");
        Cookie cookie = new Cookie("customCoookie", 1 + "");
        response.addCookie(cookie);

        getServletContext().setAttribute("", "");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch(req.getMethod().toLowerCase()) {
            case "get" : {
                doGet(req, resp); break;
            }
            case "post" : {
                doPost(req, resp); break;
            }
        }
    }
}
