package edu.matc.rest;

import edu.matc.entity.Item;
import edu.matc.persistence.GenericDao;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * This class holds all operations and properties associated with generated outfits.
 */
public class Outfit {

    /**
     * Instantiates a new Outfit
     */
    public Outfit() {
    }

    /**
     * This method creates and returns the String that will be output
     * when the OutfitAPI is called.
     * @param req the servlet request
     * @return the API's output String
     */
    public String getApiOutputString(@Context HttpServletRequest req) {
        HttpSession session = req.getSession();
        GenericDao itemGenericDao = new GenericDao(Item.class);

        String temp = (String) session.getAttribute("temperature");
        StringBuffer buffer = new StringBuffer(temp);
        buffer.delete(temp.length() - 3, temp.length());
        temp = buffer.toString();
        int tempInt = Integer.parseInt(temp);

        String windSpeed = (String) session.getAttribute("windSpeed");
        /*StringBuffer bufferTwo = new StringBuffer(windSpeed);
        bufferTwo.delete(windSpeed.length() - 3, windSpeed.length());
        windSpeed = bufferTwo.toString();
        int windSpeedInt = Integer.parseInt(windSpeed);*/

        String output = "<html lang='en'><head><title>" + "Weather Where" + "</title>"
                + "<meta charset='UTF-8'>"
                + "    <meta name='viewport' content='width=device-width, initial-scale=1'>"
                + "</head><body>"
                + "<main><div><h1>" + "Outfit Recommendation" + "</h1>"
                + "<div><p><span>Temperature: </span>" + temp + "</p>"
                + "<p><span>Wind Speed: </span>" + windSpeed + "</p></div><br>";

        if (tempInt < 65) {
            List<Item> sweaters = itemGenericDao.getByPropertyEqual("itemCategory", "Sweaters");
            int sweatersIntSelection = itemGenericDao.getRandomInt(sweaters.size());
            Item sweatersSelection = sweaters.get(sweatersIntSelection);
            JSONObject sweaterObject = new JSONObject(sweatersSelection);

            String sweaterName = sweaterObject.getString("itemName");
            String sweaterDescription = sweaterObject.getString("itemDescription");

            output += "<p><span>Sweater: </span>" + sweaterName + "; " + sweaterDescription + "</p>";

        }
        if (tempInt < 45) {
            List<Item> lightJackets = itemGenericDao.getByPropertyEqual("itemCategory", "Light jackets");
            int lightJacketsIntSelection = itemGenericDao.getRandomInt(lightJackets.size());
            Item lightJacketsSelection = lightJackets.get(lightJacketsIntSelection);
            JSONObject lightJacketObject = new JSONObject(lightJacketsSelection);

            String lightJacketName = lightJacketObject.getString("itemName");
            String lightJacketDescription = lightJacketObject.getString("itemDescription");

            output += "<p><span>Light Jacket: </span>" + lightJacketName + "; " + lightJacketDescription + "</p>";

        }
        if (tempInt < 26) {
            List<Item> gloves = itemGenericDao.getByPropertyEqual("itemCategory", "Gloves");
            List<Item> scarves = itemGenericDao.getByPropertyEqual("itemCategory", "Scarves");
            List<Item> earMuffs = itemGenericDao.getByPropertyEqual("itemCategory", "Ear muffs");
            List<Item> heavyJackets = itemGenericDao.getByPropertyEqual("itemCategory", "Heavy jackets");

            int glovesIntSelection = itemGenericDao.getRandomInt(gloves.size());
            int scarvesIntSelection = itemGenericDao.getRandomInt(scarves.size());
            int earMuffsIntSelection = itemGenericDao.getRandomInt(earMuffs.size());
            int heavyJacketsIntSelection = itemGenericDao.getRandomInt(heavyJackets.size());

            Item glovesSelection = gloves.get(glovesIntSelection);
            Item scarvesSelection = scarves.get(scarvesIntSelection);
            Item earMuffsSelection = earMuffs.get(earMuffsIntSelection);
            Item heavyJacketsSelection = heavyJackets.get(heavyJacketsIntSelection);

            JSONObject glovesObject = new JSONObject(glovesSelection);
            JSONObject scarfObject = new JSONObject(scarvesSelection);
            JSONObject earMuffsObject = new JSONObject(earMuffsSelection);
            JSONObject heavyJacketObject = new JSONObject(heavyJacketsSelection);

            String glovesName = glovesObject.getString("itemName");
            String glovesDescription = glovesObject.getString("itemDescription");

            output += "<p><span>Gloves: </span>" + glovesName + "; " + glovesDescription + "</p>";

            String scarfName = scarfObject.getString("itemName");
            String scarfDescription = scarfObject.getString("itemDescription");

            output += "<p><span>Scarf: </span>" + scarfName + "; " + scarfDescription + "</p>";

            String earMuffsName = earMuffsObject.getString("itemName");
            String earMuffsDescription = earMuffsObject.getString("itemDescription");

            output += "<p><span>Ear Muffs: </span>" + earMuffsName + "; " + earMuffsDescription + "</p>";

            String heavyJacketName = heavyJacketObject.getString("itemName");
            String heavyJacketDescription = heavyJacketObject.getString("itemDescription");

            output += "<p><span>Heavy Jacket: </span>" + heavyJacketName + "; " + heavyJacketDescription + "</p>";

        }

        // Build a list of all default Items
        List<Item> footwear = itemGenericDao.getByPropertyEqual("itemCategory", "Footwear");
        List<Item> socks = itemGenericDao.getByPropertyEqual("itemCategory", "Socks");
        List<Item> shirts = itemGenericDao.getByPropertyEqual("itemCategory", "Shirts");
        List<Item> legwear = itemGenericDao.getByPropertyEqual("itemCategory", "Legwear");

        // Randomly generated number will determine which Item is picked
        int footwearIntSelection = itemGenericDao.getRandomInt(footwear.size());
        int socksIntSelection = itemGenericDao.getRandomInt(socks.size());
        int shirtsIntSelection = itemGenericDao.getRandomInt(shirts.size());
        int legwearIntSelection = itemGenericDao.getRandomInt(legwear.size());

        // Get the chosen item from each category
        Item footwearSelection = footwear.get(footwearIntSelection);
        Item socksSelection = socks.get(socksIntSelection);
        Item shirtsSelection = shirts.get(shirtsIntSelection);
        Item legwearSelection = legwear.get(legwearIntSelection);

        JSONObject footwearObject = new JSONObject(footwearSelection);
        JSONObject socksObject = new JSONObject(socksSelection);
        JSONObject shirtsObject = new JSONObject(shirtsSelection);
        JSONObject legwearObject = new JSONObject(legwearSelection);

        String footwearName = footwearObject.getString("itemName");
        String footwearDescription = footwearObject.getString("itemDescription");

        String socksName = socksObject.getString("itemName");
        String socksDescription =socksObject.getString("itemDescription");

        String shirtName = shirtsObject.getString("itemName");
        String shirtDescription = shirtsObject.getString("itemDescription");

        String legwearName = legwearObject.getString("itemName");
        String legwearDescription = legwearObject.getString("itemDescription");

        // Return a simple message
        output += "<div><p><span>Footwear: </span>" + footwearName + "; " + footwearDescription + "</p>"
                + "<p><span>Socks: </span>" + socksName + "; " + socksDescription + "</p>"
                + "<p><span>Shirt: </span>" + shirtName + "; " + shirtDescription + "</p>"
                + "<p><span>Legwear: </span>" + legwearName + "; " + legwearDescription + "</p>"
                + "</div></div></main></body></html>";

        return output;
    }

}