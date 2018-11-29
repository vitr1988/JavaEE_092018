package ru.otus.webservice.rest;

import io.swagger.annotations.*;
import ru.otus.webservice.rest.model.User;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("user")
@SwaggerDefinition(
        info = @Info(
                title = "Swagger-generated RESTful API",
                description = "RESTful Description example",
                version = "1.0.0",
                termsOfService = "share and care",
                contact = @Contact(
                        name = "Vitalii", email = "v_ivanov@otus.ru",
                        url = "https://otus.ru"),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org")),
        tags = {
                @Tag(name = "User Resource", description = "RESTful API to interact with user resource.")
        },
        host = "localhost:8080",
        basePath = "/JavaEE/api",
        schemes = {SwaggerDefinition.Scheme.HTTP},
        externalDocs = @ExternalDocs(
                value = "Developing a Swagger-enabled REST API using WebSphere Developer Tools",
                url = "https://tinyurl.com/swagger-wlp")
)
@Api(tags = "User Resource")
public class UserResource {

    @POST
    @ApiOperation("Create user")
    @ApiResponses({
        @ApiResponse(code = 201, message = "User created")
    })
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "User's id", dataType = "long", paramType = "query"),
        @ApiImplicitParam(name = "login", value = "Login", dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "fio", value = "Full name", dataType = "string", paramType = "query"),
        @ApiImplicitParam(name = "age", value = "User's age", dataType = "int", paramType = "query")
    })
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
