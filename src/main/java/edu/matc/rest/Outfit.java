package edu.matc.rest;

import com.eferreira.persistence.WeatherDao;
import edu.matc.entity.Item;
import edu.matc.persistence.GenericDao;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * This class holds all operations and properties associated with generated outfits.
 */
public class Outfit {
    GenericDao itemGenericDao;
    String output = "";

    JSONObject mainObject;
    int windSpeed;
    int temperature;

    List<Item> footwear;
    List<Item> socks;
    List<Item> shirts;
    List<Item> legwear;
    List<Item> sweaters;
    List<Item> lightJackets;
    List<Item> gloves;
    List<Item> scarves;
    List<Item> earMuffs;
    List<Item> heavyJackets;
    List<Item>[] allDefaultItemLists;
    List<Item>[] allDynamicItemLists;
    /////////////////////////////
    JSONObject footwearObject;
    JSONObject socksObject;
    JSONObject shirtsObject;
    JSONObject legwearObject;
    JSONObject sweaterObject;
    JSONObject lightJacketObject;
    JSONObject glovesObject;
    JSONObject scarfObject;
    JSONObject earMuffsObject;
    JSONObject heavyJacketObject;
    ////////////////////////////////
    String footwearName;
    String footwearDescription;
    String socksName;
    String socksDescription;
    String shirtName;
    String shirtDescription;
    String legwearName;
    String legwearDescription;
    String sweaterName;
    String sweaterDescription;
    String lightJacketName;
    String lightJacketDescription;
    String glovesName;
    String glovesDescription;
    String scarfName;
    String scarfDescription;
    String earMuffsName;
    String earMuffsDescription;
    String heavyJacketName;
    String heavyJacketDescription;

    /**
     * Instantiates a new Outfit
     */
    public Outfit() {
        itemGenericDao = new GenericDao(Item.class);
    }

    /**
     * This method creates and returns the String that will be output
     * when the OutfitAPI is called and no parameters are passed to it.
     * @return the API's output String
     */
    public String getRandomOutfit() {
        buildDefaultItemLists();

        getDefaultItemNames();

        getDefaultItemDescriptions();

        output += "<html lang='en'><head><title>" + "Weather Where" + "</title>"
                + "<meta charset='UTF-8'>"
                + "    <meta name='viewport' content='width=device-width, initial-scale=1'>"
                + "</head><body>"
                + "<main><div><h1>" + "Outfit Recommendation" + "</h1>"
                + "<br>";

        outputDefaultCategoryItems();

        output += "</div></div></main></body></html>";

        return output;
    }

    /**
     * This method takes an x and y coordinate which it then passes to an API to retrieve weather data.
     * @param x an x coordinate
     * @param y a y coordinate
     * @return a String containing an outfit recommendation
     */
    public String getOutfitUsingCoordinates(String x, String y) {
        WeatherDao weatherDao = new WeatherDao();

        // Get the weather forecast JSON object from the WeatherDao
        mainObject = weatherDao.getMainJsonObject(x, y);

        windSpeed = getWindSpeed(mainObject);

        temperature = getTemperature(mainObject);

        String outfit = getOutfitUsingTemperature();

        return outfit;
    }

    public int getWindSpeed(JSONObject mainObject) {
        // Get wind JSON object
        JSONObject windObject = mainObject.getJSONObject("wind");
        int windSpeed = Math.round(windObject.getInt("speed"));
        return windSpeed;
    }

    public int getTemperature(JSONObject mainObject) {
        // Get temperature JSON object
        JSONObject tempObject = mainObject.getJSONObject("main");
        int temperature = Math.round(tempObject.getInt("temp"));
        return temperature;
    }

    /**
     * This method uses the temperature and wind speed to generate an outfit.
     * @return the text output
     */
    private String getOutfitUsingTemperature() {
        outputTempAndWindSpeed();

        buildDefaultItemLists();

        pickDefaultItems();

        buildDynamicItemLists();

        pickDynamicItems();

        getDefaultItemDetails();

        outputDefaultCategoryItems();

        return output;
    }

    /**
     * This method retrieves the items that are dynamically generated.
     */
    private void pickDynamicItems() {
        if (!footwear.isEmpty() && !socks.isEmpty() && !shirts.isEmpty() && !legwear.isEmpty()) {
            getDynamicItems();

            getDynamicItemDetails();

            outputDynamicItemsOutput();
        }
    }

