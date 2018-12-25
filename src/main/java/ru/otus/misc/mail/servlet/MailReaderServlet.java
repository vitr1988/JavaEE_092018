package ru.otus.misc.mail.servlet;

import ru.otus.misc.mail.bean.EmailBean;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MailReaderServlet", urlPatterns = "/mailReader")
public class MailReaderServlet extends HttpServlet {

    @Inject
    private EmailBean emailBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        // compose message
        try (PrintWriter pw = response.getWriter()){
            pw.println(emailBean.readEmail());
        } catch (MessagingException e) {
            throw new ServletException(e);
        }
    }
}
