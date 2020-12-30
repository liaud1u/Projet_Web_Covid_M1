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

@WebServlet(name = "FriendServlet")
public class AddFriendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");


        SQLConnector connector = new SQLConnector();

        ArrayList<User> users = connector.getUsersSimplify("");

        User me = null;

        for(User user : users){
           if(user.getLogin().equals(((User)request.getSession().getAttribute("user")).getLogin()))
                me = user;
        }

        users.remove(me);

        request.setAttribute("users", users);

        request.getRequestDispatcher("/JSP/addFriend.jsp").forward(request, response);
    }

}
