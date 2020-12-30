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

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String lastname = request.getParameter("lastname");
        String firstname = request.getParameter("firstname");
        String birthdate = request.getParameter("birthdate");
        SQLConnector sqlConnector = new SQLConnector();
        if (verificationRegex(login, password, lastname, firstname, birthdate)) {
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


    }
    //TODO: Regarder si possible de verifier que le login n'existe déjà pas en ajax (priorité faible)
    //TODO: Formater la date pour que ça rentre dans le format date de SQL (priorité primordiale) : peut être pas besoin
    //TODO: Gerer l'ajout de photo (priorité faible)

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/JSP/inscription.jsp").forward(request, response);

    }

    private boolean verificationRegex(String login, String password, String lastname, String firstname, String birthdate) {

        return login.matches("^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{3,19}$") &&
                password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$") &&
                lastname.matches("^([A-Z][a-z]+([ ]?[a-z]?[\'-]?[A-Z][a-z]+)*)$") &&
                firstname.matches("^([A-Z][a-z]+([ ]?[a-z]?[\'-]?[A-Z][a-z]+)*)$") &&
                birthdate.matches("^(((0[1-9]|[12][0-9]|3[01])[- /.](0[13578]|1[02])|(0[1-9]|[12][0-9]|30)[- /.](0[469]|11)|(0[1-9]|1\\d|2[0-8])[- /.]02)[- /.]\\d{4}|29[- /.]02[- /.](\\d{2}(0[48]|[2468][048]|[13579][26])|([02468][048]|[1359][26])00))$");

    }

}
