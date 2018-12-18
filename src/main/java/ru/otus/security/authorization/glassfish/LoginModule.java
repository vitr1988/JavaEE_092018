package ru.otus.security.authorization.glassfish;

import com.sun.appserv.security.AppservPasswordLoginModule;

import javax.security.auth.login.LoginException;

@SuppressWarnings("rawtypes")
public class LoginModule extends AppservPasswordLoginModule {

    @Override
    protected void authenticateUser() throws LoginException {
        System.out.println("Going to Log In ............................");
        String userString = _username;

        //HERE YOU CAN GET A HANDLE TO A JDBC CONNECTION POOL IN GLASSFISH
        //FROM THE JNDI NAME, AND EXECUTE A SQL TO RETRIEVE ALL THE GROUPS
        //THE USER BELONGS TO

        String[] groups = {"ADMIN"};
        commitUserAuthentication(groups);
    }

}
