package edu.matc.restdemo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/outfits")
public class Outfit {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public Response getMesssage() {
        // Return a simple message
        String output = "Outfit";
        return Response.status(200).entity(output).build();
    }
}
