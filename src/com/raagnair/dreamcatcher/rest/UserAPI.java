package com.raagnair.dreamcatcher.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
public class UserAPI
{

    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    public String login()
    {
        return "yes";
    }

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test()
    {
        return "we're in baby!";
    }
}
