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
import java.util.Map;

@WebServlet(name = "FriendServlet")
public class RechercheMembre extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Map<String , String[] > parameter = request.getParameterMap();

        SQLConnector connector = new SQLConnector();
        connector.connect();

        String critaire = parameter.get("critaire")[0];



        ArrayList<User> users = connector.getUsers(critaire);

        request.setAttribute("users", users);

        request.getRequestDispatcher("/ajax/rechercheMembre.jsp").forward(request, response);
    }

}
