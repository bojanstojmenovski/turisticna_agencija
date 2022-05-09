import java.io.*;
import java.util.*;

public class UporabniskiVmesnik {
	
	public static void main(String[] args) throws Exception {
		
		TuristickaAgencija ta = new TuristickaAgencija();

		// Vnos uporanikov iz datoteke
		ta.readFromFile("users.txt");
		ta.readFromFile("pocitnice.txt");
		ta.readFromFile("rezervacije.txt");
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		System.out.println("\r\n***************** Dobrodosli v programu 'Turisticka Agencija' *****************\r\n");

		char izbira;
		
		while(!ta.isUserLoggedIn()) {
			System.out.println("Pritisni (L) za logiranje v program.");
			System.out.println("Ce se nimas racun, pritisni (R) za registriranje v program.");
			System.out.println("Pritisni (Q) za prekinitev programa.");
			System.out.println();
			
			izbira = br.readLine().trim().toLowerCase().charAt(0);
			
			switch(izbira) {
				case 'l':
					ta.logIn();
					break;
				case 'r':
					Uporabnik user = Uporabnik.kreirajUporabnik();
					ta.registrirajUporabnik(user);
					break;
				case 'q':
					return;
				default:
					System.out.println("Pritisnili ste napacno izbiro!");
					System.out.println();
			}
		}

		if(ta.isUserLoggedIn()) {
			UporabnikVmesnik.main(ta);
		}
		
	}
	
}