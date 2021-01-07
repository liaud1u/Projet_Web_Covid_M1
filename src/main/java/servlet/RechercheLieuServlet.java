package servlet;

import SQLPackage.SQLConnector;
import bean.Lieu;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(name = "RechercheLieuServlet")
public class RechercheLieuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Map<String , String[] > parameter = request.getParameterMap();

        SQLConnector connector = new SQLConnector();

        String critaire = parameter.get("critaire")[0];

        ArrayList<Lieu> lieux = connector.getLocations(critaire);

        request.setAttribute("lieux", lieux);

        request.getRequestDispatcher("/ajax/afficheLieu.jsp").forward(request, response);
    }
}
