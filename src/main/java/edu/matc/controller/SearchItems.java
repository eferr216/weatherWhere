package edu.matc.controller;

import edu.matc.persistence.ItemDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private final Logger logger = LogManager.getLogger(this.getClass());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        ItemDao itemDao = new ItemDao();
        String clickedLink = req.getParameter("link");

        if (clickedLink.equals("clothing")) {
            req.setAttribute("items", itemDao.getAllItems());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/clothes.jsp");
            dispatcher.forward(req, res);
        }
        else if (req.getParameter("submit").equals("Search")) {
            req.setAttribute("items", itemDao.getItemsByCategory(req.getParameter("/results.jsp")));
        }
        /*else if (req.getParameter("submit").equals("Submit")) {
            req.setAttribute("items", itemDao.insert(req.getParameter("itemCategory")));
        }*/
        else {
            req.setAttribute("items", itemDao.getAllItems());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/results.jsp");
            dispatcher.forward(req, res);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        ItemDao itemDao = new ItemDao();

        String param = req.getParameter("delete_id");
        req.setAttribute("paramAttribute", param);

        if (!req.getParameter("delete_id").isEmpty()) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/deleteItem.jsp");
            dispatcher.forward(req, res);
        }
        else if ("Delete".equals(req.getParameter("submitButton"))) {
            itemDao.delete(itemDao.getById(Integer.parseInt(param)));
            req.setAttribute("items", itemDao.getAllItems());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, res);
        }
        else {
            req.setAttribute("items", itemDao.getAllItems());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/clothes.jsp");
            dispatcher.forward(req, res);
        }

    }
}
