package edu.matc.rest;

import edu.matc.entity.Item;
import edu.matc.persistence.GenericDao;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/outfits")
public class OutfitAPI {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/html")
    public Response getMesssage(@Context HttpServletRequest req) {
        Outfit outfit = new Outfit();

        String output = outfit.getApiOutputString(req);

        return Response.status(200).entity(output).build();
    }

}
