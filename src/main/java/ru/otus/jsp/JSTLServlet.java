package ru.otus.jsp;

import ru.otus.jsp.model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/jstl")
public class JSTLServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> empList = new ArrayList<Employee>();
        Employee emp1 = new Employee();
        emp1.setId(1);
        emp1.setName("Vitalii");
        emp1.setRole("Developer");

        Employee emp2 = new Employee();
        emp2.setId(2);
        emp2.setName("Alexey");
        emp2.setRole("Manager");
        empList.add(emp1);
        empList.add(emp2);

        request.setAttribute("empList", empList);

        request.setAttribute("htmlTagData", "<br/> creates a new line.");
        request.setAttribute("url", "http://www.otus.ru");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/jstl.jsp");
        rd.forward(request, response);
    }
}
