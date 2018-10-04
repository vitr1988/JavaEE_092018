package ru.otus.json.demo;

import ru.otus.json.model.User;

import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/jsonb")
public class JSONBServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = "{\"login\":\"test\",\"password\":\"test\"}";
        User user = JsonbBuilder.create().fromJson(json, User.class);
        // Serialize back
        try(PrintWriter pw = response.getWriter()){
            pw.println(user);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setLogin("test");
        user.setPassword(String.valueOf(new char[]{'t', 'e', 's', 't'}));
        String result = JsonbBuilder.create().toJson(user);
        // Serialize back
        response.setHeader("Content-type", "application/json");
        try(PrintWriter pw = response.getWriter()){
            pw.println(result);
        }
    }
}
