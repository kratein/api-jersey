package com.example.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.dao.UserDAO;
import com.example.entities.User;
import com.example.exception.CustomException;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserService {
    private final UserDAO userDAO = new UserDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        Response response;
        if (user == null) {
            response = Response.status(Status.NO_CONTENT).build();
        } else {
            try {
                response = Response.status(Status.OK).entity(userDAO.createUser(user)).build();
            } catch (CustomException cException) {
                response = Response.status(Status.BAD_REQUEST).entity(cException.toString()).build();
            }
        }
        return response;
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response UpdateUser(@PathParam("id") int id, User user) {
        Response response;
        if (user == null) {
            response = Response.status(Status.NO_CONTENT).build();
        } else {
            try {
                response = Response.status(Status.OK).entity(userDAO.updateUser(id, user)).build();
            } catch (CustomException cException) {
                response = Response.status(Status.BAD_REQUEST).entity(cException.toString()).build();
            }
        }
        return response;
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int id) {
        Response response;
        try {
            response = Response.status(Status.OK).entity(userDAO.deleteUser(id)).build();
        } catch (CustomException cException){
            response = Response.status(Status.BAD_REQUEST).entity(cException.toString()).build();
        }
        return response;
    }

    @GET
    @Path("/all")
    public Response getAllUsers() {
        return Response.status(Status.OK).entity(userDAO.findAllUsers()).build();
    }

    @GET
    @Path("{id}")
    public Response getUser(@PathParam("id") int id) {
        return Response.status(Status.OK).entity(userDAO.findUserById(id)).build();
    }
}