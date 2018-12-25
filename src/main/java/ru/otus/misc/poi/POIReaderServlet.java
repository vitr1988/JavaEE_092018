package ru.otus.misc.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;

@WebServlet(name = "POIReaderServlet", urlPatterns = "/poiReader")
public class POIReaderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        try (PrintWriter pw = response.getWriter()){

            FileInputStream excelFile = new FileInputStream(new File(request.getParameter("path")));
            Workbook workbook = new XSSFWorkbook(excelFile); //HSSFWorkbook - for xls-files
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum will be renamed to getCellType starting from version 4.0
                    switch (currentCell.getCellType()){
                        case STRING :
                            pw.println(currentCell.getStringCellValue() + "--"); break;
                        case NUMERIC :
                            pw.println(currentCell.getNumericCellValue() + "--"); break;
                    }

                }
                System.out.println();
            }
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
