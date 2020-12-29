package SQLPackage;


import bean.Activitie;
import bean.Location;
import bean.Notification;
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

								String rqStringActivite = "Select * from activite where login='"+login+"';";
								ResultSet res2 = doRequest(rqStringActivite);

								ArrayList<Activitie> activities = new ArrayList<>();

								try {
									while (res2.next()) {
										Activitie activitie = getActivite(res2.getString("idActivite"));
										activities.add(activitie);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								user.setActivities(activities);

								String rqStringNotif = "Select * from notification where login1='"+login+"';";
								ResultSet res3 = doRequest(rqStringNotif);

								ArrayList<Notification> notifs = new ArrayList<>();

								try {
									while (res3.next()) {
										Notification notif = getNotification(res3.getString("idNotif"));
										notifs.add(notif);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}

								user.setNotifications(notifs);

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

		public Activitie getActivite(String id){
			Activitie activitie = null;
			String rqString = "Select * from activite where idActivite='"+id+"';";
			ResultSet res = doRequest(rqString);
			int i = 0;
			try {
				while (res.next()) {
						if (i == 0) {
							Location lieu = getLocation(res.getString("idLieu"));

							Date dateDebut = res.getDate("heureDebut");
							Date dateFin = res.getDate("heureFin");

							activitie = new Activitie(lieu,dateDebut,dateFin);
						} else {
							i++;
							arret("Plus d'une activitie ayant le même id ??");
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return activitie;
		}

		public Location getLocation(String id){
			Location location = null;
			String rqString = "Select * from lieu where idLieu='"+id+"';";
			ResultSet res = doRequest(rqString);
			int i = 0;
			try {
				while (res.next()) {
						if (i == 0) {
							location = new Location(res.getString("nom"),res.getString("adresse"));
						} else {
							i++;
							arret("Plus d'une location ayant le même id ??");
						}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return location;
		}

	public Notification getNotification(String id){
		Notification notif = null;
		String rqString = "Select * from notification where idNotif='"+id+"';";
		ResultSet res = doRequest(rqString);
		int i = 0;
		try {
			while (res.next()) {
				if (i == 0) {
					notif = new Notification(res.getBoolean("repondu"),res.getBoolean("lu"),res.getDate("date"),res.getString("login2"),res.getBoolean("accepte"));
				} else {
					i++;
					arret("Plus d'une notif ayant le même id ??");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return notif;
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

							String rqStringActivite = "Select * from activite where login='"+login+"';";
							ResultSet res2 = doRequest(rqStringActivite);

							ArrayList<Activitie> activities = new ArrayList<>();

							try {
								while (res2.next()) {
									Activitie activitie = getActivite(res.getString("idActivite"));
									activities.add(activitie);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

							user.setActivities(activities);

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
