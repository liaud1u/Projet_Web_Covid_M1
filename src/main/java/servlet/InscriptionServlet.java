package servlet;

import SQLPackage.SQLConnector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "InscriptionServlet")
public class InscriptionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String lastname = request.getParameter("lastname");
        String firstname = request.getParameter("firstname");
        String birthdate = request.getParameter("birthdate");

        SQLConnector sqlConnector = new SQLConnector();
        boolean res = sqlConnector.insertUser(login, password, lastname, firstname, birthdate);
        System.out.println("Resultat de l'insert : " + res);
    }
    //TODO: Verification des champs dans la partie serveur
    //TODO: Hashage du mot de passe
    //TODO: Redirection vers une page connexion si inscription réussie
    //TODO: Si inscription echec alors indiquer un message d'erreur sur la page.
    //TODO: Regarder si possible de verifier que le login n'existe déjà pas en ajax
    //TODO: Formater la date pour que ça rentre dans le format date de SQL
    //TODO: Gerer l'ajout de photo

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/JSP/inscription.jsp").forward(request, response);

    }
}
