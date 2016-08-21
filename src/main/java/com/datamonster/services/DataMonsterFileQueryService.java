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
public class DataMonsterFileQueryService {

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
    public Response searchResuts(@QueryParam("url") String url,
                                @QueryParam("limit") String limit){
        
        Search newSearch=new Search();
        
        return Response.status(200).entity(newSearch.searchURLWithLimits(url,limit)).build();
        
    }

    @GET
    @Path("raw")
    @Produces(MediaType.TEXT_PLAIN)
    
    public String searchForVersion(@QueryParam("url")String url,
                                     @QueryParam("version") String version){
    
        Search newSearch=new Search();
     
        return newSearch.searchRawVersion(url,version);
    }

    @GET
    @Path("purge")
    @Produces(MediaType.TEXT_HTML)
    public Response purgeFiles(@QueryParam("url")String url,
                             @QueryParam("keeponlyrecent")String keeponlyrecent){
        Search newSearch=new Search();
        return Response.status(200).entity(newSearch.purgeAndKeep(url,keeponlyrecent)).build();
        
   }
}
