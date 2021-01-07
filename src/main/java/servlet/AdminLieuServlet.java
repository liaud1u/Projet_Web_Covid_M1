package servlet;

import SQLPackage.SQLConnector;
import bean.Location;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdminLieuServlet")
public class AdminLieuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        String res = "";

        switch (type) {

            case "getAllLieu": {
                res = getAllLieu(request);
            }
            break;
        }

        response.setContentType("text/plain");
        response.getWriter().write(res);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/JSP/adminLieu.jsp").forward(request, response);
    }

    private String getAllLieu(HttpServletRequest request) {
        String lieu = request.getParameter("lieu");

        SQLConnector connector = new SQLConnector();

        ArrayList<Location> locations = connector.getLocationsSimplify(lieu);
        StringBuffer stringBuffer = new StringBuffer();

        for (Location location : locations) {
            stringBuffer.append(location.getName());
            stringBuffer.append("|");
        }


        return stringBuffer.toString();
    }
}
