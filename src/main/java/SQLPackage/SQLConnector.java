package SQLPackage;


import java.sql.*;

public class SQLConnector {

		public SQLConnector() { }

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
			         String DBurl = "jdbc:mysql://localhost/covid";
			         con = DriverManager.getConnection(DBurl,"root","");
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
