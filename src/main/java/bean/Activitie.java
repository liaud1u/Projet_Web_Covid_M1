package bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Activitie {
    private Location location;
    private Date debutActivitee;
    private Date finActivitee;

    public Activitie(Location location, Date debutActivitee, Date finActivitee) {
        this.location = location;
        this.debutActivitee = debutActivitee;
        this.finActivitee = finActivitee;
    }

    public Location getLocation() {
        return location;
    }

    public Date getDebutActivitee() {
        return debutActivitee;
    }

    public Date getFinActivitee() {
        return finActivitee;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleformat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z");

        return "Activitie{" +
                "location=" + location +
                ", debutActivitee=" + simpleformat.format(debutActivitee) +
                ", finActivitee=" + simpleformat.format(finActivitee) +
                '}';
    }
}
