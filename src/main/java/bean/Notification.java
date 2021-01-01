package bean;

import SQLPackage.SQLConnector;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Notification {
    private boolean reponse;
    private boolean lu;
    private Date date;
    private String destinataire;
    private String envoyeur;
    private boolean acceptable;
    private String contenu;
    private int id;

    public Notification(boolean acceptable, boolean lu, Date date, String envoyer,String userConcerne, boolean reponse, String contenu, int id) {
        this.reponse = reponse;
        this.lu = lu;
        this.date = date;
        this.destinataire = userConcerne;
        this.acceptable = acceptable;
        this.contenu = contenu;
        this.envoyeur=envoyer;
        this.id = id;
    }

    public Notification(String contenu, String envoyer,String userConcerne, boolean acceptable){
        this.contenu=contenu;
        this.envoyeur=envoyer;
        this.destinataire=userConcerne;
        this.acceptable = acceptable;
    }

    public boolean isReponse() {
        return reponse;
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

    public boolean isAcceptable() {
        return acceptable;
    }

    public void accepte() {

        SQLConnector connector = new SQLConnector();

        connector.accepteNotif(this);

        if(!connector.getUserWithoutPass(envoyeur).hasFriend(destinataire))
            connector.addAmi(envoyeur,destinataire);

        if(!connector.getUserWithoutPass(destinataire).hasFriend(envoyeur))
            connector.addAmi(destinataire,envoyeur);

        reponse = true;
        acceptable =false;

        Notification notification = new Notification("Vous êtes désormais ami avec "+destinataire+" !",destinataire,envoyeur,false);
        notification.create();
    }

    public User getDestinataireUser(){
        SQLConnector connector = new SQLConnector();
        return connector.getUserWithoutPass(destinataire);
    }

    public User getEnvoyeurUser(){
        SQLConnector connector = new SQLConnector();
        return connector.getUserWithoutPass(envoyeur);
    }

    public void refuse() {

        SQLConnector connector = new SQLConnector();

        connector.refuseNotif(this);

        reponse= false;
        acceptable =false;


        Notification notification = new Notification(destinataire+" a refusé votre invitation !",destinataire,envoyeur,true);
        notification.create();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public void setAcceptable(boolean acceptable) {
        this.acceptable = acceptable;
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
                "repondu=" + reponse +
                ", lu=" + lu +
                ", date=" + date +
                ", userConcerne='" + destinataire + '\'' +
                ", accepte=" + acceptable +
                '}';
    }

    public void create(){
        SQLConnector connector = new SQLConnector();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // your template here
        String date = formatter.format(java.util.Calendar.getInstance().getTime());

        connector.insertNotif(contenu, envoyeur, destinataire,date,acceptable?1:0);
    }

    public void lu(){
        SQLConnector connector = new SQLConnector();
        lu=true;
        connector.updateNotif(this);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

