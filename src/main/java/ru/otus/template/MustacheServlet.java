package ru.otus.template;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import ru.otus.template.model.ToDo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;

@WebServlet("/mustache")
public class MustacheServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                getServletContext().getResourceAsStream("/WEB-INF/classes/template/todo.mustache")))) {
            Mustache m = mf.compile(reader, null);

            response.setContentType("text/html;charset=UTF-8");

            Map<String, Object> context = new HashMap<>();
            List<ToDo> todo = Arrays.asList(
                    new ToDo("Ivanov Vitalii", new Date(), "Hello World!", true)
                    , new ToDo("Sidorov Petr", new Date("04/11/2018"), "Hi World!", false)
            );
            context.put("todos", todo);

//            m.execute(response.getWriter(), new ToDo("Ivanov Vitalii", new Date(), "Hello World!")).flush();
            m.execute(response.getWriter(), context).flush();
        }
    }
}
