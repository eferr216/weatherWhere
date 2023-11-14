package edu.matc.controller;

import edu.matc.persistence.ItemDao;
import edu.matc.persistence.ItemData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A simple servlet to welecome the user
 */

@WebServlet(
        urlPatterns = {"/searchItem"}
)
public class SearchItems extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //ItemData itemData = new ItemData();
        ItemDao itemDao = new ItemDao();

        if (req.getParameter("submit").equals("search")) {
            req.setAttribute("items", itemDao.getItemsByCategory());
        }
        else {
            req.setAttribute("items", itemDao.getAllItems());
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, res);
    }
}
