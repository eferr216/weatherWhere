package edu.matc.rest;

import edu.matc.entity.Item;
import edu.matc.persistence.GenericDao;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
     * when the OutfitAPI is called and no parameters are passed to it.
     * @return the API's output String
     */
    public String getRandomOutfit() {

        GenericDao itemGenericDao = new GenericDao(Item.class);


        String output = "<html lang='en'><head><title>" + "Weather Where" + "</title>"
                + "<meta charset='UTF-8'>"
                + "    <meta name='viewport' content='width=device-width, initial-scale=1'>"
                + "</head><body>"
                + "<main><div><h1>" + "Outfit Recommendation" + "</h1>"
                + "<br>";

        // Build a list of all default Items
        List<Item> footwear = itemGenericDao.getByPropertyEqual("itemCategory", "Footwear");
        List<Item> socks = itemGenericDao.getByPropertyEqual("itemCategory", "Socks");
        List<Item> shirts = itemGenericDao.getByPropertyEqual("itemCategory", "Shirts");
        List<Item> legwear = itemGenericDao.getByPropertyEqual("itemCategory", "Legwear");
        List<Item> sweaters = itemGenericDao.getByPropertyEqual("itemCategory", "Sweaters");
        List<Item> gloves = itemGenericDao.getByPropertyEqual("itemCategory", "Gloves");
        List<Item> scarves = itemGenericDao.getByPropertyEqual("itemCategory", "Scarves");
        List<Item> earMuffs = itemGenericDao.getByPropertyEqual("itemCategory", "Ear muffs");
        List<Item> heavyJackets = itemGenericDao.getByPropertyEqual("itemCategory", "Heavy jackets");
        List<Item> lightJackets = itemGenericDao.getByPropertyEqual("itemCategory", "Light jackets");

        // Randomly generated number will determine which Item is picked
        int footwearIntSelection = itemGenericDao.getRandomInt(footwear.size());
        int socksIntSelection = itemGenericDao.getRandomInt(socks.size());
        int shirtsIntSelection = itemGenericDao.getRandomInt(shirts.size());
        int legwearIntSelection = itemGenericDao.getRandomInt(legwear.size());
        int sweatersIntSelection = itemGenericDao.getRandomInt(sweaters.size());
        int glovesIntSelection = itemGenericDao.getRandomInt(gloves.size());
        int scarvesIntSelection = itemGenericDao.getRandomInt(scarves.size());
        int earMuffsIntSelection = itemGenericDao.getRandomInt(earMuffs.size());
        int heavyJacketsIntSelection = itemGenericDao.getRandomInt(heavyJackets.size());
        int lightJacketsIntSelection = itemGenericDao.getRandomInt(lightJackets.size());

        // Get the chosen item from each category
        Item footwearSelection = footwear.get(footwearIntSelection);
        Item socksSelection = socks.get(socksIntSelection);
        Item shirtsSelection = shirts.get(shirtsIntSelection);
        Item legwearSelection = legwear.get(legwearIntSelection);
        Item sweatersSelection = sweaters.get(sweatersIntSelection);
        Item glovesSelection = gloves.get(glovesIntSelection);
        Item scarvesSelection = scarves.get(scarvesIntSelection);
        Item earMuffsSelection = earMuffs.get(earMuffsIntSelection);
        Item heavyJacketsSelection = heavyJackets.get(heavyJacketsIntSelection);
        Item lightJacketsSelection = lightJackets.get(lightJacketsIntSelection);

        JSONObject footwearObject = new JSONObject(footwearSelection);
        JSONObject socksObject = new JSONObject(socksSelection);
        JSONObject shirtsObject = new JSONObject(shirtsSelection);
        JSONObject legwearObject = new JSONObject(legwearSelection);
        JSONObject sweaterObject = new JSONObject(sweatersSelection);
        JSONObject glovesObject = new JSONObject(glovesSelection);
        JSONObject scarfObject = new JSONObject(scarvesSelection);
        JSONObject earMuffsObject = new JSONObject(earMuffsSelection);
        JSONObject heavyJacketObject = new JSONObject(heavyJacketsSelection);
        JSONObject lightJacketObject = new JSONObject(lightJacketsSelection);

        String footwearName = footwearObject.getString("itemName");
        String footwearDescription = footwearObject.getString("itemDescription");

        String socksName = socksObject.getString("itemName");
        String socksDescription =socksObject.getString("itemDescription");

        String shirtName = shirtsObject.getString("itemName");
        String shirtDescription = shirtsObject.getString("itemDescription");

        String legwearName = legwearObject.getString("itemName");
        String legwearDescription = legwearObject.getString("itemDescription");

        String sweaterName = sweaterObject.getString("itemName");
        String sweaterDescription = sweaterObject.getString("itemDescription");

        String glovesName = glovesObject.getString("itemName");
        String glovesDescription = glovesObject.getString("itemDescription");

        String scarfName = scarfObject.getString("itemName");
        String scarfDescription = scarfObject.getString("itemDescription");

        String earMuffsName = earMuffsObject.getString("itemName");
        String earMuffsDescription = earMuffsObject.getString("itemDescription");

        String heavyJacketName = heavyJacketObject.getString("itemName");
        String heavyJacketDescription = heavyJacketObject.getString("itemDescription");

        String lightJacketName = lightJacketObject.getString("itemName");
        String lightJacketDescription = lightJacketObject.getString("itemDescription");

        // Return a simple message
        output += "<div><p><span>Footwear: </span>" + footwearName + "; " + footwearDescription + "</p>"
                + "<p><span>Socks: </span>" + socksName + "; " + socksDescription + "</p>"
                + "<p><span>Shirt: </span>" + shirtName + "; " + shirtDescription + "</p>"
                + "<p><span>Legwear: </span>" + legwearName + "; " + legwearDescription + "</p>"
                + "<p><span>Sweater: </span>" + sweaterName + "; " + sweaterDescription + "</p>"
                + "<p><span>Gloves: </span>" + glovesName + "; " + glovesDescription + "</p>"
                + "<p><span>Scarf: </span>" + scarfName + "; " + scarfDescription + "</p>"
                + "<p><span>Ear Muffs: </span>" + earMuffsName + "; " + earMuffsDescription + "</p>"
                + "<p><span>Heavy Jacket: </span>" + heavyJacketName + "; " + heavyJacketDescription + "</p>"
                + "<p><span>Light Jacket: </span>" + lightJacketName + "; " + lightJacketDescription + "</p>"
                + "</div></div></main></body></html>";

        return output;
    }

    /**
     * This method takes an x and y coordinate which it then passes to an API to retrieve weather data.
     * @param x an x coordinate
     * @param y a y coordinate
     * @return a String containing an outfit recommendation
     */
    public String getOutfitUsingCoordinates(String x, String y) {

        Client client = ClientBuilder.newClient();

        GenericDao itemGenericDao = new GenericDao(Item.class);

        String openWeatherMapKey = "99cb12ee7bf388635c3b8d6538da8e35";

        // Call 2nd API using GPS coordinates
        String nationalWeatherServiceApiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + x + "&lon=" + y + "&appid=" + openWeatherMapKey + "&units=imperial";
        WebTarget target = client.target(nationalWeatherServiceApiUrl);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        JSONObject mainObject = new JSONObject(response);

        JSONObject windObject = mainObject.getJSONObject("wind");
        int windSpeed = Math.round(windObject.getInt("speed"));
        String windSpeedString = windSpeed + " MPH";

        JSONObject tempObject = mainObject.getJSONObject("main");
        int temperature = Math.round(tempObject.getInt("temp"));



        String output = "<html lang='en'><head><title>" + "Weather Where" + "</title>"
                + "<meta charset='UTF-8'>"
                + "    <meta name='viewport' content='width=device-width, initial-scale=1'>"
                + "</head><body>"
                + "<main><div><h1>" + "Outfit Recommendation" + "</h1>"
                + "<div><p><span>Temperature: </span>" + temperature + "</p>"
                + "<p><span>Wind Speed: </span>" + windSpeedString + "</p></div><br>";

        if (temperature < 65) {
            List<Item> sweaters = itemGenericDao.getByPropertyEqual("itemCategory", "Sweaters");
            int sweatersIntSelection = itemGenericDao.getRandomInt(sweaters.size());
            Item sweatersSelection = sweaters.get(sweatersIntSelection);
            JSONObject sweaterObject = new JSONObject(sweatersSelection);

            String sweaterName = sweaterObject.getString("itemName");
            String sweaterDescription = sweaterObject.getString("itemDescription");

            output += "<p><span>Sweater: </span>" + sweaterName + "; " + sweaterDescription + "</p>";

        }
        if (temperature < 45) {
            List<Item> lightJackets = itemGenericDao.getByPropertyEqual("itemCategory", "Light jackets");
            int lightJacketsIntSelection = itemGenericDao.getRandomInt(lightJackets.size());
            Item lightJacketsSelection = lightJackets.get(lightJacketsIntSelection);
            JSONObject lightJacketObject = new JSONObject(lightJacketsSelection);

            String lightJacketName = lightJacketObject.getString("itemName");
            String lightJacketDescription = lightJacketObject.getString("itemDescription");

            output += "<p><span>Light Jacket: </span>" + lightJacketName + "; " + lightJacketDescription + "</p>";

        }
        if (temperature < 26) {
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