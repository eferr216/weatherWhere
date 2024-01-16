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

        /*Item footwearSelection = (Item) session.getAttribute("footwearSelection");
        Item socksSelection = (Item) session.getAttribute("socksSelection");
        Item shirtsSelection = (Item) session.getAttribute("shirtsSelection");
        Item legwearSelection = (Item) session.getAttribute("legwearSelection");

        Item sweatersSelection = (Item) session.getAttribute("sweatersSelection");
        Item lightJacketsSelection = (Item) session.getAttribute("lightJacketsSelection");

        Item glovesSelection = (Item) session.getAttribute("glovesSelection");
        Item scarvesSelection = (Item) session.getAttribute("scarvesSelection");
        Item earMuffsSelection = (Item) session.getAttribute("earMuffsSelection");
        Item heavyJacketsSelection = (Item) session.getAttribute("heavyJacketsSelection");*/

        String footwearName = (String) session.getAttribute("footwearName");
        String footwearDescription = (String) session.getAttribute("footwearDescription");

        String socksName = (String) session.getAttribute("socksName");
        String socksDescription = (String) session.getAttribute("socksDescription");

        String shirtName = (String) session.getAttribute("shirtName");
        String shirtDescription = (String) session.getAttribute("shirtDescription");

        String legwearName = (String) session.getAttribute("legwearName");
        String legwearDescription = (String) session.getAttribute("legwearDescription");

        String sweaterName = (String) session.getAttribute("sweaterName");
        String sweaterDescription = (String) session.getAttribute("sweaterDescription");

        String lightJacketName = (String) session.getAttribute("lightJacketName");
        String lightJacketDescription = (String) session.getAttribute("lightJacketDescription");

        String glovesName = (String) session.getAttribute("glovesName");
        String glovesDescription = (String) session.getAttribute("glovesDescription");

        String scarfName = (String) session.getAttribute("scarfName");
        String scarfDescription = (String) session.getAttribute("scarfDescription");

        String earMuffsName = (String) session.getAttribute("earMuffsName");
        String earMuffsDescription = (String) session.getAttribute("earMuffsDescription");

        String heavyJacketName = (String) session.getAttribute("heavyJacketName");
        String heavyJacketDescription = (String) session.getAttribute("heavyJacketDescription");

        // Return a simple message
        String output = "Temp: " + temp + ", Wind Speed: " + windSpeed
                    + ", Footwear: " + footwearName + "; " + footwearDescription
                    + ", Socks: " + socksName + "; " + socksDescription
                    + ", Shirt: " + shirtName + "; " + shirtDescription
                    + ", Legwear: " + legwearName + "; " + legwearDescription
                    + ", Sweater: " + sweaterName + "; " + sweaterDescription
                    + ", Light Jacket: " + lightJacketName + "; " + lightJacketDescription
                    + ", Gloves: " + glovesName + "; " + glovesDescription
                    + ", Scarf: " + scarfName + "; " + scarfDescription
                    + ", Ear Muffs: " + earMuffsName + "; " + earMuffsDescription
                + ", Heavy Jacket: " + heavyJacketName + "; " + heavyJacketDescription;

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
