package servlet;

import SQLPackage.SQLConnector;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "AdminServlet")
public class AdminUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter("type");
        String res = "";

        switch (type) {

            case "getAllUser": {
                res = getAllUser(request);
            }
            break;

            case "getUser": {
                res = getUser(request);
            }
            break;

            case "getAllFriend": {
                res = getAllFriend(request);
            }
            break;

            case "modifProfil": {
                res = modifUser(request);
            }
            break;

            case "delete": {
                delete(request);
            }
        }

        response.setContentType("text/plain");
        response.getWriter().write(res);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/JSP/adminUser.jsp").forward(request, response);
    }

    private String getAllUser(HttpServletRequest request){
        String login = request.getParameter("login");

        SQLConnector sqlConnector = new SQLConnector();

        ArrayList<User> users = sqlConnector.getAllUser(login);
        StringBuffer stringBuffer = new StringBuffer();

        for (User user : users) {
           stringBuffer.append(user.getLogin());
           stringBuffer.append("/");
        }

        return stringBuffer.toString();
    }

    private String getUser(HttpServletRequest request) {
        String login = request.getParameter("login");

        SQLConnector sqlConnector = new SQLConnector();

        User user = sqlConnector.getUserWithoutPass(login);
        return user.getLogin() + ";" + user.getLastname() + ";" + user.getFirstname() + ";" + user.getDate() + ";";

    }

    private String getAllFriend(HttpServletRequest request) {
        String login = request.getParameter("login");

        SQLConnector sqlConnector = new SQLConnector();

        User user = sqlConnector.getUserWithoutPass(login);

        StringBuffer res = new StringBuffer();

        for (User userFriend : user.getFriend()) {
            res.append(userFriend.getLogin());
            res.append('/');
        }

        return res.toString();
    }

    private String modifUser(HttpServletRequest request) {
        String loginAncien = request.getParameter("ancienLogin");

        SQLConnector connector = new SQLConnector();
        User user = connector.getUserWithoutPass(loginAncien);


        String login = request.getParameter("login");
        String password = user.getPassword();
        String lastname = request.getParameter("lastname");
        String firstname = request.getParameter("firstname");
        String birthdate = request.getParameter("birthdate");
        int admin = user.isAdmin() ? 1:0;
        int positif = user.isPositif() ? 1:0;
        boolean res = false;
        if (verificationRegex(login, lastname, firstname, birthdate)) {
            res = connector.updateUser(user.getId(), login, password, lastname, firstname, birthdate, admin, positif);
        }

        return res ? "true" : "false";

    }

    private void delete(HttpServletRequest request) {
        String login = request.getParameter("login");

        SQLConnector connector = new SQLConnector();
        connector.deleteUser(login);

    }

    private boolean verificationRegex(String login, String lastname, String firstname, String birthdate) {

        return login.matches("^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{3,19}$") &&
                lastname.matches("^([A-Z][a-z]+([ ]?[a-z]?[\'-]?[A-Z][a-z]+)*)$") &&
                firstname.matches("^([A-Z][a-z]+([ ]?[a-z]?[\'-]?[A-Z][a-z]+)*)$") &&
                birthdate.matches("^(((0[1-9]|[12][0-9]|3[01])[- /.](0[13578]|1[02])|(0[1-9]|[12][0-9]|30)[- /.](0[469]|11)|(0[1-9]|1\\d|2[0-8])[- /.]02)[- /.]\\d{4}|29[- /.]02[- /.](\\d{2}(0[48]|[2468][048]|[13579][26])|([02468][048]|[1359][26])00))$");

    }
}
