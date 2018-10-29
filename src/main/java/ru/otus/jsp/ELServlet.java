package ru.otus.jsp;

import ru.otus.jsp.model.Address;
import ru.otus.jsp.model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/el")
public class ELServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee person = new Employee();
        person.setName("Ivanov Vitalii");

        request.setAttribute("person", person);

        Employee emp = new Employee();
        Address add = new Address();
        add.setAddress("Samara");
        emp.setAddress(add);
        emp.setId(1);
        emp.setName("Vitalii Ivanov");

        HttpSession session = request.getSession();
        session.setAttribute("employee", emp);

        response.addCookie(new Cookie("User.Cookie","TomcatAdmin"));
        getServletContext().setAttribute("User.Cookie","TomcatUser");

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/el.jsp");
        rd.forward(request, response);
    }
}
