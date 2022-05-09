import java.io.*;
import java.util.*;

public class Rezervacija {
    private String imeUporabnika;
    private String priimekUporabnika;
    private String uporabnikUsername;
    private int steviloOdraslih;
    private int steviloOtrok;
    private int terminId;

    public Rezervacija() {
        this.imeUporabnika = "";
        this.priimekUporabnika = "";
        this.uporabnikUsername = "";
        this.steviloOdraslih = 0;
        this.steviloOtrok = 0;
        this.terminId = (int)Math.floor(Math.random()*(999-100+1)+100); // Generate a random number between 100 and 999
    }

    public Rezervacija(String imeUporabnika, String priimekUporabnika, String uporabnikUsername, int steviloOdraslih,
                        int steviloOtrok, int terminId) {
        this.imeUporabnika = imeUporabnika;
        this.priimekUporabnika = priimekUporabnika;
        this.uporabnikUsername = uporabnikUsername;
        this.steviloOdraslih = steviloOdraslih;
        this.steviloOtrok = steviloOtrok;
        this.terminId = terminId;
    }

    public String getImeUporabnika() {
        return imeUporabnika;
    }

    public void setImeUporabnika(String imeUporabnika) {
        this.imeUporabnika = imeUporabnika;
    }

    public String getPriimekUporabnika() {
        return priimekUporabnika;
    }

    public void setPriimekUporabnika(String priimekUporabnika) {
        this.priimekUporabnika = priimekUporabnika;
    }

    public String getUporabnikUsername() {
        return uporabnikUsername;
    }

    public void setUporabnikUsername(String uporabnikUsername) {
        this.uporabnikUsername = uporabnikUsername;
    }

    public int getSteviloOdraslih() {
        return steviloOdraslih;
    }

    public void setSteviloOdraslih(int steviloOdraslih) {
        this.steviloOdraslih = steviloOdraslih;
    }

    public int getSteviloOtrok() {
        return steviloOtrok;
    }

    public void setSteviloOtrok(int steviloOtrok) {
        this.steviloOtrok = steviloOtrok;
    }

    public int getTerminId() {
        return terminId;
    }

    public void setTerminId(int terminId) {
        this.terminId = terminId;
    }

    public String compressAsString() {
        String rezervacija = "*R\r\n";
        rezervacija += this.imeUporabnika + "\r\n";
        rezervacija += this.priimekUporabnika + "\r\n";
        rezervacija += this.uporabnikUsername + "\r\n";
        rezervacija += this.steviloOdraslih + "\r\n";
        rezervacija += this.steviloOtrok + "\r\n";
        rezervacija += this.terminId + "\r\n";
        rezervacija += "##\r\n";

		return rezervacija;
    }

    @Override
    public String toString() {
        String rezervacija = "\r\n------- Rezervacija -------\r\n";
        rezervacija += "Stevilo odraslih oseb: " + this.steviloOdraslih + "\r\n";
        rezervacija += "Stevilo otrok: " + this.steviloOtrok + "\r\n";
        rezervacija += "Sifra termina: " + this.terminId + "\r\n";
        rezervacija += "-----------------------\r\n";
        return rezervacija;
    }

    public static Rezervacija kreirajRezervacije(Uporabnik user) throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        System.out.println("\r\nVnesi sifra termina pocitnice: \r\n");
        int sifra = Integer.parseInt(br.readLine().trim());
        System.out.println();

        System.out.println("\r\nVnesi stevilo odraslih oseb: \r\n");
        int steviloOdraslih = Integer.parseInt(br.readLine().trim());
        System.out.println();

        System.out.println("\r\nVnesi stevilo otrok: \r\n");
        int steviloOtrok = Integer.parseInt(br.readLine().trim());
        System.out.println();

        Rezervacija rezervacija = new Rezervacija(user.getIme(), user.getPriimek(), user.getUporabniskoIme(), steviloOdraslih, steviloOtrok, sifra);

        return rezervacija;
    }

    public static Rezervacija readFromArray(ArrayList<String> input) {
        Rezervacija rezervacija = new Rezervacija();
        try {
            rezervacija.setImeUporabnika(input.get(0));
            rezervacija.setPriimekUporabnika(input.get(1));
            rezervacija.setUporabnikUsername(input.get(2));
            rezervacija.setSteviloOdraslih(Integer.parseInt(input.get(3)));
            rezervacija.setSteviloOtrok(Integer.parseInt(input.get(4)));
            rezervacija.setTerminId(Integer.parseInt(input.get(5)));
            return rezervacija;
        } catch (Exception e) {
            System.out.println("Prislo je do napake pri vnosu nove rezervacije!\r\n");
            throw e;
        }
    }
}