    /**
     * This method adds some html markup to the output
     * as well as the temperature and wind speed.
     */
    private void outputTempAndWindSpeed() {
        // Output Section
        output += "<html lang='en'><head><title>" + "Weather Where" + "</title>"
                + "<meta charset='UTF-8'>"
                + "    <meta name='viewport' content='width=device-width, initial-scale=1'>"
                + "</head><body>"
                + "<main><div><h1>" + "Outfit Recommendation" + "</h1>"
                + "<div><p><span>Temperature: </span>" + temperature + "&deg</p>"
                + "<p><span>Wind Speed: </span>" + windSpeed + " MPH</p></div><br>";
    }

    /**
     * This method populates the default Item Lists
     * and populates the allDefaultItemLists<>[] ArrayList.
     */
    public void buildDefaultItemLists() {
        allDefaultItemLists = new ArrayList[4];

        footwear = itemGenericDao.getByPropertyEqual("itemCategory", "Footwear");
        socks = itemGenericDao.getByPropertyEqual("itemCategory", "Socks");
        shirts = itemGenericDao.getByPropertyEqual("itemCategory", "Shirts");
        legwear = itemGenericDao.getByPropertyEqual("itemCategory", "Legwear");

        allDefaultItemLists[0] = footwear;
        allDefaultItemLists[1] = socks;
        allDefaultItemLists[2] = shirts;
        allDefaultItemLists[3] = legwear;
    }

    /**
     * This method populates the dynamic Item Lists based upon temperature.
     */
    public void buildDynamicItemLists() {
        allDynamicItemLists = new List[6];
        if (temperature < 65) {
            sweaters = itemGenericDao.getByPropertyEqual("itemCategory", "Sweaters");
        }
        if (temperature < 45) {
            lightJackets = itemGenericDao.getByPropertyEqual("itemCategory", "Light jackets");
        }
        if (temperature < 26) {
            gloves = itemGenericDao.getByPropertyEqual("itemCategory", "Gloves");
            scarves = itemGenericDao.getByPropertyEqual("itemCategory", "Scarves");
            earMuffs = itemGenericDao.getByPropertyEqual("itemCategory", "Ear muffs");
            heavyJackets = itemGenericDao.getByPropertyEqual("itemCategory", "Heavy jackets");
        }
    }

    /**
     * This method uses a randomly generated number to pick 1 item from each of the default Items.
     */
    public void pickDefaultItems() {
        // Randomly generated number will determine which Item is picked
        int footwearIntSelection = itemGenericDao.getRandomInt(footwear.size());
        int socksIntSelection = itemGenericDao.getRandomInt(socks.size());
        int shirtsIntSelection = itemGenericDao.getRandomInt(shirts.size());
        int legwearIntSelection = itemGenericDao.getRandomInt(legwear.size());

        // Get the chosen item from each category
        // using the randomly generated number
        Item footwearSelection = footwear.get(footwearIntSelection);
        Item socksSelection = socks.get(socksIntSelection);
        Item shirtsSelection = shirts.get(shirtsIntSelection);
        Item legwearSelection = legwear.get(legwearIntSelection);

        // Convert to a JSON object
        footwearObject = new JSONObject(footwearSelection);
        socksObject = new JSONObject(socksSelection);
        shirtsObject = new JSONObject(shirtsSelection);
        legwearObject = new JSONObject(legwearSelection);

    }

    /**
     * This method uses a random number to select 1 item from each (dynamic) item category.
     */
    private void getDynamicItems() {
        // Randomly generated number will determine which Item is picked
        int sweatersIntSelection = itemGenericDao.getRandomInt(sweaters.size());
        int lightJacketsIntSelection = itemGenericDao.getRandomInt(lightJackets.size());
        int glovesIntSelection = itemGenericDao.getRandomInt(gloves.size());
        int earMuffsIntSelection = itemGenericDao.getRandomInt(earMuffs.size());
        int scarvesIntSelection = itemGenericDao.getRandomInt(scarves.size());
        int heavyJacketsIntSelection = itemGenericDao.getRandomInt(heavyJackets.size());

        // Get the chosen item from each category
        // using the randomly generated number
        Item sweatersSelection = sweaters.get(sweatersIntSelection);
        Item lightJacketsSelection = lightJackets.get(lightJacketsIntSelection);
        Item glovesSelection = gloves.get(glovesIntSelection);
        Item scarvesSelection = scarves.get(scarvesIntSelection);
        Item earMuffsSelection = earMuffs.get(earMuffsIntSelection);
        Item heavyJacketsSelection = heavyJackets.get(heavyJacketsIntSelection);

        // Convert to a JSON object
        sweaterObject = new JSONObject(sweatersSelection);
        lightJacketObject = new JSONObject(lightJacketsSelection);
        glovesObject = new JSONObject(glovesSelection);
        scarfObject = new JSONObject(scarvesSelection);
        earMuffsObject = new JSONObject(earMuffsSelection);
        heavyJacketObject = new JSONObject(heavyJacketsSelection);
    }

