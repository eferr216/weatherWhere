package edu.matc;

import edu.matc.controller.SearchItems;
import edu.matc.entity.Item;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;

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
        int footwearIntSelection = (int) session.getAttribute("footwearIntSelection");
        int socksIntSelection = (int) session.getAttribute("socksIntSelection");
        int shirtsIntSelection = (int) session.getAttribute("shirtsIntSelection");
        int legwearIntSelection = (int) session.getAttribute("legwearIntSelection");
        int sweatersIntSelection = (int) session.getAttribute("sweatersIntSelection");
        int lightJacketsIntSelection = (int) session.getAttribute("lightJacketsIntSelection");
        int glovesIntSelection = (int) session.getAttribute("glovesIntSelection");
        int scarvesIntSelection = (int) session.getAttribute("scarvesIntSelection");
        int earMuffsIntSelection = (int) session.getAttribute("earMuffsIntSelection");
        int heavyJacketsIntSelection = (int) session.getAttribute("heavyJacketsIntSelection");

        List<Item> footwearList = (List<Item>) session.getAttribute("footwear");
        List<Item> socksList = (List<Item>) session.getAttribute("socks");
        List<Item> shirtsList = (List<Item>) session.getAttribute("shirts");
        List<Item> legwearList = (List<Item>) session.getAttribute("legwear");
        List<Item> sweatersList = (List<Item>) session.getAttribute("sweaters");
        List<Item> lightJacketsList = (List<Item>) session.getAttribute("lightJackets");
        List<Item> glovesList = (List<Item>) session.getAttribute("gloves");
        List<Item> scarvesList = (List<Item>) session.getAttribute("scarves");
        List<Item> earMuffsList = (List<Item>) session.getAttribute("earMuffs");
        List<Item> heavyJacketsList = (List<Item>) session.getAttribute("heavyJackets");

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

        /*String footwearListSelection;
        String socksListSelection;
        String shirtsListSelection;
        String legwearListSelection;
        String sweatersListSelection;
        String lightJacketsListSelection;
        String glovesListSelection;
        String scarvesListSelection;
        String earMuffsListSelection;
        String heavyJacketsListSelection;*/

        // Return a simple message
        String output = "Temp: " + temp + ", Wind Speed: " + windSpeed + ", Footwear: " + /*footwearList
                    + footwearIntSelection +*/ footwearSelection + ", Socks: " + /*socksList + socksIntSelection
                    +*/ socksSelection + ", Shirt: " + /*shirtsList + shirtsIntSelection +*/ shirtsSelection
                    + ", Legwear: " + /*legwearList + legwearIntSelection +*/ legwearSelection
                    + ", Sweater: " + /*sweatersList + sweatersIntSelection +*/ sweatersSelection
                    + ", Light Jacket: " + /*lightJacketsList + lightJacketsIntSelection
                    +*/ lightJacketsSelection + ", Gloves: " + /*glovesList
                    + glovesIntSelection +*/ glovesSelection + ", Scarf: "
                    + /*scarvesList + scarvesIntSelection +*/ scarvesSelection
                    + ", Ear Muffs: " + /*earMuffsList + earMuffsIntSelection +*/ earMuffsSelection
                    + ", Heavy Jacket: " + /*heavyJacketsList + heavyJacketsIntSelection
                    +*/ heavyJacketsSelection;

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
