package ru.otus.security;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;

@Stateless
public class RestrictredBean {

    @Inject
    private SecurityContext securityContext;

    public void printSomething() {
        if (securityContext.isCallerInRole("ADMIN")) {
            //TODO:something
        }
        boolean hasAccess = securityContext.hasAccessToWebResource("/secretServlet", "GET");
    }
}
