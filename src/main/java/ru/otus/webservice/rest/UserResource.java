package ru.otus.webservice.rest;

import ru.otus.webservice.rest.model.User;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("user")
public class UserResource {

    @POST
    public Response create(@Valid @BeanParam User user){
        return Response.status(201).build();
    }

    @GET
    @Path("/{id}")
    public Response read(@PathParam("id") Integer id){
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, @Valid User user){
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id){
        return Response.status(204).build();
    }
}
