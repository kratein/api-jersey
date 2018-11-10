package com.example.services;

import com.example.dao.PhotoDAO;
import com.example.entities.Photo;
import com.example.exception.CustomException;

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

@Path("/photo")
@Produces(MediaType.APPLICATION_JSON)
public class PhotoService {

    private final PhotoDAO photoDAO = new PhotoDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPhoto(Photo photo) {
        Response response;
        if (photo == null) {
            response = Response.status(Status.NO_CONTENT).build();
        } else {
            try {          
                response = Response.status(Status.OK).entity(photoDAO.createPhoto(photo)).build();
            } catch (CustomException cException) {
                response = Response.status(Status.BAD_REQUEST).entity(cException.toString()).build();
            }
        }
        return response;
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response UpdatePhoto(@PathParam("id") int id, Photo photo) {
        Response response;
        if (photo == null) {
            response = Response.status(Status.NO_CONTENT).build();
        } else {
            try {
                response = Response.status(Status.OK).entity(photoDAO.updatePhoto(photo)).build();
            } catch (CustomException cException) {
                response = Response.status(Status.BAD_REQUEST).entity(cException.toString()).build();
            }
        }
        return response;
    }

    @DELETE
    @Path("{id}")
    public Response deletePhoto(@PathParam("id") int id) {
        Response response;
        try {
            response = Response.status(Status.OK).entity(photoDAO.deletePhoto(id)).build();
        } catch (CustomException cException){
            response = Response.status(Status.BAD_REQUEST).entity(cException.toString()).build();
        }
        return response;
    }

    @GET
    @Path("/all")
    public Response getAllPhotos() {
        return Response.status(Status.OK).entity(photoDAO.findAllPhotos()).build();
    }

    @GET
    @Path("{id}")
    public Response getPhoto(@PathParam("id") int id) {
        Response response;
        Photo photo = photoDAO.findPhotoById(id);
        if (photo != null) {
            response = Response.status(Status.OK).entity(photo).build();
        } else {
            response = Response.status(Status.NOT_FOUND).entity(photo).build();
        }
        return response;
    }
}