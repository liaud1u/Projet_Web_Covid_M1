package servlet;


import SQLPackage.SQLConnector;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "FriendsServlet")
public class FriendsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");


        SQLConnector connector = new SQLConnector();

        User user;

        User userbis = ((User)request.getSession().getAttribute("user"));

        user = connector.getUserWithoutPass(userbis.getLogin());


        //Mise à jour des amis de l'user dans le cache au cas ou

        request.getSession().setAttribute("user",user);
        request.setAttribute("users",user.getFriend());

        request.getRequestDispatcher("/JSP/friends.jsp").forward(request, response);
    }

}
