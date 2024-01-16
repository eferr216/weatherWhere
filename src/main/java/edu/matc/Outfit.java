package edu.matc;

import edu.matc.entity.Item;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/outfits")
public class Outfit {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public Response getMesssage(@Context HttpServletRequest req) {
        HttpSession session = req.getSession();
        String temp = (String) session.getAttribute("temperature");
        String windSpeed = (String) session.getAttribute("windSpeed");

        Item footwearSelection = (Item) session.getAttribute("footwearSelection");
        Item socksSelection = (Item) session.getAttribute("socksSelection");
        Item shirtsSelection = (Item) session.getAttribute("shirtsSelection");
        Item legwearSelection = (Item) session.getAttribute("legwearSelection");
        Item sweatersSelection = (Item) session.getAttribute("sweatersSelection");
        Item lightJacketsSelection = (Item) session.getAttribute("lightJacketsSelection");
        Item glovesSelection = (Item) session.getAttribute("glovesSelection");
        Item scarvesSelection = (Item) session.getAttribute("scarvesSelection");
        Item earMuffsSelection = (Item) session.getAttribute("earMuffsSelection");
        Item heavyJacketsSelection = (Item) session.getAttribute("heavyJacketsSelection");

        // Return a simple message
        String output = "Temp: " + temp + ", Wind Speed: " + windSpeed + ", Footwear: "
                    + footwearSelection + ", Socks: " + socksSelection + ", Shirt: "
                    + shirtsSelection + ", Legwear: " + legwearSelection
                    + ", Sweater: " + sweatersSelection + ", Light Jacket: "
                    + lightJacketsSelection + ", Gloves: " + glovesSelection
                    + ", Scarf: " + scarvesSelection + ", Ear Muffs: "
                    + earMuffsSelection + ", Heavy Jacket: " + heavyJacketsSelection;

        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/outfitId")
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public Response getSpecificIdMesssage() {
        // Return a simple message
        String output = "this is an outfit with a specific id.";
        return Response.status(200).entity(output).build();
    }
}
