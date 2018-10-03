package ru.otus.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.otus.json.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/json")
public class JSONServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        user.setLogin("test");
        user.setPassword(new char[]{'t', 'e', 's', 't'});
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(user);
        // Serialize back
        try(PrintWriter pw = response.getWriter()){
            pw.println(result);
        }


    }
}