package ru.otus.json.demo;

import org.apache.log4j.Logger;
import ru.otus.json.util.Util;
import ru.otus.xml.model.DeptEntity;
import ru.otus.xml.model.EmpEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/xml2json")
public class XML2JSONServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(XML2JSONServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        String xmlString = Util.marshal(employee);
        logger.info("XML content : " + xmlString);

        resp.setHeader("Content-type", "application/xml");
        try(PrintWriter pw = resp.getWriter()){
            pw.println(xmlString);
        }
    }
}
