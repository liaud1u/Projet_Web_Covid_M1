package bean;

public class Location {
    private String name;
    private String adresse;

    public Location() {
    }

    public Location(String name, String adresse){
        this.name = name;
        this.adresse = adresse;
    }

    public String getName() {
        return name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}