    /**
     * This method gets the default items' name and description attributes.
     */
    private void getDefaultItemDetails() {
        getDefaultItemNames();

        getDefaultItemDescriptions();
    }

    /**
     * This method gets the dynamic items' name and description attributes.
     */
    private void getDynamicItemDetails() {
        getDynamicItemNames();

        getDynamicItemDescriptions();
    }

    /**
     * This method gets the name attribute of the dynamic items.
     */
    private void getDefaultItemNames() {
        footwearName = footwearObject.getString("itemName");
        socksName = socksObject.getString("itemName");
        shirtName = shirtsObject.getString("itemName");
        legwearName = legwearObject.getString("itemName");
    }

    /**
     * This method gets the description attribute of the dynamic items.
     */
    private void getDefaultItemDescriptions() {
        footwearDescription = footwearObject.getString("itemDescription");
        socksDescription =socksObject.getString("itemDescription");
        shirtDescription = shirtsObject.getString("itemDescription");
        legwearDescription = legwearObject.getString("itemDescription");
    }

    /**
     * This method gets the name attribute of the dynamic items.
     */
    private void getDynamicItemNames() {
        sweaterName = sweaterObject.getString("itemName");
        lightJacketName = lightJacketObject.getString("itemName");
        glovesName = glovesObject.getString("itemName");
        scarfName = scarfObject.getString("itemName");
        earMuffsName = earMuffsObject.getString("itemName");
        heavyJacketName = heavyJacketObject.getString("itemName");
    }

    /**
     * This method gets the description attribute of the dynamic items.
     */
    private void getDynamicItemDescriptions() {
        sweaterDescription = sweaterObject.getString("itemDescription");
        lightJacketDescription = lightJacketObject.getString("itemDescription");
        glovesDescription = glovesObject.getString("itemDescription");
        scarfDescription = scarfObject.getString("itemDescription");
        earMuffsDescription = earMuffsObject.getString("itemDescription");
        heavyJacketDescription = heavyJacketObject.getString("itemDescription");
    }

    /**
     * This method adds the default items to the html output.
     */
    private void outputDefaultCategoryItems() {
        output += "<div><p><span>Footwear: </span>" + footwearName + "; " + footwearDescription + "</p>"
                + "<p><span>Socks: </span>" + socksName + "; " + socksDescription + "</p>"
                + "<p><span>Shirt: </span>" + shirtName + "; " + shirtDescription + "</p>"
                + "<p><span>Legwear: </span>" + legwearName + "; " + legwearDescription + "</p>";
    }

    /**
     * This method adds the dynamic items to the html output.
     */
    private void outputDynamicItemsOutput() {
        output += "<p><span>Sweater: </span>" + sweaterName + "; " + sweaterDescription + "</p>"
            + "<p><span>Light Jacket: </span>" + lightJacketName + "; " + lightJacketDescription + "</p>"
            + "<p><span>Gloves: </span>" + glovesName + "; " + glovesDescription + "</p>"
            + "<p><span>Scarf: </span>" + scarfName + "; " + scarfDescription + "</p>"
            + "<p><span>Ear Muffs: </span>" + earMuffsName + "; " + earMuffsDescription + "</p>"
            + "<p><span>Heavy Jacket: </span>" + heavyJacketName + "; " + heavyJacketDescription + "</p>";
    }

    /**
     * This method gets the allDefaultItemLists<Item>[].
     * @return an ArrayList<>
     */
    public List<Item>[] getAllDefaultItemLists() {
        return allDefaultItemLists;
    }

    /**
     * This method gets the allDynamicItemLists<Item>[].
     * @return an ArrayList<>
     */
    public List<Item>[] getAllDynamicItemLists() {
        return allDynamicItemLists;
    }

    /**
     * This method gets the sweaters List<Item>.
     * @return a List<>
     */
    public List<Item> getSweatersList() {
        return sweaters;
    }

    /**
     * This method gets the lightJackets List<Item>.
     * @return a List<>
     */
    public List<Item> getLightJacketsList() {
        return lightJackets;
    }

    /**
     * This method gets the gloves List<Item>.
     * @return a List<>
     */
    public List<Item> getGlovesList() {
        return gloves;
    }

    /**
     * This method gets the scarves List<Item>.
     * @return a List<>
     */
    public List<Item> getScarvesList() {
        return scarves;
    }

    /**
     * This method gets the earMuffs List<Item>.
     * @return a List<>
     */
    public List<Item> getEarMuffsList() {
        return earMuffs;
    }

