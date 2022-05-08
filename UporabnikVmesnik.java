import java.io.*;
import java.util.*;

public class UporabnikVmesnik {
    public static void main(TuristickaAgencija ta) throws Exception {
        System.out.println("\r\nHey " + ta.getLoggedUser().getIme() + ". Dobrodosli v nasoj turisticni agenciji.\r\n");

        InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

        char izbira;

        while (ta.isUserLoggedIn()) {
            if (ta.getLoggedUser().isJeAdmin()) {
                System.out.println("Pritisni (U) za pogled vsi uporabniki.");
            }
            System.out.println("Pritisnite (P) za pogled vse pocitnice.");
            System.out.println("Pritisnite (1) za iskanje pocitnice po casovnem okvirju.");
            System.out.println("Pritisnite (2) za iskanje pocitnice po drzavi.");
            System.out.println("Pritisnite (3) za iskanje pocitnice po cenovnem okvirju.");
            System.out.println("Pritisnite (4) za iskanje pocitnice po tipu.");
            System.out.println("Pritisnite (E) za odjava iz programa.");
            System.out.println("Pritisni (Q) za prekinitev programa.");

            izbira = br.readLine().trim().toLowerCase().charAt(0);

            switch(izbira) {
                case 'u':
                    if (ta.getLoggedUser().isJeAdmin()) {
                        for (Uporabnik u : ta.getSeznamUporabnikov()) {
                            System.out.println(u.toString());
                        }
                    }
                    break;

                case 'p':
                    ta.prikaziVsePocitnice();
                    break;

                case '1':
                    ta.prikaziPocitniceCas();
                    break;

                case '2':
                    ta.prikaziPocitniceDrzava();
                    break;

                case '3':
                    ta.prikaziPocitniceCena();
                    break;

                case '4':
                    ta.prikaziPocitniceTip();
                    break;

                case 'e':
                    ta.setLoggedUser(null);
                    System.out.println("Uspesno ste se odjavili od turisticne agencije.\r\n");
                    UporabniskiVmesnik.main(new String[0]);
                    break;

                case 'q':
                    return;

                default:
					System.out.println("Pritisnili ste napacno izbiro!");
					System.out.println();
            }
        }
    }

}
