package ru.otus.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;
import java.io.IOException;

@WebServlet("/push")
public class PushBuilderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PushBuilder pushBuilder = request.newPushBuilder(); // local variables are thread safe
        if (pushBuilder != null) {
            pushBuilder.path("images/news.jpg")
                        .addHeader("content-type", "image/jpg")
                    .push();
            pushBuilder.path("css/style-all.min.css")
                        .addHeader("content-type", "text/css")
                    .push();
        }
        request.getRequestDispatcher("/html/page.html").forward(request, response);
    }
}
