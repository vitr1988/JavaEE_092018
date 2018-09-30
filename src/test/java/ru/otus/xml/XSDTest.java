package ru.otus.xml;

import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.net.URL;

public class XSDTest {

    @Test
    public void test(){
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            URL schemaFile = XSDTest.class.getResource("/xml/schemaEntity.xsd");

            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(XSDTest.class.getResourceAsStream("/xml/data.xml")));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+ e.getMessage());
            Assert.fail(e.getLocalizedMessage());
        }
    }
}
