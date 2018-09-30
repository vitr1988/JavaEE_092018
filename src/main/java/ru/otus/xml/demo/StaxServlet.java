package ru.otus.xml.demo;

import ru.otus.xml.model.DeptEntity;
import ru.otus.xml.model.EmpEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileReader;
import java.io.IOException;

@WebServlet("/stax")
public class StaxServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(getServletContext().getRealPath("/WEB-INF/classes/xml/data.xml")));
            EmpEntity empEntity = new EmpEntity();
            DeptEntity deptEntity = new DeptEntity();
            boolean isEname = false,
                    isJob = false,
                    isMgr = false,
                    isDeptName = false,
                    isDeptLoc = false;
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        switch (qName) {
                            case "employee" : {
                                empEntity.setEmpno(Long.valueOf(startElement.getAttributeByName(new QName("empno")).getValue()));
                                empEntity.setSal(Long.valueOf(startElement.getAttributeByName(new QName("sal")).getValue()));
                                break;
                            }
                            case "department" : {
                                deptEntity.setDeptno(Long.valueOf(startElement.getAttributeByName(new QName("deptno")).getValue()));
                                break;
                            }
                            case "ename" : {
                                isEname = true;
                                break;
                            }
                            case "job" : {
                                isJob = true;
                                break;
                            }
                            case "mgr" : {
                                isMgr = true;
                                break;
                            }
                            case "name" : {
                                isDeptName = true;
                                break;
                            }
                            case "loc" : {
                                isDeptLoc = true;
                                break;
                            }
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        String data = characters.getData();
                        if (isEname) {
                            empEntity.setEname(data);
                        }
                        else if (isJob) {
                            empEntity.setJob(data);
                        }
                        else if (isMgr) {
                            empEntity.setMgr(Long.valueOf(data));
                        }
                        else if (isDeptName) {
                            deptEntity.setDname(data);
                        }
                        else if (isDeptLoc) {
                            deptEntity.setLoc(data);
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        String endQName = endElement.getName().getLocalPart();
                        switch (endQName) {
                            case "ename" : {
                                isEname = false;
                                break;
                            }
                            case "job" : {
                                isJob = false;
                                break;
                            }
                            case "mgr" : {
                                isMgr = false;
                                break;
                            }
                            case "name" : {
                                isDeptName = false;
                                break;
                            }
                            case "loc" : {
                                isDeptLoc = false;
                                break;
                            }
                        }
                        // check if we found the closing element
                        // close resources that need to be explicitly closed
                        break;
                }
            }
            empEntity.setDeptNo(deptEntity);
            response.getWriter().println(empEntity.toString());
        }
        catch (XMLStreamException e){
            new ServletException(e);
        }
    }
}
