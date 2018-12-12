package ru.otus.ejb.entity;

import ru.otus.db.entity.EmpEntity;

import javax.ejb.EJB;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("employee")
public class EmployeeResource {

    @EJB
    EntityBean bean;

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_HTML)
    public String getEmployee(@PathParam("id") Long id){
        EmpEntity employee = bean.loadEmployee(id);
        return employee == null ? "" : employee.toString();
    }
}
