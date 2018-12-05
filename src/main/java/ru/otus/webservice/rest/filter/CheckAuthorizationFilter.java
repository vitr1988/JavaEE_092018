package ru.otus.webservice.rest.filter;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;
import java.util.Objects;

@Provider
@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class CheckAuthorizationFilter implements ContainerRequestFilter {

    @Context
    HttpServletRequest request;

    @Context
    UriInfo uriInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        requestContext.setSecurityContext(new SecurityContext() {

            @Override
            public Principal getUserPrincipal() {
                // get principal info from session
                return null;
            }

            @Override
            public boolean isUserInRole(String s) {
                return getUserPrincipal().getName().equals(s);
            }

            @Override
            public boolean isSecure() {
                return uriInfo.getAbsolutePath().toString().startsWith("https");
            }

            @Override
            public String getAuthenticationScheme() {
                return "Token-Based-Auth-Scheme";
            }
        });

//        String payload = null;
//        if (isJson(requestContext)) {
//            payload = IOUtils.toString(requestContext.getEntityStream(), StandardCharsets.UTF_8.name());
//            // replace input stream for Jersey as we've already read it
//            requestContext.setEntityStream(IOUtils.toInputStream(payload));
//        }
//        else {
//            payload = requestContext.getUriInfo().getQueryParameters().entrySet().stream()
//                    .map(entry -> entry.getKey() + " : " + entry.getValue())
//                    .collect(Collectors.joining(", "));
//            if (payload != null) {
//                payload = "{" + payload + "}";
//            }
//        }
    }

    private boolean isJson(ContainerRequestContext request) {
        // define rules when to read body
        return Objects.toString(request.getMediaType(), "").contains(MediaType.APPLICATION_JSON);
    }
}
