package servlet;


import SQLPackage.SQLConnector;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet(name = "RechercheAmiServlet")
public class RechercheAmiServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Map<String , String[] > parameter = request.getParameterMap();

        SQLConnector connector = new SQLConnector();

        String critaire = parameter.get("critaire")[0];

        ArrayList<User> friendToShow = new ArrayList<>();

        for(User f :  ((User)request.getSession().getAttribute("user")).getFriend()){
            if(f.getLogin().contains(critaire))
                friendToShow.add(f);
        }

        request.setAttribute("users", friendToShow);

        request.getRequestDispatcher("/ajax/afficheMembre.jsp").forward(request, response);
    }

}
