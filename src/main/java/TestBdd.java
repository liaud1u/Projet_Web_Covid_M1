import SQLPackage.SQLConnector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestBdd extends HttpServlet {

    private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestBdd() {
        super();

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        System.out.println("Test Connection");

        SQLConnector sc = new SQLConnector();

        sc.connect();

        String rqString = "Select * from utilisateur";
        ResultSet res = sc.doRequest(rqString);

        try {

            while (res.next()) {
                System.out.println(res.getString("login"));
            }
        }

			   catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        response.sendRedirect("/JSP/index.jsp");
    }

}

