package edu.matc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.entity.Item;
import edu.matc.entity.ItemNote;
import edu.matc.persistence.GenericDao;
import edu.matc.persistence.ItemDao;
import edu.matc.persistence.ItemNoteDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;

/**
 * A simple servlet to welecome the user
 */

@WebServlet(
        urlPatterns = {"/searchItem"}
)
public class SearchItems extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        //ItemDao itemDao = new ItemDao();
        GenericDao itemGenericDao = new GenericDao(Item.class);

        String clickedLink = req.getParameter("link");

        if (req.getParameter("searchZipCode") != null) {
            int userZipCode = Integer.parseInt(req.getParameter("zipCode"));
            Double x;
            Double y;

            String bingMapsApiUrl = "http://dev.virtualearth.net/REST/v1/Locations?postalCode=" + userZipCode +"&key=Alanux9ey6vFw_0du1NniRGmnjb6UUF7bZxGP-_XKGRxuBCq98ucjVISngc5s7oL";

            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(bingMapsApiUrl);
            String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

            // Traverse the main JSON response to extract the GPS coordinates of the user's zip code
            JSONObject mainJsonObject = new JSONObject(response);
            JSONArray resourceSetsArray = mainJsonObject.getJSONArray("resourceSets");
            JSONObject resourceSetsArrayFirstObject = resourceSetsArray.getJSONObject(0);
            JSONArray resourcesArray = resourceSetsArrayFirstObject.getJSONArray("resources");
            JSONObject resourcesArrayFirstObject = resourcesArray.getJSONObject(0);
            JSONArray geocodePointsArray = resourcesArrayFirstObject.getJSONArray("geocodePoints");
            JSONObject geocodePointsArrayFirstObject = geocodePointsArray.getJSONObject(0);
            JSONArray coordinatesArray = geocodePointsArrayFirstObject.getJSONArray("coordinates");

            // Get coordinates
            x = (Double) coordinatesArray.get(0);
            y = (Double) coordinatesArray.get(1);

            String openWeatherMapKey = "99cb12ee7bf388635c3b8d6538da8e35";

            // Call 2nd API using GPS coordinates
            String nationalWeatherServiceApiUrl = "https://api.openweathermap.org/data/2.5/weather?lat=" + x + "&lon=" + y + "&appid=" + openWeatherMapKey + "&units=imperial";
            target = client.target(nationalWeatherServiceApiUrl);
            response = target.request(MediaType.APPLICATION_JSON).get(String.class);

            JSONObject mainObject = new JSONObject(response);

            JSONObject windObject = mainObject.getJSONObject("wind");
            int windSpeed = Math.round(windObject.getInt("speed"));
            String windSpeedString = windSpeed + " MPH";

            JSONObject tempObject = mainObject.getJSONObject("main");
            int temperature = Math.round(tempObject.getInt("temp"));
            String temperatureString = temperature + " \u00B0" + "F";

            String cityName = mainObject.getString("name");

            req.setAttribute("mainObject", mainObject);
            req.setAttribute("city", cityName);
            //req.setAttribute("temperature", temperatureString);
            //req.setAttribute("windSpeed", windSpeedString);
            HttpSession session = req.getSession();
            session.setAttribute("temperature", temperatureString);
            session.setAttribute("windSpeed", windSpeedString);
            //String userName = (String) session.getAttribute("userName");

            RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
            dispatcher.forward(req, res);
        }
        else if (clickedLink.equals("outfitRecommendation")) {
            HttpSession session = req.getSession();
            String temp = (String) session.getAttribute("temperature");
            String windSpeed = (String) session.getAttribute("windSpeed");

            List<Item> footwear = itemGenericDao.getByPropertyEqual("itemCategory", "Footwear");
            List<Item> socks = itemGenericDao.getByPropertyEqual("itemCategory", "Socks");
            List<Item> shirts = itemGenericDao.getByPropertyEqual("itemCategory", "Shirts");
            List<Item> legwear = itemGenericDao.getByPropertyEqual("itemCategory", "Legwear");
            List<Item> sweaters = itemGenericDao.getByPropertyEqual("itemCategory", "Sweaters");
            List<Item> lightJackets = itemGenericDao.getByPropertyEqual("itemCategory", "Light jackets");
            List<Item> gloves = itemGenericDao.getByPropertyEqual("itemCategory", "Gloves");
            List<Item> scarves = itemGenericDao.getByPropertyEqual("itemCategory", "Scarves");
            List<Item> earMuffs = itemGenericDao.getByPropertyEqual("itemCategory", "Ear muffs");
            List<Item> heavyJackets = itemGenericDao.getByPropertyEqual("itemCategory", "Heavy jackets");

            int footwearIntSelection = itemGenericDao.getRandomInt(footwear.size());
            int socksIntSelection = itemGenericDao.getRandomInt(socks.size());
            int shirtsIntSelection = itemGenericDao.getRandomInt(shirts.size());
            int legwearIntSelection = itemGenericDao.getRandomInt(legwear.size());
            int sweatersIntSelection = itemGenericDao.getRandomInt(sweaters.size());
            int lightJacketsIntSelection = itemGenericDao.getRandomInt(lightJackets.size());
            int glovesIntSelection = itemGenericDao.getRandomInt(gloves.size());
            int scarvesIntSelection = itemGenericDao.getRandomInt(scarves.size());
            int earMuffsIntSelection = itemGenericDao.getRandomInt(earMuffs.size());
            int heavyJacketsIntSelection = itemGenericDao.getRandomInt(heavyJackets.size());

            Item footwearSelection = footwear.get(footwearIntSelection);
            Item socksSelection = socks.get(socksIntSelection);
            Item shirtsSelection = shirts.get(shirtsIntSelection);
            Item legwearSelection = legwear.get(legwearIntSelection);
            Item sweatersSelection = sweaters.get(sweatersIntSelection);
            Item lightJacketsSelection = lightJackets.get(lightJacketsIntSelection);
            Item glovesSelection = gloves.get(glovesIntSelection);
            Item scarvesSelection = scarves.get(scarvesIntSelection);
            Item earMuffsSelection = earMuffs.get(earMuffsIntSelection);
            Item heavyJacketsSelection = heavyJackets.get(heavyJacketsIntSelection);

            /*Item footwearSelection = footwear.get(0);
            Item socksSelection = socks.get(0);
            Item shirtsSelection = shirts.get(0);
            Item legwearSelection = legwear.get(0);
            Item sweatersSelection = sweaters.get(0);
            Item lightJacketsSelection = lightJackets.get(0);
            Item glovesSelection = gloves.get(0);
            Item scarvesSelection = scarves.get(0);
            Item earMuffsSelection = earMuffs.get(0);
            Item heavyJacketsSelection = heavyJackets.get(0);*/

            session.setAttribute("footwear", footwear);
            session.setAttribute("footwearIntSelection", footwearIntSelection);
            session.setAttribute("footwearSelection", footwearSelection);
            session.setAttribute("socks", socks);
            session.setAttribute("socksIntSelection", socksIntSelection);
            session.setAttribute("socksSelection", socksSelection);
            session.setAttribute("shirts", shirts);
            session.setAttribute("shirtsIntSelection", shirtsIntSelection);
            session.setAttribute("shirtsSelection", shirtsSelection);
            session.setAttribute("legwear", legwear);
            session.setAttribute("legwearIntSelection", legwearIntSelection);
            session.setAttribute("legwearSelection", legwearSelection);
            session.setAttribute("sweaters", sweaters);
            session.setAttribute("sweatersIntSelection", sweatersIntSelection);
            session.setAttribute("sweatersSelection", sweatersSelection);
            session.setAttribute("lightJackets", lightJackets);
            session.setAttribute("lightJacketsIntSelection", lightJacketsIntSelection);
            session.setAttribute("lightJacketsSelection", lightJacketsSelection);
            session.setAttribute("gloves", gloves);
            session.setAttribute("glovesIntSelection", glovesIntSelection);
            session.setAttribute("glovesSelection", glovesSelection);
            session.setAttribute("scarves", scarves);
            session.setAttribute("scarvesIntSelection", scarvesIntSelection);
            session.setAttribute("scarvesSelection", scarvesSelection);
            session.setAttribute("earMuffs", earMuffs);
            session.setAttribute("earMuffsIntSelection", earMuffsIntSelection);
            session.setAttribute("earMuffsSelection", earMuffsSelection);
            session.setAttribute("heavyJackets", heavyJackets);
            session.setAttribute("heavyJacketsIntSelection", heavyJacketsIntSelection);
            session.setAttribute("heavyJacketsSelection", heavyJacketsSelection);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/services/outfits");
            dispatcher.forward(req, res);
        }
        else if (clickedLink.equals("clothing")) {
            req.setAttribute("items", itemGenericDao.getAll());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/clothes.jsp");
            dispatcher.forward(req, res);
        }
        else if (clickedLink.equals("viewNotes")) {
            int itemId = Integer.parseInt(req.getParameter("item_id"));

            Item selectedItem = (Item) itemGenericDao.getById(itemId);

            Set<ItemNote> itemNotesList = selectedItem.getItemNotes();

            req.setAttribute("selectedItem", selectedItem);
            req.setAttribute("itemNotes", itemNotesList);


            RequestDispatcher dispatcher = req.getRequestDispatcher("/itemNotes.jsp");
            dispatcher.forward(req, res);
        }
        else {
            //req.setAttribute("items", itemGenericDao.getAll());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, res);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        GenericDao itemGenericDao = new GenericDao(Item.class);
        GenericDao itemNoteGenericDao = new GenericDao(ItemNote.class);

        if (req.getParameter("delete") != null) {
            int idToDelete = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("idToDelete", idToDelete);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/deleteItem.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("edit") != null) {
            int idToEdit = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("idToEdit", idToEdit);
            Item itemToEdit = (Item) itemGenericDao.getById(idToEdit);
            req.setAttribute("itemToEdit", itemToEdit);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/editItem.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("confirmDeleteButton") != null) {
            int idToDelete = Integer.parseInt(req.getParameter("id_to_delete"));
            itemGenericDao.delete(itemGenericDao.getById(idToDelete));

            req.setAttribute("items", itemGenericDao.getAll());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/clothes.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("cancelDeleteButton") != null) {
            req.setAttribute("items", itemGenericDao.getAll());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/clothes.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("confirmEditButton") != null) {
            int idToEdit = Integer.parseInt(req.getParameter("id_to_edit"));
            String itemName = req.getParameter("itemName");
            String itemDescription = req.getParameter("itemDescription");
            String itemCategory = req.getParameter("itemCategory");

            Item itemToUpdate = (Item) itemGenericDao.getById(idToEdit);

            itemToUpdate.setItemName(itemName);
            itemToUpdate.setItemDescription(itemDescription);
            itemToUpdate.setItemCategory(itemCategory);

            itemGenericDao.saveOrUpdate(itemToUpdate);

            req.setAttribute("items", itemGenericDao.getAll());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/clothes.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("cancelEditButton") != null) {
            req.setAttribute("items", itemGenericDao.getAll());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/clothes.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("insertSubmitButton") != null) {
            String itemName = req.getParameter("itemName");
            String itemDescription = req.getParameter("itemDescription");
            String itemCategory = req.getParameter("itemCategory");

            Item newItem = new Item();

            newItem.setItemName(itemName);
            newItem.setItemDescription(itemDescription);
            newItem.setItemCategory(itemCategory);

            itemGenericDao.insert(newItem);

            req.setAttribute("items", itemGenericDao.getAll());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/clothes.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("insertNoteSubmitButton") != null) {
            int selectedItemId = Integer.parseInt(req.getParameter("selected_item_id"));
            Item selectedItem = (Item) itemGenericDao.getById(selectedItemId);

            String noteText = req.getParameter("noteText");

            ItemNote newItemNote = new ItemNote(noteText, selectedItem);
            selectedItem.addItemNote(newItemNote);

            itemNoteGenericDao.insert(newItemNote);

            Set<ItemNote> itemNotesList = selectedItem.getItemNotes();

            req.setAttribute("selectedItem", selectedItem);
            req.setAttribute("itemNotes", itemNotesList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/itemNotes.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("deleteItemNote") != null) {
            int selectedItemId = Integer.parseInt(req.getParameter("selected_item_id"));
            Item selectedItem = (Item) itemGenericDao.getById(selectedItemId);

            int idToDelete = Integer.parseInt(req.getParameter("item_note_id"));

            selectedItem.removeItemNote((ItemNote) itemNoteGenericDao.getById(idToDelete));

            itemNoteGenericDao.delete(itemNoteGenericDao.getById(idToDelete));

            Set<ItemNote> itemNotesList = selectedItem.getItemNotes();

            req.setAttribute("selectedItem", selectedItem);
            req.setAttribute("itemNotes", itemNotesList);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/itemNotes.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("editItemNote") != null) {
            int selectedItemId = Integer.parseInt(req.getParameter("selected_item_id"));
            Item selectedItem = (Item) itemGenericDao.getById(selectedItemId);

            int idToEdit = Integer.parseInt(req.getParameter("item_note_id"));

            req.setAttribute("selectedItem", selectedItem);
            req.setAttribute("idToEdit", idToEdit);

            ItemNote itemNoteToEdit = (ItemNote) itemNoteGenericDao.getById(idToEdit);
            req.setAttribute("itemNoteToEdit", itemNoteToEdit);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/editItemNote.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("confirmEditNoteButton") != null) {
            int selectedItemId = Integer.parseInt(req.getParameter("selected_item_id"));
            Item selectedItem = (Item) itemGenericDao.getById(selectedItemId);

            int idToEdit = Integer.parseInt(req.getParameter("note_id_to_edit"));

            String noteText = req.getParameter("noteText");

            ItemNote itemNoteToUpdate = (ItemNote) itemNoteGenericDao.getById(idToEdit);

            itemNoteToUpdate.setNoteText(noteText);

            itemNoteGenericDao.saveOrUpdate(itemNoteToUpdate);

            selectedItem.setItemNote(itemNoteToUpdate);

            Set<ItemNote> itemNotesList = selectedItem.getItemNotes();

            req.setAttribute("selectedItem", selectedItem);
            req.setAttribute("itemNotes", itemNotesList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/itemNotes.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("cancelEditNoteButton") != null) {
            int selectedItemId = Integer.parseInt(req.getParameter("selected_item_id"));
            Item selectedItem = (Item) itemGenericDao.getById(selectedItemId);

            Set<ItemNote> itemNotesList = selectedItem.getItemNotes();

            req.setAttribute("selectedItem", selectedItem);
            req.setAttribute("itemNotes", itemNotesList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/itemNotes.jsp");
            dispatcher.forward(req, res);
        }
        else {
            req.setAttribute("items", itemGenericDao.getAll());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/clothes.jsp");
            dispatcher.forward(req, res);
        }

    }
}
