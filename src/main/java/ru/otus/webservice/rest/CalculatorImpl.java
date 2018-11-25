package ru.otus.webservice.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Providers;

@Path("/calculator")
@Produces(MediaType.TEXT_PLAIN)
public class CalculatorImpl implements Calculator {

    @Context
    Application app; // provides access to application configuration information.

    @Context
    UriInfo uri; // provides access to application and request URI information.

    @Context
    HttpHeaders headers; // provides access to HTTP header information either as a Map or as convenience methods.
    // Note that @HeaderParam can also be used to bind an HTTP header to a resource method parameter,
    // field, or bean property.

    @Context
    Request request; // provides a helper to request processing and is typically used with Response to dynamically build the response.

    @Context
    SecurityContext security; // provides access to security-related information about the current request.

    @Context
    Providers providers; // supplies information about runtime lookup of provider instances based on a set of search criteria.

    @GET
    @Path("/sqrt/{value}")
    @Override
    public double calcSqrt(@PathParam("value") double value) {
        return Math.sqrt(value);
    }

    @GET
    @Path("/plus/{value1}/{value2}")
    @Override
    public double calcAddTwoValues(@PathParam("value1") double value1, @PathParam("value2") double value2) {
        return value1 + value2;
    }

    @GET
    @Path("/minus/{value1}/{value2}")
    @Override
    public double calcSubTwoValues(@PathParam("value1") double value1, @PathParam("value2") double value2) {
        return value1 - value2;
    }

    @GET
    @Path("/div/{value1}/{value2}")
    @Override
    public double calcDivTwoValues(@PathParam("value1") double value1, @PathParam("value2") double value2) {
        if (value2 == 0){
            throw new WebApplicationException(
                    Response.status(Response.Status.BAD_REQUEST)
                            .entity("Div by 0 is prohibited").type(MediaType.TEXT_PLAIN).build());
        }
        return value1 / value2;
    }
}
