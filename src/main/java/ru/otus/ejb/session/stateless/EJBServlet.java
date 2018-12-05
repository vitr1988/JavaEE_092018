package ru.otus.ejb.session.stateless;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ejb")
public class EJBServlet extends HttpServlet {

    @EJB
    ApplicationSimpleBean bean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        bean.doSomething();
        response.getWriter().println(bean.saySmth());
    }
}
