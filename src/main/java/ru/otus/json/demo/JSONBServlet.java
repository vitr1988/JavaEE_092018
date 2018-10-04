package ru.otus.json.demo;

import ru.otus.json.model.User;
import ru.otus.json.model.jsonb.Person;

import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;

@WebServlet("/jsonb")
public class JSONBServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = "{\n" +
                "\"email\": \"v_ivanov@otus.ru\",\n" +
                "\"id\": 1,\n" +
                "\"person-name\": \"Vitalii Ivanov\",\n" +
                "\"registeredDate\": \"01.01.2010\",\n" +
                "\"salary\": 5000\n" +
                "}";
        Person user = JsonbBuilder.create().fromJson(json, Person.class);
        // Serialize back
        try(PrintWriter pw = response.getWriter()){
            pw.println(user);
        }
    }

    private int index = 1;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Person person = new Person();
        person.setId(index++);
        person.setName("Vitalii Ivanov");
        person.setAge(30);
        person.setEmail("v_ivanov@otus.ru");
        person.setSalary(BigDecimal.valueOf(5000));
        person.setRegisteredDate(LocalDate.now());
        String result = JsonbBuilder.create().toJson(person);
        // Serialize back
        response.setHeader("Content-type", "application/json");
        try(PrintWriter pw = response.getWriter()){
            pw.println(result);
        }
    }
}
