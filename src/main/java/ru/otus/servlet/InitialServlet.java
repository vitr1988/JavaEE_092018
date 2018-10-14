package ru.otus.servlet;

import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "InitializeServlet", urlPatterns = "/init", loadOnStartup = 1)
public class InitialServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(InitialServlet.class.getName());

    @Override
    public void init(ServletConfig sc) {
        // TODO: you can do everything what you want to do
        logger.info("InitialServlet starts");
    }

    @Override
    public void destroy(){
        logger.info("InitialServlet is dead");
    }
}
