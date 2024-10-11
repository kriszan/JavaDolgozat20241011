import java.time.Duration;
import java.time.LocalDateTime;

public class Bejegyzes {
    private String szerzo;
    private String tartalom;
    private int likeok = 0;
    private LocalDateTime letrejott = LocalDateTime.now();
    private LocalDateTime szerkesztve = LocalDateTime.now();

    public Bejegyzes(String szerzo, String tartalom) {
        this.szerzo = szerzo;
        this.tartalom = tartalom;
    }

    public String getSzerzo() {
        return szerzo;
    }

    public String getTartalom() {
        return tartalom;
    }

    public int getLikeok() {
        return likeok;
    }

    public LocalDateTime getLetrejott() {
        return letrejott;
    }

    public LocalDateTime getSzerkesztve() {
        return szerkesztve;
    }

    public void setTartalom(String tartalom) {
        this.tartalom = tartalom;
        this.szerkesztve = LocalDateTime.now();
    }

    public void like() {
        this.likeok++;
    }

    public void like(int dbszam) {
        this.likeok += dbszam;
    }

    @Override
    public String toString() {
        return this.szerzo+" - "+ String.valueOf(this.likeok)+" - "+this.letrejott+  System.lineSeparator()+ ((this.letrejott.equals(this.szerkesztve))? "": "Szerkesztve: "+this.szerkesztve+System.lineSeparator()+this.tartalom);
    }
}
