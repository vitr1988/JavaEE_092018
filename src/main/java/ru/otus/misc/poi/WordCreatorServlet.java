package ru.otus.misc.poi;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "WordCreatorServlet", urlPatterns = "/poiWord")
public class WordCreatorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");

        response.setHeader("Expires", "0");
        response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setHeader("Content-Disposition", "attachment; filename=\"OtusReport.docx\"");

        try(OutputStream out = response.getOutputStream()) {
            //Blank Document
            XWPFDocument document = new XWPFDocument();

            //create Paragraph
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("Здесь могла быть Ваша реклама.");

            document.write(out);
            System.out.println("OtusReport.docx was written successfully");
        }
    }
}
