package ru.otus.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.IntStream;

@WebServlet("/jsoup")
public class JsoupServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Document doc;
        response.setContentType("text/html; charset=UTF8");
        try (PrintWriter pw = response.getWriter()) {
            doc = Jsoup.connect(request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()
                    + request.getContextPath()
                    + "/html/page.html").get();
            pw.println("<!DOCTYPE html>");
            pw.println("<html lang=\"ru\">");
            pw.println("<body>");
            pw.println("Заголовок страницы: <em>" + doc.title() + "</em><br/><br/>");
            IntStream.range(1, 4).forEach(i -> {
                pw.println("Новость " + i + " : " + doc.getElementById("news" + i).text() + "<br/><br/>");
//                pw.println(doc.getElementById("news" + i).getElementsByTag("h3").first().text() + "<br/>");

            });
            pw.println("</body>");
            pw.println("</html>");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
