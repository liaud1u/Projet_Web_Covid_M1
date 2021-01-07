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

        ArrayList<User> users = sqlConnector.getUsersSimplify(login);
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
        return user.getLogin() + ";" + user.getLastname() + ";" + user.getFirstname() + ";" + user.getDate();

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
}
