package no.hvl.dat107;

import no.hvl.dat107.dao.AnsattDAO;
import no.hvl.dat107.dao.AvdelingDAO;
import no.hvl.dat107.dao.ProsjektDAO;
import no.hvl.dat107.dao.ProsjektDeltagelseDAO;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;
import no.hvl.dat107.entity.Prosjekt;
import no.hvl.dat107.entity.ProsjektDeltagelse;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AnsattDAO ansattDAO = new AnsattDAO();
        AvdelingDAO avdelingDAO = new AvdelingDAO();
        ProsjektDAO prosjektDAO = new ProsjektDAO();
        ProsjektDeltagelseDAO prosjektDeltagelseDAO = new ProsjektDeltagelseDAO();

        Scanner s = new Scanner(System.in);

        boolean quit = false;
        while (!quit) {
            System.out.println("--------------------");
            System.out.println("( 0) Avslutt");
            System.out.println("( 1) Søk etter ansatt på id");
            System.out.println("( 2) Søk etter ansatt på brukernavn");
            System.out.println("( 3) Utlisting av alle ansatte");
            System.out.println("( 4) Oppdater en ansatt sin stilling");
            System.out.println("( 5) Legg til en ny ansatt");
            System.out.println("( 6) Søk etter avdeling på id");
            System.out.println("( 7) Utlisting av alle ansatte ved gitt avdeling");
            System.out.println("( 8) Oppdater ansatt sin avdeling");
            System.out.println("( 9) Legg til en ny avdeling");
            System.out.println("(10) Legg til et nytt prosjekt");
            System.out.println("(11) Registrer prosjektdeltagelse");
            System.out.println("(12) Før timer på ansatt i prosjekt");
            System.out.println("(13) Informasjon om prosjekt");
            System.out.print("> ");

            int valg = s.nextInt(); s.nextLine();

            System.out.println();

            switch (valg) {
                case 0: {
                    quit = true;
                } break;

                case 1: {
                    System.out.print("ansatt id: ");
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
                    System.out.print("ansatt id: ");
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

                    System.out.print("avdeling id: ");
                    int avdelingId = s.nextInt(); s.nextLine();

                    Ansatt ansatt = new Ansatt(brukernavn, fornavn, etternavn, stilling, manedslonn, avdelingId);
                    ansattDAO.leggTilAnsatt(ansatt);
                } break;

                case 6: {
                    System.out.print("avdeling id: ");
                    int id = s.nextInt(); s.nextLine();
                    Avdeling avdeling = avdelingDAO.finnAvdelingMedId(id);
                    System.out.println(avdeling.toString());
                } break;

                case 7: {
                    System.out.print("avdeling id: ");
                    int avdelingId = s.nextInt(); s.nextLine();
                    ansattDAO.listAnsatteVedAvdeling(avdelingId);
                } break;

                case 8: {
                    System.out.println("ansatt id: ");
                    int id = s.nextInt(); s.nextLine();

                    System.out.println("ny avdeling id: ");
                    int avdelingId = s.nextInt(); s.nextLine();

                    ansattDAO.oppdaterAnsattAvdeling(id, avdelingId);
                } break;

                case 9: {
                    System.out.print("navn: ");
                    String navn = s.nextLine();

                    System.out.print("sjef id: ");
                    int sjef_id = s.nextInt(); s.nextLine();

                    Avdeling avdeling = new Avdeling(navn, sjef_id);
                    avdelingDAO.leggTilAvdeling(avdeling);
                } break;

                case 10: {
                    System.out.print("navn: ");
                    String navn = s.nextLine();

                    System.out.print("beskrivelse: ");
                    String beskrivelse = s.nextLine();

                    Prosjekt prosjekt = new Prosjekt(navn, beskrivelse, new ArrayList<>());
                    prosjektDAO.leggTilProsjekt(prosjekt);
                } break;

                case 11: {
                    System.out.print("ansatt id: ");
                    int ansattId = s.nextInt(); s.nextLine();

                    System.out.print("prosjekt id: ");
                    int prosjektId = s.nextInt(); s.nextLine();

                    System.out.print("rolle: ");
                    String rolle = s.nextLine();

                    Ansatt ansatt = ansattDAO.finnAnsattMedId(ansattId);
                    Prosjekt prosjekt = prosjektDAO.finnProsjektMedId(prosjektId);

                    ProsjektDeltagelse prosjektDeltagelse = new ProsjektDeltagelse(prosjekt, ansatt, rolle);
                    prosjektDeltagelseDAO.leggTilProsjektDeltagelse(prosjektDeltagelse);
                } break;

                case 12: {
                    System.out.print("ansatt id: ");
                    int ansattId = s.nextInt(); s.nextLine();

                    System.out.print("prosjekt id: ");
                    int prosjektId = s.nextInt(); s.nextLine();

                    System.out.print("timer: ");
                    int timer = s.nextInt(); s.nextLine();

                    Ansatt ansatt = ansattDAO.finnAnsattMedId(ansattId);
                    Prosjekt prosjekt = prosjektDAO.finnProsjektMedId(prosjektId);

                    for (ProsjektDeltagelse deltagelse : ansatt.getDeltagelser()) {
                        if (deltagelse.getProsjekt().getId() == prosjekt.getId()) {
                            prosjektDeltagelseDAO.leggTilTimer(deltagelse, timer);
                        }
                    }
                } break;

                case 13: {
                    System.out.print("prosjekt id: ");
                    int prosjektId = s.nextInt(); s.nextLine();

                    Prosjekt prosjekt = prosjektDAO.finnProsjektMedId(prosjektId);
                    System.out.println(prosjekt.toString());

                    int timerTotalt = 0;
                    for (ProsjektDeltagelse deltagelse : prosjekt.getDeltagelser()) {
                        timerTotalt += deltagelse.getTimer();
                        System.out.println("    " + deltagelse.toString());
                    }
                    System.out.println("Timer totalt: " + timerTotalt);
                } break;
            }
        }
    }
}
