package servlet;

import SQLPackage.SQLConnector;
import bean.Notification;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AjoutAmiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");


        User envoyeur = (User) request.getSession().getAttribute("user");
        String destinataire =  (String) request.getParameter("nom");

        Notification notification = new Notification(envoyeur.getLogin()+" ("+envoyeur.getFirstname()+" "+envoyeur.getLastname()+") vous a envoyé une invitation à devenir amis !",envoyeur.getLogin(),destinataire);
        notification.create();
    }
}
