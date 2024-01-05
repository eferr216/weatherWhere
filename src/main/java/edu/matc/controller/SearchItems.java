package edu.matc.controller;

import edu.matc.entity.Item;
import edu.matc.entity.ItemNote;
import edu.matc.persistence.ItemDao;
import edu.matc.persistence.ItemNoteDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

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

        ItemDao itemDao = new ItemDao();

        String clickedLink = req.getParameter("link");

        if (req.getParameter("searchZipCode") != null) {
            int userZipCode = Integer.parseInt(req.getParameter("zipCode"));
            String x = "";
            String y = "";

            String bingMapsApiUrl = "http://dev.virtualearth.net/REST/v1/Locations?postalCode=" + userZipCode +"&key=Alanux9ey6vFw_0du1NniRGmnjb6UUF7bZxGP-_XKGRxuBCq98ucjVISngc5s7oL";

            Client client = ClientBuilder.newClient();
            WebTarget target = client.target(bingMapsApiUrl);
            String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

            req.setAttribute("apiResponse", response);

            //

            RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
            dispatcher.forward(req, res);
        }
        else if (clickedLink.equals("clothing")) {
            req.setAttribute("items", itemDao.getAllItems());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/clothes.jsp");
            dispatcher.forward(req, res);
        }
        else if (clickedLink.equals("viewNotes")) {
            int itemId = Integer.parseInt(req.getParameter("item_id"));

            Item selectedItem = itemDao.getById(itemId);

            Set<ItemNote> itemNotesList = selectedItem.getItemNotes();

            req.setAttribute("selectedItem", selectedItem);
            req.setAttribute("itemNotes", itemNotesList);


            RequestDispatcher dispatcher = req.getRequestDispatcher("/itemNotes.jsp");
            dispatcher.forward(req, res);
        }
        else {
            req.setAttribute("items", itemDao.getAllItems());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
            dispatcher.forward(req, res);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        ItemDao itemDao = new ItemDao();
        ItemNoteDao itemNoteDao = new ItemNoteDao();

        if (req.getParameter("delete") != null) {
            int idToDelete = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("idToDelete", idToDelete);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/deleteItem.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("edit") != null) {
            int idToEdit = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("idToEdit", idToEdit);
            Item itemToEdit = itemDao.getById(idToEdit);
            req.setAttribute("itemToEdit", itemToEdit);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/editItem.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("confirmDeleteButton") != null) {
            int idToDelete = Integer.parseInt(req.getParameter("id_to_delete"));
            itemDao.delete(itemDao.getById(idToDelete));

            req.setAttribute("items", itemDao.getAllItems());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/clothes.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("cancelDeleteButton") != null) {
            req.setAttribute("items", itemDao.getAllItems());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/clothes.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("confirmEditButton") != null) {
            int idToEdit = Integer.parseInt(req.getParameter("id_to_edit"));
            String itemName = req.getParameter("itemName");
            String itemDescription = req.getParameter("itemDescription");
            String itemCategory = req.getParameter("itemCategory");

            Item itemToUpdate = itemDao.getById(idToEdit);

            itemToUpdate.setItemName(itemName);
            itemToUpdate.setItemDescription(itemDescription);
            itemToUpdate.setItemCategory(itemCategory);

            itemDao.saveOrUpdate(itemToUpdate);

            req.setAttribute("items", itemDao.getAllItems());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/clothes.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("cancelEditButton") != null) {
            req.setAttribute("items", itemDao.getAllItems());
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

            itemDao.insert(newItem);

            req.setAttribute("items", itemDao.getAllItems());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/clothes.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("insertNoteSubmitButton") != null) {
            int selectedItemId = Integer.parseInt(req.getParameter("selected_item_id"));
            Item selectedItem = itemDao.getById(selectedItemId);

            String noteText = req.getParameter("noteText");

            ItemNote newItemNote = new ItemNote(noteText, selectedItem);
            selectedItem.addItemNote(newItemNote);

            itemNoteDao.insert(newItemNote);

            Set<ItemNote> itemNotesList = selectedItem.getItemNotes();

            req.setAttribute("selectedItem", selectedItem);
            req.setAttribute("itemNotes", itemNotesList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/itemNotes.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("deleteItemNote") != null) {
            int selectedItemId = Integer.parseInt(req.getParameter("selected_item_id"));
            Item selectedItem = itemDao.getById(selectedItemId);

            int idToDelete = Integer.parseInt(req.getParameter("item_note_id"));

            selectedItem.removeItemNote(itemNoteDao.getById(idToDelete));

            itemNoteDao.delete(itemNoteDao.getById(idToDelete));

            Set<ItemNote> itemNotesList = selectedItem.getItemNotes();

            req.setAttribute("selectedItem", selectedItem);
            req.setAttribute("itemNotes", itemNotesList);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/itemNotes.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("editItemNote") != null) {
            int selectedItemId = Integer.parseInt(req.getParameter("selected_item_id"));
            Item selectedItem = itemDao.getById(selectedItemId);

            int idToEdit = Integer.parseInt(req.getParameter("item_note_id"));

            req.setAttribute("selectedItem", selectedItem);
            req.setAttribute("idToEdit", idToEdit);

            ItemNote itemNoteToEdit = itemNoteDao.getById(idToEdit);
            req.setAttribute("itemNoteToEdit", itemNoteToEdit);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/editItemNote.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("confirmEditNoteButton") != null) {
            int selectedItemId = Integer.parseInt(req.getParameter("selected_item_id"));
            Item selectedItem = itemDao.getById(selectedItemId);

            int idToEdit = Integer.parseInt(req.getParameter("note_id_to_edit"));

            String noteText = req.getParameter("noteText");

            ItemNote itemNoteToUpdate = itemNoteDao.getById(idToEdit);

            itemNoteToUpdate.setNoteText(noteText);

            itemNoteDao.saveOrUpdate(itemNoteToUpdate);

            selectedItem.setItemNote(itemNoteToUpdate);

            Set<ItemNote> itemNotesList = selectedItem.getItemNotes();

            req.setAttribute("selectedItem", selectedItem);
            req.setAttribute("itemNotes", itemNotesList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/itemNotes.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("cancelEditNoteButton") != null) {
            int selectedItemId = Integer.parseInt(req.getParameter("selected_item_id"));
            Item selectedItem = itemDao.getById(selectedItemId);

            Set<ItemNote> itemNotesList = selectedItem.getItemNotes();

            req.setAttribute("selectedItem", selectedItem);
            req.setAttribute("itemNotes", itemNotesList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/itemNotes.jsp");
            dispatcher.forward(req, res);
        }
        else {
            req.setAttribute("items", itemDao.getAllItems());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/clothes.jsp");
            dispatcher.forward(req, res);
        }

    }
}
