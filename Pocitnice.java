import java.io.*;
import java.util.*;
import java.util.function.Supplier;

public class Pocitnice {
    private int id;
    private int maxSteviloOseb;
    private String drzava;
    private int cena;
    private ArrayList<Termin> seznamTerminov;

    public Pocitnice() {
        this.id = (int)Math.floor(Math.random()*(999-100+1)+100); // Generate a random number between 100 and 999
        this.maxSteviloOseb = 50;
        this.drzava = "";
        this.cena = 0;
        this.seznamTerminov = new ArrayList<Termin>();
    }

    public Pocitnice(int id, int maxSteviloOseb, String drzava, int cena, ArrayList<Termin> seznamTerminov) {
        this.id = id;
        this.maxSteviloOseb = maxSteviloOseb;
        this.drzava = drzava;
        this.cena = cena;
        this.seznamTerminov = seznamTerminov;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxSteviloOseb() {
        return maxSteviloOseb;
    }

    public void setMaxSteviloOseb(int maxSteviloOseb) {
        this.maxSteviloOseb = maxSteviloOseb;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public ArrayList<Termin> getSeznamTerminov() {
        return seznamTerminov;
    }

    public void setSeznamTerminov(ArrayList<Termin> seznamTerminov) {
        this.seznamTerminov = seznamTerminov;
    }

    public void dodajTermin(Termin termin) {
        this.seznamTerminov.add(termin);
    }

    public static void main(String args) {}
}
