import java.io.*;
import java.util.*;

public class Smucanje extends Pocitnice {   
    private String skiCentar;
    private int cenaSkiKarte;

    public Smucanje() {
        super();
        this.skiCentar = "";
        this.cenaSkiKarte = 0;
    }

    public Smucanje(int id, int maxSteviloOseb, String drzava, int cena,
                    ArrayList<Termin> seznamTerminov, String skiCentar, int cenaSkiKarte) {

        super(id, maxSteviloOseb, drzava, cena, seznamTerminov);
        this.skiCentar = skiCentar;
        this.cenaSkiKarte = cenaSkiKarte;
    }

    public String getSkiCentar() {
        return skiCentar;
    }

    public void setSkiCentar(String skiCentar) {
        this.skiCentar = skiCentar;
    }

    public int getCenaSkiKarte() {
        return cenaSkiKarte;
    }

    public void setCenaSkiKarte(int cenaSkiKarte) {
        this.cenaSkiKarte = cenaSkiKarte;
    }

    @Override
    public String toString() {
        String smucanje = "\r\n------- Smucanje -------\r\n";
        smucanje += "Drzava: " + this.getDrzava() + "\r\n";
        smucanje += "Ski Centar: " + this.skiCentar + "\r\n";
        smucanje += "Cena: " + this.getCena() + "\r\n";
        smucanje += "Cena ski karte: " + this.cenaSkiKarte + "\r\n";
        int i = 1;
        for (Termin t : this.getSeznamTerminov()) {
            smucanje += t.compressToString(this, i);
            i++;
        }
        smucanje += "-----------------------\r\n";
        return smucanje;
    }

    public static Smucanje readFromArray(ArrayList<String> data) {
        Smucanje smucanje = new Smucanje(); 
        try {
            smucanje.setId(Integer.parseInt(data.get(0)));
            smucanje.setMaxSteviloOseb(Integer.parseInt(data.get(1)));
            smucanje.setSkiCentar(data.get(2));
            smucanje.setDrzava(data.get(3));
            smucanje.setCena(Integer.parseInt(data.get(4)));
            smucanje.setCenaSkiKarte(Integer.parseInt(data.get(5)));

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

					smucanje.dodajTermin(termin);
				}
			}

            return smucanje;
        } catch (Exception e) {
            System.out.println("\r\nPrislo je do napake pri vnosu nove smucarske pocitnice!\r\n");
            throw e;
        }
    }
}
