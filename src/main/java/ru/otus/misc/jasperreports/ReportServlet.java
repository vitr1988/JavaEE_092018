package ru.otus.misc.jasperreports;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

@WebServlet(name = "ReportServlet", urlPatterns = "/report")
public class ReportServlet extends HttpServlet {

    private static final String otusReport = "OtusReport";

    @Resource(mappedName = "jdbc/OracleDS")
    DataSource dataSource;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReportFiller filler = new ReportFiller();
        filler.setDataSource(dataSource);
        filler.setParameters(new HashMap<String, Object>() {
            {
//                put("reportTitle", "Otus Report Data");
                put("reportTitle", "Новый заголовок");
            }
        });
        filler.setReportFileName(otusReport +  ".jrxml");
        filler.setRequest(request);
        filler.prepareReport();

        response.setContentType("application/pdf");
        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setHeader("Content-Disposition", "inline; filename=\"" + otusReport + ".pdf\"");
        try (OutputStream stream = response.getOutputStream()) {
            filler.print(stream);
        }
    }
}
