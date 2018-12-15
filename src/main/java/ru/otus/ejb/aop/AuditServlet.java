package ru.otus.ejb.aop;

import ru.otus.ejb.aop.bean.AuditEJB;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AuditServlet", urlPatterns = "/audit")
public class AuditServlet extends HttpServlet {

    @EJB
    AuditEJB ejb;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println(ejb.bizMethod());
        }
    }
}
