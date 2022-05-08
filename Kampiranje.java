import java.io.*;
import java.util.*;

public class Kampiranje extends Pocitnice {
    private String mesto;
    private boolean tentNeeded;

    public Kampiranje() {
        super();
        this.mesto = "";
        this.tentNeeded = true;
    }

    public Kampiranje(int id, int maxSteviloOseb, String drzava, int cena,
                    ArrayList<Termin> seznamTerminov, String mesto, boolean tentNeeded) {

        super(id, maxSteviloOseb, drzava, cena, seznamTerminov);
        this.mesto = "";
        this.tentNeeded = true;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public boolean getTentNeeded() {
        return tentNeeded;
    }

    public void setTentNeeded(boolean tentNeeded) {
        this.tentNeeded = tentNeeded;
    }

    @Override
    public String toString() {
        String kampiranje = "\r\n------- Kampiranje -------\r\n";
        kampiranje += "Mesto: " + this.mesto + "\r\n";
        kampiranje += "Drzava: " + this.getDrzava() + "\r\n";
        kampiranje += "Cena: " + this.getCena() + "\r\n";
        kampiranje += "Potrebujete sotor: " + (this.tentNeeded ? "Da" : "Ne") + "\r\n";
        int i = 1;
        for (Termin t : this.getSeznamTerminov()) {
            kampiranje += t.compressToString(this, i);
            i++;
        }
        kampiranje += "-----------------------\r\n";
        return kampiranje;
    }

    public static Kampiranje readFromArray(ArrayList<String> data) {
        Kampiranje kampiranje = new Kampiranje(); 
        try {
            kampiranje.setId(Integer.parseInt(data.get(0)));
            kampiranje.setMaxSteviloOseb(Integer.parseInt(data.get(1)));
            kampiranje.setMesto(data.get(2));
            kampiranje.setDrzava(data.get(3));
            kampiranje.setTentNeeded(data.get(4).equals("true") ? true : false);
            kampiranje.setCena(Integer.parseInt(data.get(5)));

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

					kampiranje.dodajTermin(termin);
				}
			}

            return kampiranje;
        } catch (Exception e) {
            System.out.println("\r\nPrislo je do napake pri vnosu nove pocitnice kampiranje!\r\n");
            throw e;
        }
    }
}
