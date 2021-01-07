package servlet;

import SQLPackage.SQLConnector;
import bean.Lieu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ChoixLieuServlet")
public class ChoixLieuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");


        SQLConnector connector = new SQLConnector();

        ArrayList<Lieu> lieux = connector.getLocations("");


        request.setAttribute("lieux", lieux);

        request.getRequestDispatcher("/JSP/choixlieu.jsp").forward(request, response);
    }
}
