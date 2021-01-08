package servlet;

import SQLPackage.PasswordHash;
import SQLPackage.SQLConnector;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet(name = "ProfilServlet")
public class ProfilServlet extends HttpServlet {
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
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");

                if (!(user == null)) {
                    int admin = user.isAdmin() ? 1:0;
                    int positif = user.isPositif() ? 1:0;
                    boolean res = sqlConnector.updateUser(user.getId(), login, passwordHash, lastname, firstname, birthdate, admin, positif);
                    if (res) {
                        session.setAttribute("user", sqlConnector.getUser(login, password));
                    }

                    PrintWriter out = response.getWriter();
                    out.print(res);
                    out.flush();
                    out.close();
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/JSP/profil.jsp").forward(request, response);
    }

    private boolean verificationRegex(String login, String password, String lastname, String firstname, String birthdate) {

        return login.matches("^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{3,19}$") &&
                password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$") &&
                lastname.matches("^([A-Z][a-z]+([ ]?[a-z]?[\'-]?[A-Z][a-z]+)*)$") &&
                firstname.matches("^([A-Z][a-z]+([ ]?[a-z]?[\'-]?[A-Z][a-z]+)*)$") &&
                birthdate.matches("^(((0[1-9]|[12][0-9]|3[01])[- /.](0[13578]|1[02])|(0[1-9]|[12][0-9]|30)[- /.](0[469]|11)|(0[1-9]|1\\d|2[0-8])[- /.]02)[- /.]\\d{4}|29[- /.]02[- /.](\\d{2}(0[48]|[2468][048]|[13579][26])|([02468][048]|[1359][26])00))$");

    }
    
}
