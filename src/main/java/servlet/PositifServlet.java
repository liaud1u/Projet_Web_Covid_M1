package servlet;

import SQLPackage.SQLConnector;
import bean.*;
import com.mysql.cj.Session;
import sun.font.CreatedFontTracker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.activation.ActivationID;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;

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

        LocalDateTime timeMinus10Day = LocalDateTime.now();
        timeMinus10Day.minus(10, ChronoUnit.DAYS);

        System.out.println(timeMinus10Day);

        HashSet<User> toNotify = new HashSet<>();

        for(Activitie activitie : user.getActivities()){
            if(activitie.getFinActivitee().isAfter(timeMinus10Day)){
                Lieu lieu = activitie.getLieu();

                ArrayList<Activitie> activitiesFromLieu = connector.getActivitiesByLieu(String.valueOf(lieu.getId()));

                System.out.println(activitiesFromLieu);

                for(Activitie a : activitiesFromLieu){
                    Creneau creneau = new Creneau(activitie.getDebutActivitee(),activitie.getFinActivitee(),a.getDebutActivitee(),a.getFinActivitee());

                    System.out.println(creneau.intersect());

                    if(creneau.intersect()){
                        toNotify.add(a.getUser());
                    }
                }
            }
        }

        toNotify.remove(user);

        for(User u : toNotify){
            System.out.println(u);
            Notification notification = new Notification( "Vous êtes cas contact ! Vous devriez vous faire tester dans un centre de test Covid19.",login,u.getLogin(),false);
            notification.create();
        }

    }
}
