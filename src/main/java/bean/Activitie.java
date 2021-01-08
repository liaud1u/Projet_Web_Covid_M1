package bean;

import SQLPackage.SQLConnector;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

public class Activitie {
    private Lieu lieu;
    private LocalDateTime debutActivitee;
    private LocalDateTime finActivitee;
    private User user;
    private int id;

    public Activitie() {
    }

    public Activitie(int id, Lieu lieu, LocalDateTime debutActivitee, LocalDateTime finActivitee, User user) {

        this.lieu = lieu;
        this.debutActivitee = debutActivitee;
        this.finActivitee = finActivitee;
        this.id = id;
        this.user= user;
    }

    public Activitie(  Lieu lieu, LocalDateTime debutActivitee, LocalDateTime finActivitee, User user) {

        this.lieu = lieu;
        this.debutActivitee = debutActivitee;
        this.finActivitee = finActivitee;
        this.user= user;
    }

    public int getId() {
        return id;
    }

    public Lieu getLocation() {
        return lieu;
    }

    public LocalDateTime getDebutActivitee() {
        return debutActivitee;
    }

    public LocalDateTime getFinActivitee() {
        return finActivitee;
    }

    public String getDebutActiviteeFormatted(){
        String date = debutActivitee.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.SHORT));
        return date;
    }


    public String getFinActiviteeFormatted(){
        String date = finActivitee.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT));
        return date;
    }

    public User getUser() {
        return user;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void create(){
        SQLConnector connector = new SQLConnector();


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // your template here


        connector.insertActivite(formatter.format(Date.from( debutActivitee.minus(1, ChronoUnit.HOURS).atZone(Clock.systemUTC().getZone()).toInstant())),formatter.format(Date.from(finActivitee.minus(1, ChronoUnit.HOURS).atZone(Clock.systemUTC().getZone()).toInstant())),lieu,user);
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public void setDebutActivitee(LocalDateTime debutActivitee) {
        this.debutActivitee = debutActivitee;
    }

    public void setFinActivitee(LocalDateTime finActivitee) {
        this.finActivitee = finActivitee;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }
}
