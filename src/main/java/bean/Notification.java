package bean;

import SQLPackage.SQLConnector;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Notification {
    private boolean repondu;
    private boolean lu;
    private Date date;
    private String destinataire;
    private String envoyeur;
    private boolean accepte;
    private String contenu;
    private int id;

    public Notification(boolean repondu, boolean lu, Date date, String envoyer,String userConcerne, boolean accepte, String contenu, int id) {
        this.repondu = repondu;
        this.lu = lu;
        this.date = date;
        this.destinataire = userConcerne;
        this.accepte = accepte;
        this.contenu = contenu;
        this.envoyeur=envoyer;
        this.id = id;
    }

    public Notification(String contenu, String envoyer,String userConcerne){
        this.contenu=contenu;
        this.envoyeur=envoyer;
        this.destinataire=userConcerne;
    }

    public boolean isRepondu() {
        return repondu;
    }

    public boolean isLu() {
        return lu;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public boolean isAccepte() {
        return accepte;
    }

    public void setRepondu(boolean repondu) {
        this.repondu = repondu;
    }

    public void setLu(boolean lu) {
        this.lu = lu;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public void setAccepte(boolean accepte) {
        this.accepte = accepte;
    }

    public String getEnvoyeur() {
        return envoyeur;
    }

    public void setEnvoyeur(String envoyeur) {
        this.envoyeur = envoyeur;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "repondu=" + repondu +
                ", lu=" + lu +
                ", date=" + date +
                ", userConcerne='" + destinataire + '\'' +
                ", accepte=" + accepte +
                '}';
    }

    public void create(){
        SQLConnector connector = new SQLConnector();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // your template here
        String date = formatter.format(java.util.Calendar.getInstance().getTime());

        connector.insertNotif(contenu, envoyeur, destinataire,date);
    }

    public void save(){
        SQLConnector connector = new SQLConnector();


        connector.updateNotif(this);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

