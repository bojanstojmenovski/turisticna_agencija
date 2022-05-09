import java.io.*;
import java.util.*;
import java.time.*;

public class TuristickaAgencija {
    private Uporabnik loggedUser;
    private ArrayList<Uporabnik> seznamUporabnikov;
    private ArrayList<Pocitnice> seznamPocitnice;
    private ArrayList<Rezervacija> seznamRezervacije;
    private ArrayList<Termin> vsiTermini;

    public TuristickaAgencija() {
        this.loggedUser = null;
        this.seznamUporabnikov = new ArrayList<Uporabnik>();
        this.seznamPocitnice = new ArrayList<Pocitnice>();
        this.seznamRezervacije = new ArrayList<Rezervacija>();
    }

    public TuristickaAgencija(Uporabnik loggedUser, ArrayList<Uporabnik> seznamUporabnikov,
                            ArrayList<Pocitnice> seznamPocitnice, ArrayList<Rezervacija> seznamRezervacije) {
        this.loggedUser = loggedUser;
        this.seznamUporabnikov = seznamUporabnikov;
        this.seznamPocitnice = seznamPocitnice;
        this.seznamRezervacije = seznamRezervacije;
    }

    public Uporabnik getLoggedUser() {
        return this.loggedUser;
    }

    public void setLoggedUser(Uporabnik loggedUser) {
        this.loggedUser = loggedUser;
    }

    public boolean isUserLoggedIn() {
        return this.loggedUser == null ? false : true;
    }

    public ArrayList<Uporabnik> getSeznamUporabnikov() {
        return this.seznamUporabnikov;
    }

    public void setSeznamUporabnikov(ArrayList<Uporabnik> seznamUporabnikov) {
        this.seznamUporabnikov = seznamUporabnikov;
    }

    public ArrayList<Pocitnice> getSeznamPocitnice() {
        return this.seznamPocitnice;
    }

    public void setSeznamPocitnice(ArrayList<Pocitnice> seznamPocitnice) {
        this.seznamPocitnice = seznamPocitnice;
    }

    public ArrayList<Rezervacija> getSeznamRezervacije() {
        return this.seznamRezervacije;
    }

    public void setSeznamRezervacije(ArrayList<Rezervacija> seznamRezervacije) {
        this.seznamRezervacije = seznamRezervacije;
    }

    public ArrayList<Termin> getVsiTermini() {
        return this.vsiTermini;
    }

    public void setVsiTermini(ArrayList<Termin> vsiTermini) {
        this.vsiTermini = vsiTermini;
    }

