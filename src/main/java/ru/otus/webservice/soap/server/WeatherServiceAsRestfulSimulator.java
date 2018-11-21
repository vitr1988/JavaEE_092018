package ru.otus.webservice.soap.server;

import javax.annotation.Resource;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.http.HTTPBinding;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static javax.xml.ws.handler.MessageContext.HTTP_REQUEST_METHOD;
import static javax.xml.ws.handler.MessageContext.QUERY_STRING;

@WebServiceProvider
@ServiceMode(value = Service.Mode.MESSAGE)
@BindingType(value = HTTPBinding.HTTP_BINDING)
public class WeatherServiceAsRestfulSimulator implements Provider<Source> {

    private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?appid=%s&mode=xml&q=%s";
    private static final String GET_HTTP_METHOD = "GET";
    private static final String USER_AGENT_HEADER = "User-Agent";

    @Resource
    protected WebServiceContext wsContext;

    @Override
    public Source invoke(Source request) {
        MessageContext msg_cxt = wsContext.getMessageContext();
        String httpMethod = (String) msg_cxt.get(HTTP_REQUEST_METHOD);
        if (httpMethod.equalsIgnoreCase(GET_HTTP_METHOD)) {
            return doGet(msg_cxt);
        }
        throw new UnsupportedOperationException("Use only GET http verb");
    }

    private Source doGet(MessageContext msg_cxt) {
        String query_string = (String) msg_cxt.get(QUERY_STRING);
        StringBuffer text = new StringBuffer("");
        String city = query_string.split("=")[1];
        try {
            URL url = new URL(String.format(WEATHER_URL, "3d58903046050728d7b36ce11b3ce32d", city));
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(GET_HTTP_METHOD);
            urlConnection.connect();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bReader.readLine()) != null) {
                text = text.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new StreamSource(new StringReader(text.toString()));
    }

}

