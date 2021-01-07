package bean;

import SQLPackage.SQLConnector;

import java.text.SimpleDateFormat;

public class Lieu {
    private String name;
    private String adresse;
    private int id;

    public Lieu(String name, String adresse){
        this.name = name;
        this.adresse = adresse;
    }

    public Lieu(int id, String name, String adresse){
        this.name = name;
        this.adresse = adresse;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAdresse() {
        return adresse;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }

    public void create(){
        SQLConnector connector = new SQLConnector();

        connector.insertLieu(name,adresse);
    }

    public int getId() {
    return id;
    }
}