    // Logiranje uporabnika u program
    public void logIn() throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        while (true) {
            System.out.println("\r\nVnesite uporabnisko ime:\r\n");
            String username = br.readLine().trim();
            System.out.println("\r\nVnesite geslo:\r\n");
            String password = br.readLine().trim();

            boolean loggedIn = false;

            for (Uporabnik u : this.seznamUporabnikov) {
                if (username.equals(u.getUporabniskoIme()) && password.equals(u.getGeslo())) {
                    this.loggedUser = u;
                    loggedIn = true;
                    System.out.println("\r\nUspesno ste se logirali v program.\r\n");
                    System.out.println(u.toString());
                    return;
                }
            }

            if (!loggedIn) {
                System.out.println("\r\nUporabnik s tem uporabniskim imenom in geslom ne obstaja! Ce se nimate racun prosim se registrirajte v program.\r\n");
            }
        }
    }

    public void saveUserInFile(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName, false);
		PrintWriter file = new PrintWriter(fw);

        for (Uporabnik u : this.seznamUporabnikov) {
            file.print(u.compressAsString());
        }

        file.close();
    }

    // Preverjanje ce uprabnik s tem imenom ne obstaja, potem ga doda v users.txt fajl
    public void registrirajUporabnik(Uporabnik user) {
        boolean helper = false;
		
		for(Uporabnik u : this.seznamUporabnikov) {
			if(u.getUporabniskoIme().equals(user.getUporabniskoIme())) {
				helper = true;
				break;
			}
		}
		
		if(!helper) {
			this.seznamUporabnikov.add(user);
            System.out.println("Uspesno ste se registrirali v program!");
            try {
                this.saveUserInFile("users.txt");
            } catch (Exception e) {
                System.out.println("Can't save user in file. File does not exist!");
            }
		}
		else {
			System.out.println("\r\nUporabnik s tem uporabniškim imenom ze obstaja!\r\n");
		}
    }

    public void saveReservationInFile(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName, false);
		PrintWriter file = new PrintWriter(fw);

        for (Rezervacija r : this.seznamRezervacije) {
            file.print(r.compressAsString());
        }

        file.close();
    }

    public void rezervirajTermin(Rezervacija r) {
        for (Pocitnice p : this.seznamPocitnice) {
            for (Termin t : p.getSeznamTerminov()) {
                if (t.getId() == r.getTerminId()) {
                    int rSteviloOseb = r.getSteviloOdraslih() + r.getSteviloOtrok();
                    int terminProsto = p.getMaxSteviloOseb() - t.getSteviloOsebRezervirano();
                    if (rSteviloOseb <= terminProsto) {
                        t.setSteviloOsebRezervirano(t.getSteviloOsebRezervirano() + rSteviloOseb);
                        this.getLoggedUser().dodajRezervacija(r);
                        this.seznamRezervacije.add(r);
                        try {
                            this.saveReservationInFile("rezervacije.txt");
                            System.out.println("\r\nUspesno ste rezervirali termin, hvala na zaupanje.\r\n");
                            System.out.println(t.compressToString(p, t.getId()));
                        } catch (Exception e) {
                            System.out.println("Can't save user in file. File does not exist!");
                        }
                    } else {
                        System.out.println("\r\nNi dovolnjno prostih mest za ta termin.\r\n");
                    }
                    break;
                }
            }
        }
    }

    public void obnoviTermin(Termin obnovenTermin, ArrayList<Termin> seznamTerminov) {
        for (Termin t : seznamTerminov) {
            if (t.getId() == obnovenTermin.getId()) {
                t.setSteviloOsebRezervirano(obnovenTermin.getSteviloOsebRezervirano());
            }
        }
    }

    public void readFromFile(String fileName) throws Exception {
        FileReader fr = new FileReader(fileName);
		BufferedReader file = new BufferedReader(fr);

		ArrayList<String> data;

        while (file.ready()) {

            String row = file.readLine().trim();

            if(row.equals("*U")) {
				data = new ArrayList<String>();
				while(file.ready() && !row.equals("##")) {
					row = file.readLine().trim();
					data.add(row);
				}
				Uporabnik newUser = Uporabnik.readFromArray(data);
				this.seznamUporabnikov.add(newUser);
			} else if (row.equals("*R")) {
                data = new ArrayList<String>();
                while (file.ready() && !row.equals("##")) {
                    row = file.readLine().trim();
					data.add(row);
                }
                Rezervacija rezervacija = Rezervacija.readFromArray(data);
                this.seznamRezervacije.add(rezervacija);
            } else if (row.equals("*P")) {
                data = new ArrayList<String>();
                while (file.ready() && !row.equals("##")) {
                    row = file.readLine().trim();
					data.add(row);
                }
                Potovanje potovanje = Potovanje.readFromArray(data);
                this.seznamPocitnice.add(potovanje);
            } else if (row.equals("*S")) {
                data = new ArrayList<String>();
                while (file.ready() && !row.equals("##")) {
                    row = file.readLine().trim();
					data.add(row);
                }
                Smucanje smucanje = Smucanje.readFromArray(data);
                this.seznamPocitnice.add(smucanje);
            } else if (row.equals("*Ka")) {
                data = new ArrayList<String>();
                while (file.ready() && !row.equals("##")) {
                    row = file.readLine().trim();
					data.add(row);
                }
                Kampiranje kampiranje = Kampiranje.readFromArray(data);
                this.seznamPocitnice.add(kampiranje);
            } else if (row.equals("*Kr")) {
                data = new ArrayList<String>();
                while (file.ready() && !row.equals("##")) {
                    row = file.readLine().trim();
					data.add(row);
                }
                Krizarjenje krizarjenje = Krizarjenje.readFromArray(data);
                this.seznamPocitnice.add(krizarjenje);
            }

        }

        file.close();
    }

    public void prikaziVsePocitnice() {
        System.out.println("\r\n******* SEZNAM POCITNICE *******");
        for (Pocitnice p : this.seznamPocitnice) {
            System.out.println(p.toString());
        }
    }

    // Biranje pocitnice po datum
    public void prikaziPocitniceCas() throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        try {
            System.out.println("\r\nVnesite datum od (format 11-22-3333):\r\n");
            String inputOd = br.readLine().trim().toLowerCase();
            LocalDate dateFrom = Termin.getDateFromString(inputOd);
            System.out.println("\r\nVnesite datum do (format 11-22-3333):\r\n");
            String inputDo = br.readLine().trim().toLowerCase();
            LocalDate dateTo = Termin.getDateFromString(inputDo);

            boolean found = false;
    
            for (Pocitnice p : this.seznamPocitnice) {
                boolean terminFound = false;
                for (Termin t : p.getSeznamTerminov()) {
                    if (t.getDatumPrihod().isAfter(dateFrom) && t.getDatumOdhod().isBefore(dateTo)) {
                        terminFound = true;
                        found = true;
                    }
                }
                if (terminFound) System.out.println(p.toString());
            }

            if (!found) System.out.println("Ni pocitnice v tem casovnem okviru!");
        } catch (Exception e) {
            System.out.println("\r\nVnesli ste napacen format datuma!\r\n");
        }
    }

    // Biranje pocitnice po drzavi
    public void prikaziPocitniceDrzava() throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        System.out.println("\r\nVnesite drzavo: \r\n");

        String input = br.readLine().trim().toLowerCase();
        boolean found = false;

        for (Pocitnice p : this.seznamPocitnice) {
            if (p.getDrzava().toLowerCase().equals(input)) {
                found = true;
                System.out.println(p.toString());
            }
        }

        if (!found) System.out.println("\r\nTrenutno ni pocitnite v te drzavi.\r\n");
    }

    // Biranje pocitnice po ceni
    public void prikaziPocitniceCena() throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        System.out.println("\r\nVnesi cena od: \r\n");
        Integer cenaOd = Integer.parseInt(br.readLine().trim());
        System.out.println("\r\nVnesi cena do: \r\n");
        Integer cenaDo = Integer.parseInt(br.readLine().trim());
        boolean found = false;

        for (Pocitnice p : this.seznamPocitnice) {
            if (p.getCena() > cenaOd && p.getCena() < cenaDo) {
                found = true;
                System.out.println(p.toString());
            }
        }

        if (!found) System.out.println("\r\nNi pocitnice v tem cenovnem okviru.\r\n");
    }

    // Biranje pocitnice po tipu
    public void prikaziPocitniceTip() throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        System.out.print("\r\nPocitnice na voljo: Potovanje, Krizarjenje, Kampiranje, Smucanje.\r\n");

        String input = br.readLine().trim().toLowerCase();
        boolean found = false;

        switch (input) {
            case "potovanje":
                for (Pocitnice p : this.seznamPocitnice) {
                    if (p instanceof Potovanje) {
                        found = true;
                        System.out.println(p.toString());
                    }
                }
                break;

            case "krizarjenje":
                for (Pocitnice p : this.seznamPocitnice) {
                    if (p instanceof Krizarjenje) {
                        found = true;
                        System.out.println(p.toString());
                    }
                }
                break;

            case "kampiranje":
                for (Pocitnice p : this.seznamPocitnice) {
                    if (p instanceof Kampiranje) {
                        found = true;
                        System.out.println(p.toString());
                    }
                }
                break;

            case "smucanje":
                for (Pocitnice p : this.seznamPocitnice) {
                    if (p instanceof Smucanje) {
                        found = true;
                        System.out.println(p.toString());
                    }
                }
                break;

            default:
                found = true;
                System.out.println("\r\nVnesi ste napacen tip pocitnice!\r\n");
                break;
        }

        if (!found) System.out.println("\r\nTrenutno pocitnice tipa '" + input + "' nimamo na voljo.\r\n");
    }

}
