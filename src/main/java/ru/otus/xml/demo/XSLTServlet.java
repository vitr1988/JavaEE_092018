package ru.otus.xml.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/xslt")
public class XSLTServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StreamSource stylesource = new StreamSource(getServletContext().getResourceAsStream("/WEB-INF/classes/xml/employee.xsl"));
        StreamSource xmlsource = new StreamSource(getServletContext().getResourceAsStream("/WEB-INF/classes/xml/data.xml"));
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter pw = response.getWriter()) {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);
            // Transform the document and store it in a file
            transformer.transform(xmlsource, new StreamResult(pw));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
