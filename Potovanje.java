import java.io.*;
import java.util.*;
import java.time.format.*;

public class Potovanje extends Pocitnice {
    private String mesto;
    private int cenaAvionskeKarte;

    public Potovanje() {
        super();
        this.mesto = "";
        this.cenaAvionskeKarte = 0;
    }

    public Potovanje(int id, int maxSteviloOseb, String drzava, int cena,
                    ArrayList<Termin> seznamTerminov, String mesto, int cenaAvionskeKarte) {

        super(id, maxSteviloOseb, drzava, cena, seznamTerminov);
        this.mesto = "";
        this.cenaAvionskeKarte = 0;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public int getCenaAvionskeKarte() {
        return cenaAvionskeKarte;
    }

    public void setCenaAvionskeKarte(int cenaAvionskeKarte) {
        this.cenaAvionskeKarte = cenaAvionskeKarte;
    }

    @Override
    public String toString() {
        String potovanje = "\r\n------- Potovanje -------\r\n";
        potovanje += "Mesto: " + this.mesto + "\r\n";
        potovanje += "Drzava: " + this.getDrzava() + "\r\n";
        potovanje += "Cena: " + this.getCena() + "\r\n";
        potovanje += "Cena avionske karte: " + this.cenaAvionskeKarte + "\r\n";
        int i = 1;
        for (Termin t : this.getSeznamTerminov()) {
            potovanje += t.compressToString(this, i);
            i++;
        }
        potovanje += "-----------------------\r\n";
        return potovanje;
    }

    public static Potovanje readFromArray(ArrayList<String> data) {
        Potovanje potovanje = new Potovanje(); 
        try {
            potovanje.setId(Integer.parseInt(data.get(0)));
            potovanje.setMaxSteviloOseb(Integer.parseInt(data.get(1)));
            potovanje.setMesto(data.get(2));
            potovanje.setDrzava(data.get(3));
            potovanje.setCena(Integer.parseInt(data.get(4)));
            potovanje.setCenaAvionskeKarte(Integer.parseInt(data.get(5)));

            ArrayList<String> terminPodatki;

			for(int i=6; i < data.size(); i++) {
				if(data.get(i).trim().equals("*T")) {
                    terminPodatki = new ArrayList<String>();
					i++;

					while(!data.get(i).trim().equals("#")) {
						terminPodatki.add(data.get(i));
						i++;
					}

					Termin termin = Termin.readFromArray(terminPodatki);

					potovanje.dodajTermin(termin);
				}
			}

            return potovanje;
        } catch (Exception e) {
            System.out.println("\r\nPrislo je do napake pri vnosu nove potovanje!\r\n");
            throw e;
        }
    }
}
