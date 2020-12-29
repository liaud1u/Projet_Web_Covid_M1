package bean;

import java.util.Date;

public class Notification {
    private boolean repondu;
    private boolean lu;
    private Date date;
    private String userConcerne;
    private boolean accepte;

    public Notification(boolean repondu, boolean lu, Date date, String userConcerne, boolean accepte) {
        this.repondu = repondu;
        this.lu = lu;
        this.date = date;
        this.userConcerne = userConcerne;
        this.accepte = accepte;
    }

    public boolean isRepondu() {
        return repondu;
    }

    public boolean isLu() {
        return lu;
    }

    public Date getDate() {
        return date;
    }

    public String getUserConcerne() {
        return userConcerne;
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

    public void setUserConcerne(String userConcerne) {
        this.userConcerne = userConcerne;
    }

    public void setAccepte(boolean accepte) {
        this.accepte = accepte;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "repondu=" + repondu +
                ", lu=" + lu +
                ", date=" + date +
                ", userConcerne='" + userConcerne + '\'' +
                ", accepte=" + accepte +
                '}';
    }
}

