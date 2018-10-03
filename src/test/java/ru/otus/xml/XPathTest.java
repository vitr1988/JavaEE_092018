package ru.otus.xml;

import org.jaxen.JaxenException;
import org.jaxen.XPath;
import org.jaxen.dom.DOMXPath;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.util.List;

import static ru.otus.xml.DOMUtil.getDocument;

public class XPathTest {

    private static final String BASIC_XML = "/xml/data.xml";

    private Document doc;

    @Before
    public void setUp() throws ParserConfigurationException, SAXException, IOException {
        doc = getDocument(XPathTest.class.getResourceAsStream(BASIC_XML));
    }

    @Test
    public void testXPath(){
        String expression = "/employee/department/name/text()";
        try {
            javax.xml.xpath.XPath xPath = XPathFactory.newInstance().newXPath();
            String nodeList = (String) xPath.compile(expression).evaluate(doc, XPathConstants.STRING);
            Assert.assertEquals("Accountant", nodeList);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testXPathByJaxen(){
        String expression = "/employee/department";
        try {
            XPath path = new DOMXPath(expression);
            List result = path.selectNodes(doc);
            Assert.assertEquals(1, result.size());
        } catch (JaxenException e) {
            e.printStackTrace();
        }
    }
}