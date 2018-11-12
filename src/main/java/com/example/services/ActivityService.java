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

import com.example.dao.ActivityDAO;
import com.example.entities.Activity;
import com.example.exception.CustomException;

@Path("/activity")
@Produces(MediaType.APPLICATION_JSON)
public class ActivityService {
    private final ActivityDAO activityDAO = new ActivityDAO();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createActivity(Activity activity) {
        Response response;
        if (activity == null) {
            response = Response.status(Status.NO_CONTENT).build();
        } else {
            try {          
                response = Response.status(Status.OK).entity(activityDAO.createActivity(activity)).build();
            } catch (CustomException cException) {
                response = Response.status(Status.BAD_REQUEST).entity(cException.toString()).build();
            }
        }
        return response;
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response UpdateActivity(@PathParam("id") int id, Activity activity) {
        Response response;
        if (activity == null) {
            response = Response.status(Status.NO_CONTENT).build();
        } else {
            try {
                response = Response.status(Status.OK).entity(activityDAO.updateActivity(id, activity)).build();
            } catch (CustomException cException) {
                response = Response.status(Status.BAD_REQUEST).entity(cException.toString()).build();
            }
        }
        return response;
    }

    @DELETE
    @Path("{id}")
    public Response deleteActivity(@PathParam("id") int id) {
        Response response;
        try {
            response = Response.status(Status.OK).entity(activityDAO.deleteActivity(id)).build();
        } catch (CustomException cException){
            response = Response.status(Status.BAD_REQUEST).entity(cException.toString()).build();
        }
        return response;
    }

    @GET
    @Path("/all")
    public Response getAllActivities() {
        return Response.status(Status.OK).entity(activityDAO.findAllActivities()).build();
    }

    @GET
    @Path("{id}")
    public Response getActivity(@PathParam("id") int id) {
        return Response.status(Status.OK).entity(activityDAO.findActivityById(id)).build();
    }

    @GET
    @Path("/tag/{id}")
    public Response getActivityBytag(@PathParam("id") int id_tag) {  
        return Response.status(Status.OK).entity(activityDAO.findActivitiesByIdtag(id_tag)).build();
    }

    
}