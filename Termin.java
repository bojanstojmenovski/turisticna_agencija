import java.util.*;
import java.time.*;
import java.time.format.*;

public class Termin {
    private int id;
    private int steviloOsebRezervirano;
    private LocalDate datumPrihod;
    private LocalDate datumOdhod;
    private int casPrihod;
    private int casOdhod;
    private boolean jeRezervirano;

    static DateTimeFormatter dateFormatter = new DateTimeFormatterBuilder().appendPattern("dd-MM-yyyy").toFormatter();

    public Termin() {
        this.id = (int)Math.floor(Math.random()*(999-100+1)+100); // Generate a random number between 100 and 999
        this.steviloOsebRezervirano = 0;
        this.datumPrihod = LocalDate.parse("01-01-2023", dateFormatter);
        this.datumOdhod = LocalDate.parse("01-01-2023", dateFormatter);
        this.casPrihod = 0;
        this.casOdhod = 0;
    }

    public Termin(int id, int steviloOsebRezervirano, LocalDate datumPrihod, LocalDate datumOdhod, int casPrihod, int casOdhod) {
        this.id = id;
        this.steviloOsebRezervirano = steviloOsebRezervirano;
        this.datumPrihod = datumPrihod;
        this.datumOdhod = datumOdhod;
        this.casPrihod = casPrihod;
        this.casOdhod = casOdhod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSteviloOsebRezervirano() {
        return steviloOsebRezervirano;
    }

    public void setSteviloOsebRezervirano(int steviloOsebRezervirano) {
        this.steviloOsebRezervirano = steviloOsebRezervirano;
    }

    public LocalDate getDatumPrihod() {
        return datumPrihod;
    }

    public void setDatumPrihod(LocalDate datumPrihod) {
        this.datumPrihod = datumPrihod;
    }

    public LocalDate getDatumOdhod() {
        return datumOdhod;
    }

    public void setDatumOdhod(LocalDate datumOdhod) {
        this.datumOdhod = datumOdhod;
    }

    public int getCasPrihod() {
        return casPrihod;
    }

    public void setCasPrihod(int casPrihod) {
        this.casPrihod = casPrihod;
    }

    public int getCasOdhod() {
        return casOdhod;
    }

    public void setCasOdhod(int casOdhod) {
        this.casOdhod = casOdhod;
    }

    public boolean isJeRezervirano() {
        return jeRezervirano;
    }

    public void setJeRezervirano(boolean jeRezervirano) {
        this.jeRezervirano = jeRezervirano;
    }

    public static LocalDate getDateFromString(String s) {
        return LocalDate.parse(s, dateFormatter);
    }

    public static String getStringFromDate(LocalDate date) {
        return date.format(dateFormatter);
    }

    public String compressToString(Pocitnice p, int i) {
        String termin = "";
        termin += "\r\n  === Termin " + i + " ===\r\n";
        termin += "  Sifra: " + this.getId() + "\r\n";
        termin += "  Datum prihoda: " + Termin.getStringFromDate(this.getDatumPrihod()) + "\r\n";
        termin += "  Cas prihoda: " + this.getCasPrihod() + "\r\n";
        termin += "  Datum odhoda: " + Termin.getStringFromDate(this.getDatumOdhod()) + "\r\n";
        termin += "  Cas odhoda: " + this.getCasOdhod() + "\r\n";

        String zasedenost;
        int nReserved = this.getSteviloOsebRezervirano();
        if (nReserved == p.getMaxSteviloOseb()) {
            zasedenost = "zagotovljeno";
        } else if (nReserved > p.getMaxSteviloOseb()/2 && nReserved < p.getMaxSteviloOseb()) {
            zasedenost = "skoraj zagotovljeno";
        } else {
            zasedenost = "ni zagotovljeno";
        }

        termin += "  Zasedenost: " + zasedenost + "\r\n";
        termin += "  =======\r\n";
        return termin;
    }

    public static Termin readFromArray(ArrayList<String> array) {
        Termin termin = new Termin();
        try {
            termin.setId(Integer.parseInt(array.get(0)));
            termin.setSteviloOsebRezervirano(Integer.parseInt(array.get(1)));
            termin.setDatumPrihod(Termin.getDateFromString(array.get(2)));
            termin.setDatumOdhod(Termin.getDateFromString(array.get(3)));
            termin.setCasPrihod(Integer.parseInt(array.get(4)));
            termin.setCasOdhod(Integer.parseInt(array.get(5)));
            termin.setJeRezervirano((array.get(6).equals("true") ? true : false));
            return termin;
        } catch (Exception e) {
            System.out.println("\r\nPrišlo je do napake v zapisu termina!\r\n");
			throw e;
        }
    }
}
