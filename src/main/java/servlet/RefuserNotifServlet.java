package servlet;


import bean.Notification;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RefuserNotifServlet")
public class RefuserNotifServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        User user = (User)(request.getSession().getAttribute("user"));

        Notification notificationConcerne = null;

        for(Notification n : user.getNotifications()){
            if(n.getId()==Integer.valueOf(request.getParameter("id"))){
                notificationConcerne = n;
            }
        }

        notificationConcerne.refuse();

    }
}
