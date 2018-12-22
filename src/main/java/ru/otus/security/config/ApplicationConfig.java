package ru.otus.security.config;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.FormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

@FormAuthenticationMechanismDefinition(
    loginToContinue = @LoginToContinue(
        loginPage = "/WEB-INF/login.html",
        errorPage = "/WEB-INF/loginError.html")
)
@ApplicationScoped
public class ApplicationConfig { }
