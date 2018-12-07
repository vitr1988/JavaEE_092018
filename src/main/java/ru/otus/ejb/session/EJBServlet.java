package ru.otus.ejb.session;

import ru.otus.ejb.session.singleton.SingletonBean;
import ru.otus.ejb.session.stateful.AnotherSimpleBean;
import ru.otus.ejb.session.stateless.ApplicationSimpleBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet("/ejb")
public class EJBServlet extends HttpServlet {

    @EJB
    ApplicationSimpleBean bean;

    @EJB
    AnotherSimpleBean anotherBean;

    @EJB
    SingletonBean singletonBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        bean.doSomething();
        PrintWriter writer = response.getWriter();
        writer.println(bean.saySmth());
        writer.println(anotherBean.incrementLastValue(25));
        writer.println(Objects.toString(singletonBean.get(Long.MAX_VALUE), "Nothing in cache"));
        singletonBean.put(Long.MAX_VALUE, "Something is in cache");

    }
}
