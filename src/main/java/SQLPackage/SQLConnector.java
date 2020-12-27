package SQLPackage;


import bean.User;

import java.sql.*;
import java.util.ArrayList;

public class SQLConnector {

		public SQLConnector() { }

			public boolean insertUser(String login, String password, String lastname, String firstname, String date) {

				String rqString =  "Insert into utilisateur(login, mdp, admin, nom, prenom, date) values('" + login + "', '" +
									password + "', 0, '" + lastname + "', '" + firstname + "', '" + date + "');";

				return doUpdate(rqString);
			}

			public User getUser(String login, String password) {
				User user = null;
				String rqString = "Select * from utilisateur where login='"+login+"';";
				ResultSet res = doRequest(rqString);
				int i = 0;
				try {
					while (res.next()) {
						if (PasswordHash.validatePassword(password, res.getString("mdp"))) {
							if (i == 0) {
								user = new User();
								user.setLogin(res.getString("login"));
								user.setPassword(res.getString("mdp"));
								user.setAdmin(res.getBoolean("admin"));
								user.setLastname(res.getString("nom"));
								user.setFirstname(res.getString("prenom"));
								user.setDate(res.getString("date"));
							} else {
								i++;
								arret("Plus d'un utilisateur ayant le même login ??");
							}
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				return user;
			}

	public ArrayList<User> getUsers(String login) {
		ArrayList<User> users = new ArrayList<>();
		String rqString = "Select * from utilisateur where login like '%"+login+"%';";
		ResultSet res = doRequest(rqString);
		int i = 0;
		try {
			while (res.next()) {
					if (i == 0) {
						User user = new User();
						user.setLogin(res.getString("login"));
						user.setAdmin(res.getBoolean("admin"));
						user.setLastname(res.getString("nom"));
						user.setFirstname(res.getString("prenom"));
						user.setDate(res.getString("date"));

						users.add(user);
					} else {
						i++;
						arret("Plus d'un utilisateur ayant le même login ??");
					}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
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
