import java.util.*;
class Legesystem {
    public static void main(String[] args) {
        int avslutt = 0;
        int input = 0;

        Pasient pasient1 = new Pasient("Jens Hans Olsen", "11111143521");
        Pasient pasient2 = new Pasient("Petrolina Swiq", "24120099343");
        Pasient pasient3 = new Pasient("Sven Svendsen", "10111224244");
        Pasient pasient4 = new Pasient("Juni Olsen", "21049563451");

        Legemiddel legemiddel1 = new LegemiddelA("Predizol", 450, 75, 8);
        Legemiddel legemiddel2 = new LegemiddelB("Paralgin Forte", 65, 400, 5);
        Legemiddel legemiddel3 = new LegemiddelC("Placebo Pianissimo", 10, 0);
        Legemiddel legemiddel4 = new LegemiddelC("Ibux", 240, 200);

        Lege lege1 = new Fastlege("Dr. Cox", 0);
        Lege lege2 = new Fastlege("Dr. Wilson", 0);
        Lege lege3 = new Fastlege("Dr. House", 12345);
        Lege lege4 = new Fastlege("Dr. Hillestad Lovold", 0);

        Resept resept1 = new BlåResept(legemiddel1, lege1, pasient3, 3);
        Resept resept2 = new HvitResept(legemiddel3, lege4, pasient4, 10000);
        Resept resept3 = new Presepter(legemiddel2, lege3, pasient2, 0);
        Resept resept4 = new Militærresepter(legemiddel4, lege4, pasient4, 2);

        Liste<Pasient> pasienterL = new Lenkeliste<>();
        Liste<Legemiddel> legemiddelerL = new Lenkeliste<>();
        Liste<Resept> resepterL = new Lenkeliste<>();
        Liste<Lege> legerL = new SortertLenkeliste<>();

        pasienterL.leggTil(pasient1);
        pasienterL.leggTil(pasient2);
        pasienterL.leggTil(pasient3);
        pasienterL.leggTil(pasient4);

        legemiddelerL.leggTil(legemiddel1);
        legemiddelerL.leggTil(legemiddel2);
        legemiddelerL.leggTil(legemiddel3);
        legemiddelerL.leggTil(legemiddel4);

        resepterL.leggTil(resept1);
        resepterL.leggTil(resept2);
        resepterL.leggTil(resept3);
        resepterL.leggTil(resept4);

        legerL.leggTil(lege1);
        legerL.leggTil(lege2);
        legerL.leggTil(lege3);
        legerL.leggTil(lege4);

        //Legesystem ls = new Legesystem();
        //ls.meny();

        while (avslutt == 0) {
            System.out.println("For utskrift av fullstendig oversikt over"
            + " personer, leger, legemidler og resepter, tast 1.");
            System.out.println("For å oprette og legge til et nytt element"
            + " i systemet, tast 2.");
            System.out.println("For å bruke en gitt resept fra en pasient sin"
            + " liste, tast 3");
            System.out.println("For å skrive ut statistikk, tast 4.");
            System.out.println("For å avslutte, tast 6.");
            System.out.println();

            Scanner scanner = new Scanner(System.in);
            String string1;
            String string2;
            String string3;
            String string4;
            String string5;
            int int1;
            int int2;
            int int3;
            int int4;
            int int5;
            double d1;
            double d2;

            input = scanner.nextInt();
            System.out.println("Du valgte " + input + ".");
            System.out.println();

            if (input == 1) {
                //skrivUtAlt();
                for (Pasient p : pasienterL) {System.out.println(p);}
                for (Legemiddel l : legemiddelerL) {System.out.println(l);}
                for (Lege lg : legerL) {System.out.println(lg);}
                for (Resept r : resepterL) {System.out.println(r);}
                System.out.println();
            } else if (input == 2) {
                System.out.println("1 for lege, 2 for pasient, 3 for resept"
                + ", 4 for legemiddel:");
                System.out.println();
                input = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Du valgte " + input + ".");
                if (input == 1) {
                    System.out.println("Legens navn: ");
                    string1 = scanner.nextLine();
                    System.out.println("Avtalenummer: ");
                    int1 = scanner.nextInt();
                    scanner.nextLine();
                    Lege nyLege = new Fastlege(string1, int1);
                    legerL.leggTil(nyLege);
                    System.out.println(nyLege + " lagt til.");
                    System.out.println();

                } else if (input == 2) {
                    System.out.println("Pasientens navn: ");
                    string1 = scanner.nextLine();
                    System.out.println("Fødselsnummer: ");
                    string2 = scanner.nextLine();
                    Pasient nyPasient = new Pasient(string1, string2);
                    pasienterL.leggTil(nyPasient);
                    System.out.println(nyPasient + " lagt til.");
                    System.out.println();

                } else if (input == 3) {

                    for (Legemiddel lm : legemiddelerL) {System.out.println(lm);}
                    System.out.println("Legemiddel (ID): ");
                    int1 = scanner.nextInt();
                    scanner.nextLine();

                    int lId = 0;
                    for (Lege l : legerL) {System.out.println((lId++) + " : " + l);}
                    System.out.println("Lege (ID): ");
                    int2 = scanner.nextInt();
                    scanner.nextLine();

                    for (Pasient p : pasienterL) {System.out.println(p);}
                    System.out.println("Pasient (ID): ");
                    int3 = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Reit: ");
                    int4 = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Type (Blå (1), Hvit (2), "
                    + "Prevensjon (3), Militær (4) : ");
                    int5 = scanner.nextInt();
                    scanner.nextLine();
                    Resept nyResept;
                    if(int5 == 1) {
                        nyResept = new BlåResept(legemiddelerL.hent(int1),
                        legerL.hent(int2), pasienterL.hent(int3), int4);
                        resepterL.leggTil(nyResept);
                        System.out.println(nyResept + " lagt til.");
                        System.out.println();

                    } else if (int5 == 2) {
                        nyResept = new HvitResept(legemiddelerL.hent(int1),
                        legerL.hent(int2), pasienterL.hent(int3), int4);
                        resepterL.leggTil(nyResept);
                        System.out.println(nyResept + " lagt til.");
                        System.out.println();

                    } else if (int5 == 3) {
                        nyResept = new Presepter(legemiddelerL.hent(int1),
                        legerL.hent(int2), pasienterL.hent(int3), int4);
                        resepterL.leggTil(nyResept);
                        System.out.println(nyResept + " lagt til.");
                        System.out.println();

                    } else if (int5 == 4) {
                        nyResept = new Militærresepter(legemiddelerL.hent(int1),
                        legerL.hent(int2), pasienterL.hent(int3), int4);
                        resepterL.leggTil(nyResept);
                        System.out.println(nyResept + " lagt til.");
                        System.out.println();

                    }
                } else if (input == 4) {
                    System.out.println("Legemiddel A(1), B(2), eller C(3)?");
                    input = scanner.nextInt();
                    scanner.nextLine();
                    if (input == 1) {
                        Legemiddel nyttLegemiddel;
                        System.out.println("Navn:");
                        string2 = scanner.nextLine();

                        System.out.println("Pris:");
                        d1 = scanner.nextDouble();
                        //d1 = Double.parseDouble(scanner.nextLine());
                        //Integer.parseInt(string);
                        scanner.nextLine();

                        System.out.println("Virkestoff:");
                        d2 = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.println("Styrke:");
                        int1 = scanner.nextInt();
                        scanner.nextLine();

                        nyttLegemiddel = new LegemiddelA(string2, d1, d2, int1);
                        legemiddelerL.leggTil(nyttLegemiddel);
                        System.out.println(nyttLegemiddel + " lagt til.");
                        System.out.println();

                    } else if (input == 2) {
                        Legemiddel nyttLegemiddel;
                        System.out.println("Navn:");
                        string2 = scanner.nextLine();

                        System.out.println("Pris:");
                        d1 = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.println("Virkestoff:");
                        d2 = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.println("Styrke:");
                        int1 = scanner.nextInt();
                        scanner.nextLine();

                        nyttLegemiddel = new LegemiddelB(string2, d1, d2, int1);
                        legemiddelerL.leggTil(nyttLegemiddel);
                        System.out.println(nyttLegemiddel + " lagt til.");
                        System.out.println();
                    } else if (input == 3) {
                        Legemiddel nyttLegemiddel;
                        System.out.println("Navn:");
                        string2 = scanner.nextLine();

                        System.out.println("Pris:");
                        d1 = scanner.nextDouble();
                        scanner.nextLine();

                        System.out.println("Virkestoff:");
                        d2 = scanner.nextDouble();
                        scanner.nextLine();

                        nyttLegemiddel = new LegemiddelC(string2, d1, d2);
                        legemiddelerL.leggTil(nyttLegemiddel);
                        System.out.println(nyttLegemiddel + " lagt til.");
                        System.out.println();
                    }
                }

            } else if (input == 3) {
                System.out.println("Hvilken pasient vil du se resepter for?");
                for (Pasient p : pasienterL) {System.out.println(p);}
                int1 = scanner.nextInt();
                scanner.nextLine();
                Pasient p = pasienterL.hent(int1);
                System.out.println("Valgt pasient " + p);
                System.out.println("Hvilken resept vil du bruke: ");
                Liste<Resept> resepter = p.hentListe();
                int rId = 0;
                for (Resept r : resepter) {System.out.println((rId++) + " : "
                + r.hentLegemiddel().hentNavn() + " | "
                + r.hentReit() + " reit.");}
                int2 = scanner.nextInt();
                scanner.nextLine();
                Resept r = resepter.hent(int2);
                if (r.bruk() != true) {
                    System.out.println("Kan ikke bruke resepten, reit er 0.");
                } else{
                    r.taReit();
                    System.out.println("Brukte resepten på "
                    + r.hentLegemiddel().hentNavn() + "Antall reit igjen: "
                    + r.hentReit());
                }

            } else if (input == 4) {
                int teller = 0;
                for (Resept r : resepterL) {
                    if (r.hentLegemiddel().erVanedannende() == true) {teller++;}
                }
                System.out.println("Totalt antall utskrevne resepter på "
                + "vanedannende legemidler: " + teller);

                teller = 0;
                for (Resept r : resepterL) {
                    if (r.erMilitær() == true) {
                        if (r.hentLegemiddel().erVanedannende() == true) {teller++;}
                    }
                }
                System.out.println("Antall vanedannende resepter utskrevne til "
                + "militære: " + teller);

                for (Lege l : legerL) {
                    teller = 0;
                    Liste<Resept> liste = l.hentListeResepter();
                    for (Resept r : liste) {
                        if (r.hentLegemiddel().erNarkotisk() == true) {
                            teller++;
                            System.out.println(l + " | " + teller);
                        }
                    }
                }

                for (Pasient p : pasienterL) {
                    teller = 0;
                    Liste<Resept> liste = p.hentListe();
                    for (Resept r : liste) {
                        if (r.hentLegemiddel().erNarkotisk() == true) {
                            if (r.bruk() == true) {
                                teller++;
                                System.out.println(p + " | " + teller);
                            }
                        }
                    }
                }

            } else if (input == 6) {
                avslutt = input;
                System.out.println("Programmet ble avlsuttet av brukeren.");
            }
        }
    }
}
