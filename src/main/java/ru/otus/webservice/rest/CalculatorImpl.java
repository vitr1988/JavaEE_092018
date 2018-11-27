package ru.otus.webservice.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

import javax.ws.rs.*;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Providers;

@Path("/calculator")
@Produces(MediaType.TEXT_PLAIN)
@Api(tags = "Calculator")
@SwaggerDefinition(tags = {
    @Tag(name = "Calculator", description = "RESTful API to manage with calculation.")
})
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

    @Context
    ResourceInfo resourceInfo; // supplies info about current resource

    @GET
//    @Path("/sqrt/{value}")
    @Path("/sqrt")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public double calcSqrt(/*@PathParam("value")*/@QueryParam("value") double value) {
        return Math.sqrt(value);
    }

    @GET
    @Path("/plus/{value1}/{value2}")
    @Override
//    @RolesAllowed({ANONYMOUS, CLIENT})
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

    @GET
    @Path("/mult/{value1}/{value2}")
    public Response calcMultiplyValues(@PathParam("value1") double value1, @PathParam("value2") double value2) {
        double result = value1 * value2;
        return Response.status(201).type(MediaType.APPLICATION_JSON_TYPE).entity(result).build();
    }
}
