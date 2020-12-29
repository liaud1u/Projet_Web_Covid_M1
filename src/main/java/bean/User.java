package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String login;
    private String password;
    private boolean admin;
    private String lastname;
    private String firstname;
    private String date;
    private ArrayList<Activitie> activities = new ArrayList<>();
    private ArrayList<Notification> notifications = new ArrayList<>();

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Activitie> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activitie> activities) {
        this.activities = activities;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public ArrayList<Notification> getNotificationsNonLues() {
        ArrayList<Notification> nonLues = new ArrayList<>();
        for(Notification n : notifications){
            if(!n.isLu()){
                nonLues.add(n);
            }
        }
        return nonLues;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", date='" + date + '\'' +
                ", activities=" + activities +
                ", notif=" + notifications +
                '}';
    }
}
