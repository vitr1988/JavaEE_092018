package ru.otus.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/upload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    public static final String FILE_INPUT_NAME = "file";

    public static final String AVAILABLE_FOR_UPLOADING_FILE_EXTENSION = "txt";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final List<String> allLines = new ArrayList<>();
        // process all files from multiple file input
        request.getParts().stream()
            .filter(part -> FILE_INPUT_NAME.equals(part.getName())
                    && part.getSubmittedFileName().toLowerCase().endsWith(AVAILABLE_FOR_UPLOADING_FILE_EXTENSION))// Retrieves <input type="file" name="file" multiple="true">
            .forEach(part -> {
                // Scanner тоже неплох :)
                try (BufferedReader buffer = new BufferedReader(
                        new InputStreamReader(part.getInputStream(), "Cp1251"))) {
                    allLines.add("<strong>Content of file '" + part.getSubmittedFileName() + "'</strong>");
                    allLines.add("");
                    allLines.addAll(buffer.lines().collect(Collectors.toList()));
                    allLines.add("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println(allLines.stream().collect(Collectors.joining("<br/>")));
    }
}
