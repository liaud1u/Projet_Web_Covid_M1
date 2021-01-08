package bean;

import java.time.LocalDateTime;

public class Creneau {
    private LocalDateTime debut1, debut2, fin1, fin2;

    public Creneau(LocalDateTime debut1, LocalDateTime debut2, LocalDateTime fin1, LocalDateTime fin2) {
        this.debut1 = debut1;
        this.debut2 = debut2;
        this.fin1 = fin1;
        this.fin2 = fin2;
    }

    public boolean intersect(){
        return  (!fin1.isBefore(debut2) && !fin2.isBefore(debut1));
    }
}
