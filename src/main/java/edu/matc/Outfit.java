package edu.matc;

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
    @Produces("text/html")
    public Response getMesssage(@Context HttpServletRequest req) {
        HttpSession session = req.getSession();
        String temp = (String) session.getAttribute("temperature");
        String windSpeed = (String) session.getAttribute("windSpeed");

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
        String output = "<html lang='en'><head><title>" + "Weather Where" + "</title>"
                + "<meta charset='UTF-8'>"
                + "    <meta name='viewport' content='width=device-width, initial-scale=1'>"
                + "    <link rel='stylesheet' href='css/styles.css'>"
                + "</head><body>"
                + "<main><div class='mainContent'><h1>" + "Outfit Recommendation" + "</h1>"
                + "<div class='outfitRecInfoDiv'><p><span class='outfitRecInfoLabel'>Temperature: </span>" + temp + "</p>"
                + "<p><span class='outfitRecInfoLabel'>Wind Speed: </span>" + windSpeed + "</p></div><br>"
                + "<div class='outfitRecInfoDiv'><p><span class='outfitRecInfoLabel'>Footwear: </span>" + footwearName + "; " + footwearDescription + "</p>"
                + "<p><span class='outfitRecInfoLabel'>Socks: </span>" + socksName + "; " + socksDescription + "</p>"
                + "<p><span class='outfitRecInfoLabel'>Shirt: </span>" + shirtName + "; " + shirtDescription + "</p>"
                + "<p><span class='outfitRecInfoLabel'>Legwear: </span>" + legwearName + "; " + legwearDescription + "</p>"
                + "<p><span class='outfitRecInfoLabel'>Sweater: </span>" + sweaterName + "; " + sweaterDescription + "</p>"
                + "<p><span class='outfitRecInfoLabel'>Light Jacket: </span>" + lightJacketName + "; " + lightJacketDescription + "</p>"
                + "<p><span class='outfitRecInfoLabel'>Gloves: </span>" + glovesName + "; " + glovesDescription + "</p>"
                + "<p><span class='outfitRecInfoLabel'>Scarf: </span>" + scarfName + "; " + scarfDescription + "</p>"
                + "<p><span class='outfitRecInfoLabel'>Ear Muffs: </span>" + earMuffsName + "; " + earMuffsDescription + "</p>"
                + "<p><span class='outfitRecInfoLabel'>Heavy Jacket: </span>" + heavyJacketName + "; " + heavyJacketDescription + "</p></div></div></main></body></html>";

        return Response.status(200).entity(output).build();
    }

}
