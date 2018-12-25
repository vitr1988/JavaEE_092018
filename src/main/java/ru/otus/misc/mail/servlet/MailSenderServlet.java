package ru.otus.misc.mail.servlet;

import ru.otus.misc.mail.bean.EmailBean;
import ru.otus.misc.mail.config.MailConfig;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MailSenderServlet", urlPatterns = "/mailSender")
public class MailSenderServlet extends HttpServlet {

    @Inject
    private EmailBean emailBean;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        // compose message
        try (PrintWriter pw = response.getWriter()){
            emailBean.sendEmail(MailConfig.RECEIVE_EMAILS, "New Subject", "This is body of test message");
            pw.println("Message sent successfully");
        } catch (MessagingException e) {
            throw new ServletException(e);
        }
    }
}
