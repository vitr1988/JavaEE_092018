package ru.otus.jsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/jsp")
public class JspServlet extends HttpServlet {

    public static final String DATE_TIME_FORMAT = "dd.MM.yyyy hh:mm:ss";

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    private String getFormattedDate() {
        return dateTimeFormatter.format(LocalDateTime.now());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter pw = resp.getWriter()) {
            pw.write("<!DOCTYPE>");
            pw.write("<html>");
            pw.write("<head>\n" +
                    "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                    "    <!-- Hello World -->\n" +
                    "    <title>Добро пожаловать, JSP!</title>\n" +
                    "</head>");

            pw.write("<body>");
                pw.write("<i>Сегодня ");
                pw.write(getFormattedDate());
                pw.write("</i>");
            pw.write("</body>");
            pw.write("</html>");
        }
    }
}
