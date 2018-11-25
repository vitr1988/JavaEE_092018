package ru.otus.webservice.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/calculator")
@Produces(MediaType.TEXT_PLAIN)
public class CalculatorImpl implements Calculator {

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
