package no.hvl.dat107;

import no.hvl.dat107.dao.AnsattDAO;
import no.hvl.dat107.entity.Ansatt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AnsattDAO ansattDAO = new AnsattDAO();

        Scanner s = new Scanner(System.in);

        boolean quit = false;
        while (!quit) {
            System.out.println("-----");
            System.out.println("(0) Avslutt");
            System.out.println("(1) Søk etter ansatt på id");
            System.out.println("(2) Søk etter ansatt på brukernavn");
            System.out.println("(3) Utlisting av alle ansatte");
            System.out.println("(4) Oppdater en ansatt sin stilling");
            System.out.println("(5) Legg til en ny ansatt");
            System.out.print("> ");

            int valg = s.nextInt(); s.nextLine();

            switch (valg) {
                case 0: {
                    quit = true;
                } break;

                case 1: {
                    System.out.print("id: ");
                    int id = s.nextInt(); s.nextLine();
                    Ansatt ansatt = ansattDAO.finnAnsattMedId(id);
                    System.out.println(ansatt.toString());
                } break;

                case 2: {
                    System.out.print("brukernavn: ");
                    String brukernavn = s.nextLine();
                    Ansatt ansatt = ansattDAO.finnAnsattMedBrukernavn(brukernavn);
                    System.out.println(ansatt.toString());
                } break;

                case 3: {
                    ansattDAO.listAnsatte();
                } break;

                case 4: {
                    System.out.print("id: ");
                    int id = s.nextInt(); s.nextLine();
                    System.out.print("ny stilling: ");
                    String stilling = s.nextLine();
                    ansattDAO.oppdaterAnsattStilling(id, stilling);
                } break;

                case 5: {
                    System.out.print("brukernavn: ");
                    String brukernavn = s.nextLine();

                    System.out.print("fornavn: ");
                    String fornavn = s.nextLine();

                    System.out.print("etternavn: ");
                    String etternavn = s.nextLine();

                    System.out.print("stilling: ");
                    String stilling = s.nextLine();

                    System.out.print("månedslønn: ");
                    int manedslonn = s.nextInt(); s.nextLine();

                    Ansatt ansatt = new Ansatt(brukernavn, fornavn, etternavn, stilling, manedslonn);
                    ansattDAO.leggTilAnsatt(ansatt);
                } break;
            }
        }
    }
}
