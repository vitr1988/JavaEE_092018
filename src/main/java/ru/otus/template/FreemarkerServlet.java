package ru.otus.template;

import ru.otus.jsp.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/freemarker")
public class FreemarkerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("user", "Ivanov Vitalii");
        request.setAttribute("x", 2);
        request.setAttribute("foo", true);
        request.setAttribute("bar", "  Test   ");

        Employee emp = new Employee();
        emp.setName("Vitalii Ivanov");

        Employee emp2 = new Employee();
        emp2.setName("Petr Sidorov");

        request.setAttribute("persons", Arrays.asList(emp, emp2));

        request.getRequestDispatcher("/WEB-INF/classes/template/page.ftl").forward(request, response);
    }
}
