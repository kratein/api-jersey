package com.example.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/photo")
public class PhotoService {

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllPhotos() {
        return "All Photos";
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPhoto(@PathParam("id") int id) {
        return "one photos";
    }   
}