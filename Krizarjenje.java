import java.io.*;
import java.util.*;

public class Krizarjenje extends Pocitnice {
    private String mestoZacetka;
    private String morje;

    public Krizarjenje() {
        super();
    }

    public Krizarjenje(int id, int maxSteviloOseb, String drzava, int cena,
                    ArrayList<Termin> seznamTerminov, String mestoZacetka, String morje) {

        super(id, maxSteviloOseb, drzava, cena, seznamTerminov);
        this.mestoZacetka = "";
        this.morje = "";
    }

    public String getMestoZacetka() {
        return mestoZacetka;
    }

    public void setMestoZacetka(String mestoZacetka) {
        this.mestoZacetka = mestoZacetka;
    }

    public String getMorje() {
        return morje;
    }

    public void setMorje(String morje) {
        this.morje = morje;
    }

    @Override
    public String toString() {
        String krizarjenje = "\r\n------- Krizarjenje -------\r\n";
        krizarjenje += "Mesto zacetka: " + this.mestoZacetka + "\r\n";
        krizarjenje += "Drzava: " + this.getDrzava() + "\r\n";
        krizarjenje += "Morje/Ocean: " + this.morje + "\r\n";
        krizarjenje += "Cena: " + this.getCena() + "\r\n";
        int i = 1;
        for (Termin t : this.getSeznamTerminov()) {
            krizarjenje += t.compressToString(this, i);
            i++;
        }
        krizarjenje += "-----------------------\r\n";
        return krizarjenje;
    }

    public static Krizarjenje readFromArray(ArrayList<String> data) {
        Krizarjenje krizarjenje = new Krizarjenje(); 
        try {
            krizarjenje.setId(Integer.parseInt(data.get(0)));
            krizarjenje.setMaxSteviloOseb(Integer.parseInt(data.get(1)));
            krizarjenje.setMestoZacetka(data.get(2));
            krizarjenje.setDrzava(data.get(3));
            krizarjenje.setMorje(data.get(4));
            krizarjenje.setCena(Integer.parseInt(data.get(5)));

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

					krizarjenje.dodajTermin(termin);
				}
			}

            return krizarjenje;
        } catch (Exception e) {
            System.out.println("\r\nPrislo je do napake pri vnosu nove krizarjenje!\r\n");
            throw e;
        }
    }
}