    /**
     * This method gets the heavyJackets List<Item>.
     * @return a List<>
     */
    public List<Item> getHeavyJacketsList() {
        return heavyJackets;
    }

    /**
     * This method gets the footwearObject.
     * @return a JSONObject
     */
    public JSONObject getFootwearObjectSuccess() {
        return footwearObject;
    }

    /**
     * This method gets the socksObject.
     * @return a JSONObject
     */
    public JSONObject getSocksObjectSuccess() {
        return socksObject;
    }

    /**
     * This method gets the shirtsObject.
     * @return a JSONObject
     */
    public JSONObject getShirtsObjectSuccess() {
        return shirtsObject;
    }

    /**
     * This method gets the legwearObject.
     * @return a JSONObject
     */
    public JSONObject getLegwearObjectSuccess() {
        return legwearObject;
    }

    /**
     * This method gets the lightJacketObject.
     * @return a JSONObject
     */
    public JSONObject getLightJacketObjectSuccess() {
        return lightJacketObject;
    }

    /**
     * This method gets the glovesObject.
     * @return a JSONObject
     */
    public JSONObject getGlovesObjectSuccess() {
        return glovesObject;
    }

    /**
     * This method gets the sweaterObject.
     * @return a JSONObject
     */
    public JSONObject getSweaterObjectSuccess() {
        return sweaterObject;
    }

    /**
     * This method gets the scarfObject.
     * @return a JSONObject
     */
    public JSONObject getScarfObjectSuccess() {
        return scarfObject;
    }

    /**
     * This method gets the earMuffsObject.
     * @return a JSONObject
     */
    public JSONObject getEarMuffsObjectSuccess() {
        return earMuffsObject;
    }

    /**
     * This method gets the heavyJacketObject.
     * @return a JSONObject
     */
    public JSONObject getHeavyJacketObjectSuccess() {
        return heavyJacketObject;
    }

    /**
     * This method gets the footwearName.
     * @return a String
     */
    public String getFootwearName() {
        return footwearName;
    }

    /**
     * This method gets the footwearDescription
     * @return a String
     */
    public String getFootwearDescription() {
        return footwearDescription;
    }

    /**
     * This method gets the socksName.
     * @return a String
     */
    public String getSocksName() {
        return socksName;
    }

    /**
     * This method gets the socksDescription
     * @return a String
     */
    public String getSocksDescription() {
        return socksDescription;
    }

    /**
     * This method gets the shirtName.
     * @return a String
     */
    public String getShirtName() {
        return shirtName;
    }

    /**
     * This method gets the shirtDescription
     * @return a String
     */
    public String getShirtDescription() {
        return shirtDescription;
    }

    /**
     * This method gets the legwearName.
     * @return a String
     */
    public String getLegwearName() {
        return legwearName;
    }

    /**
     * This method gets the legwearDescription
     * @return a String
     */
    public String getLegwearDescription() {
        return legwearDescription;
    }

    /**
     * This method gets the sweaterName.
     * @return a String
     */
    public String getSweaterName() {
        return sweaterName;
    }

    /**
     * This method gets the sweaterDescription.
     * @return a String
     */
    public String getSweaterDescription() {
        return sweaterDescription;
    }

    /**
     * This method gets the lightJacketName.
     * @return a String
     */
    public String getLightJacketName() {
        return lightJacketName;
    }

    /**
     * This method gets the lightJacketDescription.
     * @return a String
     */
    public String getLightJacketDescription() {
        return lightJacketDescription;
    }

    /**
     * This method gets the glovesName.
     * @return a String
     */
    public String getGlovesName() {
        return glovesName;
    }

    /**
     * This method gets the glovesDescription.
     * @return a String
     */
    public String getGlovesDescription() {
        return glovesDescription;
    }

    /**
     * This method gets the scarfName.
     * @return a String
     */
    public String getScarfName() {
        return scarfName;
    }

    /**
     * This method gets the scarfDescription.
     * @return a String
     */
    public String getScarfDescription() {
        return scarfDescription;
    }

    /**
     * This method gets the earMuffsName.
     * @return a String
     */
    public String getEarMuffsName() {
        return earMuffsName;
    }

    /**
     * This method gets the earMuffsDescription.
     * @return a String
     */
    public String getEarMuffsDescription() {
        return earMuffsDescription;
    }

    /**
     * This method gets the heavyJacketName.
     * @return a String
     */
    public String getHeavyJacketName() {
        return heavyJacketName;
    }

    /**
     * This method gets the heavyJacketDescription.
     * @return a String
     */
    public String getHeavyJacketDescription() {
        return heavyJacketDescription;
    }
}