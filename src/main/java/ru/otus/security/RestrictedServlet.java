package ru.otus.security;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
    name ="RestrictedServlet",
    urlPatterns = { "/restricted/∗" } )
@ServletSecurity(httpMethodConstraints =  {
    @HttpMethodConstraint(value = "GET", rolesAllowed = "ADMIN",
        transportGuarantee = ServletSecurity.TransportGuarantee.NONE
    )})
public class RestrictedServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.login("ADMIN", "admin");
//        request.authenticate(response);
//        Principal principal = request.getUserPrincipal();
//        if (request.isUserInRole("ADMIN")) {
//        }

        try (PrintWriter pw = response.getWriter()) {
            pw.print("<h1>Secured Servlet</h1>");
        }
    }

}
