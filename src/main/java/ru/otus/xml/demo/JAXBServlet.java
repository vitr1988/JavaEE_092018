package ru.otus.xml.demo;

import ru.otus.xml.model.DeptEntity;
import ru.otus.xml.model.EmpEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;

@WebServlet("/jaxb")
public class JAXBServlet extends HttpServlet {

    public static final String TEST_DATA_FILE_LOCATION = "testDataFileLocation";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DeptEntity department = new DeptEntity();
        department.setDeptno(100L);
        department.setDname("Accountant");
        department.setLoc("Moscow");

        EmpEntity employee = new EmpEntity();
        employee.setEmpno(1L);
        employee.setEname("Ivanov Vitalii Andreevich");
        employee.setMgr(1L);
        employee.setSal(5000L);
        employee.setComm(200L);
        employee.setHiredate(new Date());
        employee.setJob("BOSS");
        employee.setDeptNo(department);

        try(PrintWriter pw = response.getWriter()) {
            JAXBContext context = JAXBContext.newInstance(employee.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            String file = getServletContext().getInitParameter(TEST_DATA_FILE_LOCATION);
            pw.println(new URI(file).toString());
            m.marshal(employee, Paths.get(new URI(file)).toFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}