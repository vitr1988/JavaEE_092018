package ru.otus.servlet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;
import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@WebFilter("/Application.html")
public class GwtFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final PushBuilder pushBuilder = request.newPushBuilder();
        if (pushBuilder != null) {
            Stream.of(new File(getServletContext().getRealPath("/Application")).listFiles())
                    .forEach(f -> {
                        if (f.isFile()) {
                            String fileName = f.getName();
                            if (fileName.endsWith("js")) {
                                pushBuilder.path("Application/" + fileName)
                                        .addHeader("content-type", "text/javascript")
                                        .push();
                            }
                        }
                    });

            pushBuilder.path("css/style-all.min.css")
                    .addHeader("content-type", "text/css")
                    .push();
        }
        super.doFilter(request, response, chain);
    }
}
