package servlet;

import bean.Lieu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AjouterLieuServlet")
public class AjouterLieuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Lieu lieu = new Lieu(request.getParameter("nom"),request.getParameter("adresse"));
        lieu.create();


        request.getRequestDispatcher("./index").forward(request, response);
    }
}
