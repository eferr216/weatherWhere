package edu.matc.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/outfits")
public class OutfitAPI {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/html"
    @Produces("text/html")
    public Response getMesssage(@Context HttpServletRequest req) {
        Outfit outfit = new Outfit();

        String output = outfit.getRandomOutfit();

        return Response.status(200).entity(output).build();
    }

    @GET
    // The Java method will produce content identified by the MIME Media type "text/html"
    @Path("/get")
    @Produces("text/html")
    public Response getMessageWithCoordinateParams(@Context HttpServletRequest req,
                                                   @QueryParam("x") String x,
                                                   @QueryParam("y") String y) {

        Outfit outfit = new Outfit();

        String output = outfit.getOutfitUsingCoordinates(x, y);

        return Response.status(200).entity(output).build();
    }

}
