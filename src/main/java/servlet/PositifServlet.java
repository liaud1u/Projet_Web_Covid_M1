package servlet;

import SQLPackage.SQLConnector;
import bean.Notification;
import bean.User;
import com.mysql.cj.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "PositifServlet")
public class PositifServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        SQLConnector connector = new SQLConnector();

        String login = request.getParameter("login");
        User user = connector.getUserWithoutPass(login);

        user.declarerPositif();

        request.getSession().setAttribute("user",user);

        ArrayList<User> friends = user.getFriend();

        for(User f : friends){
            Notification notification = new Notification(login+" a été déclaré positif à la Covid19 ! Vous devriez vous faire tester dans un centre de test Covid19.",login,f.getLogin(),false);
            notification.create();
        }

    }
}
