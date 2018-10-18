package ru.otus.servlet.listener;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class ApplicationSessionAttributeListener implements HttpSessionAttributeListener {

    private static final Logger logger = Logger.getLogger(ApplicationSessionAttributeListener.class.getName());

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        switch (event.getName()) {
            case "info" : {
                logger.info("Info message with value " +  event.getValue() + " with session id " + event.getSession().getId());
                break;
            }
            default : {
                logger.trace("Trace message with value \" +  event.getValue() + \" with session id \" + event.getSession().getId()");
                break;
            }
        }

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        logger.info("Delete event : " + event.getName());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        logger.info("Replaceing with info message with value " +  event.getValue() + " with session id " + event.getSession().getId());
    }
}
