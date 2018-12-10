package ru.otus.ejb.session;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/calcEJB")
public class CalculatorServlet extends HttpServlet {

    @EJB//@Inject
    CalculatorBean bean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        CalculatorBean bean = new CalculatorBean(); // так делать нельзя ни в коем случа!
        int summa = bean.summa(100, 124);
        int subtract = bean.subtract(100, 124);
        resp.getWriter().println("Summa : " + summa + "; Subtraction : " + subtract);
    }
}
