package servlet;

import SQLPackage.SQLConnector;
import bean.Activitie;
import bean.Lieu;

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

            case "getLieu": {
                res = getLieu(request);
            }
            break;

            case "getAllActivite": {
                res = getAllActivite(request);
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

        ArrayList<Lieu> locations = connector.getLocations(lieu);
        StringBuffer stringBuffer = new StringBuffer();

        for (Lieu location : locations) {
            stringBuffer.append(location.getName());
            stringBuffer.append("|");
        }


        return stringBuffer.toString();
    }

    private String getLieu(HttpServletRequest request) {
        String name = request.getParameter("name");

        SQLConnector connector = new SQLConnector();
        Lieu lieu = connector.getLocationByName(name);


        return lieu.getName() + "|" + lieu.getAdresse() + "|" + lieu.getId();
    }

    private String getAllActivite(HttpServletRequest request) {
        String id = request.getParameter("id");

        SQLConnector sqlConnector = new SQLConnector();
        ArrayList<Activitie> activities = sqlConnector.getActivitiesByLieu(id);

        StringBuffer stringBuffer = new StringBuffer();

        for (Activitie activitie : activities) {
            stringBuffer.append(activitie.getDebutActiviteeFormatted());
            stringBuffer.append(";");
            stringBuffer.append(activitie.getFinActiviteeFormatted());
            stringBuffer.append(";");
            stringBuffer.append(activitie.getUser().getLogin());
            stringBuffer.append("|");
        }

        return stringBuffer.toString();
    }
}
