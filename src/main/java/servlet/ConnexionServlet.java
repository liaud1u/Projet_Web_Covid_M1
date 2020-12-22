package servlet;

import SQLPackage.SQLConnector;
import bean.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

@WebServlet(name = "ConnexionServlet")
public class ConnexionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();

        if (verificationRegex(login, password)) {
            SQLConnector sqlConnector = new SQLConnector();
            User user = sqlConnector.getUser(login, password);

            if (!(user == null)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                out.print(true);

            } else {
                out.print(false);
            }
            
        } else {
            out.print(false);
        }

        out.flush();
        out.close();
    }
    //TODO: affichage d'erreur si pas bon identifiant ou mot de passe
    //TODO: Verification que le login n'est pas vide ou le mot de passe

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/JSP/connexion.jsp").forward(request, response);
    }

    private boolean verificationRegex(String login, String password) {

        return login.matches("^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{3,19}$") &&
                password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$");
    }

}
