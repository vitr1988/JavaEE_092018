package ru.otus.ws;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "HttpUpgradeServlet")
public class HttpUpgradeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.upgrade(SimpleHttpUpgradeHandler.class);
        //TODO: something interesting...
    }

    class SimpleHttpUpgradeHandler implements HttpUpgradeHandler {
        @Override
        public void init(WebConnection webConnection) {

        }

        @Override
        public void destroy() {

        }
    }
}
