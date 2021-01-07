package bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Activitie {
    private Lieu lieu;
    private Date debutActivitee;
    private Date finActivitee;
    private int id;

    public Activitie(int id, Lieu lieu, Date debutActivitee, Date finActivitee) {
        this.lieu = lieu;
        this.debutActivitee = debutActivitee;
        this.finActivitee = finActivitee;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Lieu getLocation() {
        return lieu;
    }

    public Date getDebutActivitee() {
        return debutActivitee;
    }

    public Date getFinActivitee() {
        return finActivitee;
    }

    public String getDebutActiviteeFormatted(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd à HH:mm:ss"); // your template here
        String date = formatter.format(debutActivitee);
        return date;
    }


    public String getFinActiviteeFormatted(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd à HH:mm:ss"); // your template here
        String date = formatter.format(finActivitee);
        return date;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleformat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z");

        return "Activitie{" +
                "location=" + lieu +
                ", debutActivitee=" + simpleformat.format(debutActivitee) +
                ", finActivitee=" + simpleformat.format(finActivitee) +
                '}';
    }
}
