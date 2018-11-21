package ru.otus.webservice.soap.server;

import org.apache.commons.io.IOUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet(name = "WeatherServiceClientServlet", urlPatterns = "/weatherClient")
public class WeatherServiceClientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // GET requests
        URL url = new URL(request.getScheme() + "://" + request.getServerName() + ":8700"
                + request.getContextPath()+ "/weather?city=Самара");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        String xml = IOUtils.toString(conn.getInputStream());
        try {
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            SaxParserHandler handler = new SaxParserHandler();
            parser.parse(new ByteArrayInputStream(xml.getBytes()), handler);
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.println("-------------------------------------------------------------------");
            writer.println(handler.city + " weather update on " + handler.lastUpdate);
            writer.println("-------------------------------------------------------------------");
            writer.println("City : " + handler.city);
            writer.println("Latitude : " + handler.latitude);
            writer.println("Longitude : " + handler.longitude);
            writer.println("Mininum Temperature (Celsius) : " + handler.minTemperature);
            writer.println("Maximum Temperature (Celsius) : " + handler.maxTemperature);
            writer.println("Wind : " + handler.wind);
            writer.println("Clouds : " + handler.clouds);
        } catch (SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}

class SaxParserHandler extends DefaultHandler {
    String city;
    String latitude;
    String longitude;
    float minTemperature;
    float maxTemperature;
    String wind;
    String clouds;
    String lastUpdate;

    public void startElement(String namespaceURI,String localName,String qname,Attributes attributes){

        if(qname.equals("city")){
            city=attributes.getValue("name");
        }else if(qname.equals("coord")){
            latitude=attributes.getValue("lat");
            longitude=attributes.getValue("lon");
        }
        else if(qname.equals("temperature")){
            String minKelvin=attributes.getValue("min");
            minTemperature=Math.round(Float.parseFloat(minKelvin)) - 272;
            String maxKelvin=attributes.getValue("max");
            maxTemperature= Math.round(Float.parseFloat(maxKelvin)) - 272;
        }
        else if(qname.equals("speed")){
            wind=attributes.getValue("name");
        }
        else if(qname.equals("clouds")){
            clouds=attributes.getValue("name");
        }
        else if(qname.equals("lastupdate")){
            lastUpdate=attributes.getValue("value").split("T")[0];
        }
    }

}
