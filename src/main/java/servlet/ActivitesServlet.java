package servlet;


import SQLPackage.SQLConnector;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "FriendServlet")
public class ActivitesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");


        SQLConnector connector = new SQLConnector();
        connector.connect();

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        System.out.println(user);

        request.getRequestDispatcher("/JSP/activities.jsp").forward(request, response);
    }

}
