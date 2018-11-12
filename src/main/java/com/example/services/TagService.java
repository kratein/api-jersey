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

import com.example.dao.TagDAO;
import com.example.entities.Tag;
import com.example.exception.CustomException;

@Path("/tag")
@Produces(MediaType.APPLICATION_JSON)
public class TagService {
    private final TagDAO tagDAO = new TagDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTag(Tag tag) {
        Response response;
        if (tag == null) {
            response = Response.status(Status.NO_CONTENT).build();
        } else {
            try {
                response = Response.status(Status.OK).entity(tagDAO.createTag(tag)).build();
            } catch (CustomException cException) {
                response = Response.status(Status.BAD_REQUEST).entity(cException.toString()).build();
            }
        }
        return response;
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response UpdateTag(@PathParam("id") int id, Tag tag) {
        Response response;
        if (tag == null) {
            response = Response.status(Status.NO_CONTENT).build();
        } else {
            try {
                response = Response.status(Status.OK).entity(tagDAO.updateTag(id, tag)).build();
            } catch (CustomException cException) {
                response = Response.status(Status.BAD_REQUEST).entity(cException.toString()).build();
            }
        }
        return response;
    }

    @DELETE
    @Path("{id}")
    public Response deleteTag(@PathParam("id") int id) {
        Response response;
        try {
            response = Response.status(Status.OK).entity(tagDAO.deleteTag(id)).build();
        } catch (CustomException cException){
            response = Response.status(Status.BAD_REQUEST).entity(cException.toString()).build();
        }
        return response;
    }

    @GET
    @Path("/all")
    public Response getAllTags() {
        return Response.status(Status.OK).entity(tagDAO.findAllTags()).build();
    }

    @GET
    @Path("{id}")
    public Response getTag(@PathParam("id") int id) {
        Response response;
        Tag tag = tagDAO.findTagById(id);
        if (tag != null) {
            response = Response.status(Status.OK).entity(tag).build();
        } else {
            response = Response.status(Status.NOT_FOUND).entity(tag).build();
        }
        return response;
    }
}