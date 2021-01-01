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

	public boolean insertNotif(String contenu, String login1, String login2, String date, int acceptable) {

		String rqString =  "Insert into notification( contenu, lu, date, login1, login2, accepte, repondu) values('" +
				contenu + "', 0, '" + date + "', '" + login1 + "', '" + login2 + "',0,"+acceptable+");";

		return doUpdate(rqString);
	}

	public boolean updateNotif(Notification notification) {

		String rqString =  "UPDATE `notification` SET `lu` = '1' WHERE `notification`.`idNotif` = "+notification.getId()+";";

		return doUpdate(rqString);
	}

	public boolean accepteNotif(Notification notification) {

		String rqString =  "UPDATE `notification` SET `accepte` = '1'  WHERE `notification`.`idNotif` = "+notification.getId()+";";
		String rqString2 =  "UPDATE `notification` SET `repondu` = '0'  WHERE `notification`.`idNotif` = "+notification.getId()+";";

		return doUpdate(rqString)&& doUpdate(rqString2);
	}

	public boolean refuseNotif(Notification notification) {

		String rqString =  "UPDATE `notification` SET `accepte` = '0'  WHERE `notification`.`idNotif` = "+notification.getId()+";";
		String rqString2 =  "UPDATE `notification` SET `repondu` = '0'  WHERE `notification`.`idNotif` = "+notification.getId()+";";

		return doUpdate(rqString) && doUpdate(rqString2);
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
								user.setPositif(res.getBoolean("positif"));

								getActivityNotificationsFriends(login, user);

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

	private void getActivityNotificationsFriends(String login, User user) {
		String rqStringActivite = "Select * from activite where login='"+login+"' ORDER BY idActivite DESC ;";
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

		String rqStringNotif = "Select * from notification where login2='"+login+"' ORDER BY date DESC ;";
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


		String rqStringAmis = "Select * from ami where login1='"+login+"';";
		ResultSet res4 = doRequest(rqStringAmis);

		ArrayList<User> amis = new ArrayList<>();

		try {
			while (res4.next()) {
				amis.add(getUserSimplify(res4.getString("login2")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		user.setIdsFriend(amis);
	}

	public User getUserWithoutPass(String login) {
		User user = null;
		String rqString = "Select * from utilisateur where login='"+login+"';";
		ResultSet res = doRequest(rqString);
		int i = 0;
		try {
			while (res.next()) {
					if (i == 0) {
						user = new User();
						user.setLogin(res.getString("login"));
						user.setPassword(res.getString("mdp"));
						user.setAdmin(res.getBoolean("admin"));
						user.setLastname(res.getString("nom"));
						user.setFirstname(res.getString("prenom"));
						user.setDate(res.getString("date"));
						user.setPositif(res.getBoolean("positif"));

						getActivityNotificationsFriends(login, user);

					} else {
						i++;
						arret("Plus d'un utilisateur ayant le même login ??");
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

							activitie = new Activitie(res.getInt("idActivite"),lieu,dateDebut,dateFin);
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
					notif = new Notification(res.getBoolean("repondu"),res.getBoolean("lu"),res.getDate("date"),res.getString("login1"),res.getString("login2"),res.getBoolean("accepte"),res.getString("contenu"),res.getInt("idNotif"));


					Timestamp timestamp = res.getTimestamp("date");
					if (timestamp != null)
						notif.setDate( new java.util.Date(timestamp.getTime()));


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

		public ArrayList<User> getUsersSimplify(String loginFraction) {
			ArrayList<User> users = new ArrayList<>();
			String rqString = "Select * from utilisateur where login like '%"+loginFraction+"%';";
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
							user.setPositif(res.getBoolean("positif"));

							String rqStringActivite = "Select * from activite where login='"+loginFraction+"' ORDER BY idActivite DESC ;";
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

	public User getUserSimplify(String login) {
		User user = new User();
		String rqString = "Select * from utilisateur where login='"+login+"';";
		ResultSet res = doRequest(rqString);
		int i = 0;
		try {
			while(res.next()) {
				if (i == 0) {

					user.setLogin(res.getString("login"));
					user.setAdmin(res.getBoolean("admin"));
					user.setLastname(res.getString("nom"));
					user.setFirstname(res.getString("prenom"));
					user.setDate(res.getString("date"));
					user.setPositif(res.getBoolean("positif"));


				} else {
					i++;
					arret("Plus d'un utilisateur ayant le même login ??");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
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

    public boolean addAmi(String envoyeur, String destinataire) {

		String rqString =  "Insert into ami(login1, login2) values('"+envoyeur+"','"+destinataire+"');";

		return doUpdate(rqString);
    }

	public boolean setPositif(User user) {

		String rqString =  "UPDATE `utilisateur` SET `positif` = '1' WHERE `utilisateur`.`login` = '"+user.getLogin()+"';";

		return doUpdate(rqString);
	}
}
