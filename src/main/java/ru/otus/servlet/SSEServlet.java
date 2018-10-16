package ru.otus.servlet;

import com.google.gson.Gson;
import ru.otus.servlet.model.SseData;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet("/sse")
public class SSEServlet extends HttpServlet {

    private static final Gson JSON = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        while (true) { // this is only for demonstration purpose, don't do this! Use asynchronous APIs instead
            try {
                String data = JSON.toJson(new SseData(UUID.randomUUID().toString(), false));
                printWriter.write("data: " + data + "\n\n");
                printWriter.flush();
                response.flushBuffer();
                //Do not close the writer!
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
