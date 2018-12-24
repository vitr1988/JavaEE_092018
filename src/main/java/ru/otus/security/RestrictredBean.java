package ru.otus.security;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import java.security.Principal;

@Stateless
@DeclareRoles("ADMIN")
public class RestrictredBean {

    @Inject
    private SecurityContext securityContext;

//    @Resource
//    private EJBContext context;

//    @DenyAll
//    @PermitAll
    @RolesAllowed({"ADMIN", "MANAGER"})
    public void printSomething() {
//        Principal callerPrincipal = context.getCallerPrincipal();
//        context.isCallerInRole("");
        securityContext.getCallerPrincipal();
        if (securityContext.isCallerInRole("ADMIN")) {
            //TODO:something
        }
        boolean hasAccess = securityContext.hasAccessToWebResource("/secretServlet", "GET");
    }
}
