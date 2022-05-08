import java.io.*;
import java.util.*;

public class Uporabnik {
    private String ime;
    private String priimek;
    private String uporabniskoIme;
    private String geslo;
    private boolean jeAdmin;
    private ArrayList<Rezervacija> rezervacije;

    public Uporabnik() {
        this.ime = "";
        this.priimek = "";
        this.uporabniskoIme = "";
        this.geslo = "";
        this.jeAdmin = false;
    }

    public Uporabnik(String ime, String priimek, String uporabniskoIme, String geslo, boolean jeAdmin) {
        this.ime = ime;
        this.priimek = priimek;
        this.uporabniskoIme = uporabniskoIme;
        this.geslo = geslo;
        this.jeAdmin = jeAdmin;
    }

    public String getIme() {
        return this.ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return this.priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getUporabniskoIme() {
        return this.uporabniskoIme;
    }

    public void setUporabniskoIme(String uporabniskoIme) {
        this.uporabniskoIme = uporabniskoIme;
    }

    public String getGeslo() {
        return this.geslo;
    }

    public void setGeslo(String geslo) {
        this.geslo = geslo;
    }

    public boolean isJeAdmin() {
        return this.jeAdmin;
    }

    public void setJeAdmin(boolean jeAdmin) {
        this.jeAdmin = jeAdmin;
    }

    public ArrayList<Rezervacija> getRezervacije() {
        return this.rezervacije;
    }

    public void setRezervacije(ArrayList<Rezervacija> rezervacije) {
        this.rezervacije = rezervacije;
    }

    // Saving User data in one string
    @Override
    public String toString() {
        String zapis = "------- User -------\r\n";
        zapis += "Ime: " + this.ime + "\r\n";
        zapis += "Priimek: " + this.priimek + "\r\n";
        zapis += "Uporabnisko ime: " + this.uporabniskoIme + "\r\n";
        zapis += "Geslo: "  + this.geslo + "\r\n";
        zapis += "Je Admin: " + this.jeAdmin + "\r\n";
        zapis += "--------------------\r\n";
        return zapis;
    }

    public String compressAsString() {
        String user = "*U\r\n";
        user += this.ime + "\r\n";
        user += this.priimek + "\r\n";
        user += this.uporabniskoIme + "\r\n";
        user += this.geslo + "\r\n";
        user += this.jeAdmin + "\r\n";
        user += "##\r\n";

		return user;
    }

    // Vnos podatke od strane uporabnika
    public static Uporabnik kreirajUporabnik() throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        System.out.println("\r\n*****   Registracija novega uporabnika   *****\r\n");
        System.out.println("Vnesi ime: ");
        String ime = br.readLine().trim();
        System.out.println();

        System.out.println("Vnesi priimek: ");
        String priimek = br.readLine().trim();
        System.out.println();

        System.out.println("Vnesi uporabnisko ime: ");
        String uporabniskoIme = br.readLine().trim();
        System.out.println();

        System.out.println("Vnesi geslo: ");
        String geslo = br.readLine().trim();
        System.out.println();

        Uporabnik user = new Uporabnik(ime, priimek, uporabniskoIme, geslo, false);

        return user;
    }

    public static Uporabnik readFromArray(ArrayList<String> input) {
        Uporabnik user = new Uporabnik();
        try {
            user.setIme(input.get(0));
            user.setPriimek(input.get(1));
            user.setUporabniskoIme(input.get(2));
            user.setGeslo(input.get(3));
            user.setJeAdmin((input.get(4).equals("true") ? true : false));

            return user;
        } catch (Exception e) {
            System.out.println("Prislo je do napake pri vnosu novega uporabnika!\r\n");
            throw e;
        }
    }
}