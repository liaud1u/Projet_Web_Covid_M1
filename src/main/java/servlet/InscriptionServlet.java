package servlet;

import SQLPackage.PasswordHash;
import SQLPackage.SQLConnector;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

@WebServlet(name = "InscriptionServlet")
public class InscriptionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MDR");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String lastname = request.getParameter("lastname");
        String firstname = request.getParameter("firstname");
        String birthdate = request.getParameter("birthdate");
        SQLConnector sqlConnector = new SQLConnector();
        try {
            String passwordHash = PasswordHash.createHash(password);
            boolean res = sqlConnector.insertUser(login, passwordHash, lastname, firstname, birthdate);

            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.print(res);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    //TODO: Verification des champs dans la partie serveur (priorité primordiale)
    //TODO: Si inscription echec alors indiquer un message d'erreur sur la page. (priorité primordiale)
    //TODO: Regarder si possible de verifier que le login n'existe déjà pas en ajax (priorité faible)
    //TODO: Formater la date pour que ça rentre dans le format date de SQL (priorité primordiale)
    //TODO: Gerer l'ajout de photo (priorité faible)

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/JSP/inscription.jsp").forward(request, response);

    }

}
