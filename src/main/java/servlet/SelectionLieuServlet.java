package servlet;

import SQLPackage.SQLConnector;
import bean.Activitie;
import bean.Lieu;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet(name = "SelectionLieuServlet")
public class SelectionLieuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        createActivity(request, response, request.getParameter("name"));
        request.getRequestDispatcher("Activites").forward(request, response);
    }

    static void createActivity(HttpServletRequest request, HttpServletResponse response, String lieuN) throws ServletException, IOException {
        LocalDateTime debut = LocalDateTime.parse((CharSequence) request.getSession().getAttribute("startDate"));
        LocalDateTime fin = LocalDateTime.parse((CharSequence)request.getSession().getAttribute("endDate"));

        SQLConnector connector = new SQLConnector();
        Lieu lieu = connector.getLocationByName( lieuN);

        Activitie activitie = new Activitie(lieu, debut, fin, (User)request.getSession().getAttribute("user"));
        activitie.create();

        User user = (User) request.getSession().getAttribute("user");
        user.addActivite(activitie);

    }
}
