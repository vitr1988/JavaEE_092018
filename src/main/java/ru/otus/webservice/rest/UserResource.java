package ru.otus.webservice.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import ru.otus.webservice.rest.model.User;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("user")
@Api(tags = "User Resource")
@SwaggerDefinition(tags = {
    @Tag(name = "User Resource", description = "RESTful API to interact with user resource.")
})
public class UserResource {

    @POST
    @ApiOperation("Create user")
    public Response create(@Valid @BeanParam User user){
        return Response.status(201).build();
    }

    @GET
    @Path("/{id}")
    @ApiOperation("Get user")
    public Response read(@PathParam("id") Integer id){
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    @ApiOperation("Update user")
    public Response update(@PathParam("id") Integer id, @Valid User user){
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @ApiOperation("Delete user")
    public Response delete(@PathParam("id") Integer id){
        return Response.status(204).build();
    }
}
