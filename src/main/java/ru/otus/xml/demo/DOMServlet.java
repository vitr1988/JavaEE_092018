package ru.otus.xml.demo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.otus.xml.DOMUtil;
import ru.otus.xml.model.DeptEntity;
import ru.otus.xml.model.EmpEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import java.io.FileReader;
import java.io.IOException;

@WebServlet("/dom")
public class DOMServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Document document = DOMUtil.getDocument(getServletContext().getRealPath("/WEB-INF/classes/xml/data.xml"));
            final Element documentElement = document.getDocumentElement();
            if (documentElement.getTagName().equalsIgnoreCase("employee")) {
                EmpEntity entity = new EmpEntity();
                final String empnoStr = documentElement.getAttribute("empno");
                if (!DOMUtil.isEmpty(empnoStr)) entity.setEmpno(Long.decode(empnoStr));
                final String salaryStr = documentElement.getAttribute("sal");
                if (!DOMUtil.isEmpty(salaryStr)) entity.setSal(Long.decode(salaryStr));
                final NodeList employeeChildNodes = documentElement.getChildNodes();
                for (int i = 0; i < employeeChildNodes.getLength(); i++) {
                    Node node = employeeChildNodes.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        switch (element.getTagName().toLowerCase()) {
                            case "ename" : {
                                entity.setEname(element.getTextContent());
                                break;
                            }
                            case "job" : {
                                entity.setJob(element.getTextContent());
                                break;
                            }
                            case "mgr" : {
                                entity.setMgr(Long.decode(element.getTextContent()));
                                break;
                            }
                            case "department" : {
                                Element departmentElement = element;
                                DeptEntity deptEntity = new DeptEntity();
                                final String deptnoStr = departmentElement.getAttribute("deptno");
                                if (!DOMUtil.isEmpty(deptnoStr)) deptEntity.setDeptno(Long.decode(deptnoStr));
                                final NodeList departmentChildNodes = departmentElement.getChildNodes();
                                for (int j = 0; j < departmentChildNodes.getLength(); j++) {
                                    Node departmentNode = departmentChildNodes.item(j);
                                    if (departmentNode.getNodeType() == Node.ELEMENT_NODE) {
                                        departmentElement = (Element) departmentNode;
                                        switch (departmentElement.getTagName().toLowerCase()) {
                                            case "name": {
                                                deptEntity.setDname(departmentElement.getTextContent());
                                                break;
                                            }
                                            case "loc": {
                                                deptEntity.setLoc(departmentElement.getTextContent());
                                                break;
                                            }
                                        }
                                    }
                                }
                                entity.setDeptNo(deptEntity);
                            }
                        }
                    }
                }
                resp.getWriter().println(entity);
            }
        } catch (ParserConfigurationException | SAXException e) {
            new ServletException(e);
        }
    }
}
