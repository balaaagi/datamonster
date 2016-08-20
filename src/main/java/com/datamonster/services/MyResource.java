package com.datamonster.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import javax.ws.rs.QueryParam;
import javax.ws.rs.PathParam;
import com.datamonster.finder.Search;
import javax.ws.rs.Encoded;
import javax.ws.rs.core.Context;

import javax.ws.rs.core.UriInfo;

// import javax.ws.rs.Decoded; 

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/")
@Encoded
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @GET
    @Path("search")
    @Produces(MediaType.TEXT_HTML)
    public Response searchResuts(@QueryParam("url") String url){
        // return "<b>"+url+"</b>"
        Search newSearch=new Search();
        // System.out.println(url);
        // return Response.status(200).entity("<html> " + "<title>" + "File Search Response" + "</title><body><b><a href=\""+url+"\">"+"Click Here </a></b></body></html>").build();
        return Response.status(200).entity(newSearch.searchURL(url)).build();
        // return "<a href=\"http://stackoverflow.com\"> Hellow</a>";
    }

    @GET
    @Path("raw")
    @Produces(MediaType.TEXT_HTML)
    
    public Response searchForVersion(@QueryParam("url")String url,
                                     @QueryParam("version") String version){
    // public Response searchForVersion(@Context UriInfo uriInfo){
        Search newSearch=new Search();
        // String url=uriInfo.getQueryParameters().getFirst("url");
        // String version=uriInfo.getQueryParameters().getFirst("version");
        return Response.status(200).entity(newSearch.searchRawVersion(url,version)).build();
    }
}
