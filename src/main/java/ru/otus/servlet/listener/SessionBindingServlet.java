package ru.otus.servlet.listener;

import ru.otus.servlet.listener.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sessionBinding")
public class SessionBindingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(1, "Ivanov Vitalii");
        req.getSession().setAttribute("user", user);
        resp.getWriter().println("OK for me as " + user.getName());
    }
}
