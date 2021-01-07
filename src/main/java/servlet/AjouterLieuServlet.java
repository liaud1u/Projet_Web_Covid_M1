package servlet;

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
import java.util.Date;

@WebServlet(name = "AjouterLieuServlet")
public class AjouterLieuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Lieu lieu = new Lieu(request.getParameter("nom"),request.getParameter("adresse"));
        lieu.create();



        SelectionLieuServlet.createActivity(request, response, request.getParameter("nom"));
        request.getRequestDispatcher("Activites").forward(request, response);
    }
}
