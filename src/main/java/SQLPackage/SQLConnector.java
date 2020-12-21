package SQLPackage;


import java.sql.*;

public class SQLConnector {

		public SQLConnector() { }

			public boolean insertUser(String login, String password, String lastname, String firstname, String date) {

				String rqString =  "insert into utilisateur(login, mdp, admin, nom, prenom, date) values('" + login + "', '" +
									password + "', 0, '" + lastname + "', '" + firstname + "', '" + date + "');";

				return doUpdate(rqString);
			}

		 	public  ResultSet doRequest(String sql_string) {
			   ResultSet results = null;
			   Connection con = connect();
			   try {
				   Statement stmt = con.createStatement();
				   results = stmt.executeQuery(sql_string);
				} catch (SQLException e) {
				   e.printStackTrace();
				}

			   return results;
		   }

		   public boolean doUpdate(String sql_string) {
			int results = 0;
			Connection con = connect();
			try {
				Statement stmt = con.createStatement();
				results = stmt.executeUpdate(sql_string);

			} catch (SQLException e) {
				e.printStackTrace();
			}

			boolean reusit = false;
			if (results == 1) {
				reusit = true;
			}
				return reusit;
		   }


		   public Connection connect() {

			   Connection con = null;

			   try {
				   Class.forName("com.mysql.cj.jdbc.Driver");
			   }
			   catch (ClassNotFoundException e) {
			         arret("Impossible de charger le pilote jdbc");
			   }

			   System.out.println("Connexion a la base de données");

			   try {
			         String DBurl = "jdbc:mysql://127.0.01:8889/covid";
			         con = DriverManager.getConnection(DBurl,"root","root");
				   System.out.println("connexion réussie");
			   }
			   catch (SQLException e) {
			         arret("Connection à la base de données impossible");
			   }

			   return con;
		   }


		   private static void arret(String message) {
			      System.err.println(message);
			      System.exit(99);
		   }
}
