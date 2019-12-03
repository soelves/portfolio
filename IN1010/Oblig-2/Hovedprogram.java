class Hovedprogram{
    public static void main(String[] args) {
        LegemiddelA Ibux = new LegemiddelA("Ibux", 349.9, 2.5, 3);
        LegemiddelB EpiPen = new LegemiddelB("EpiPen", 249.9, 2.9, 2);
        LegemiddelC Valium = new LegemiddelC("Valium", 555.5, 9);
        Fastlege vegar = new Fastlege("Vegar Beider", 420);

        Militærresepter reseptM = new Militærresepter(EpiPen, vegar, 101, 5);
        Presepter reseptP = new Presepter(Valium, vegar, 102, 6);
        BlåResept reseptB = new BlåResept(Ibux, vegar, 104, 12);

        //Fastlege fast = (Fastlege) reseptM.hentLege();
        //System.out.println(fast.hentAvtalenummer());

        System.out.println("Info om legemiddel:");
        System.out.println("Navn: " + reseptM.hentLegemiddel().hentNavn()
        + " | Pris: " + reseptM.hentLegemiddel().hentPris()
        + " | Virkestoff: " + reseptM.hentLegemiddel().hentVirkestoff()
        + " | Styrke: " + reseptM.hentLegemiddel().hentVanedannendeStyrke()
        + " | Id: " + reseptM.hentLegemiddel().hentId());
        Fastlege fastM = (Fastlege) reseptM.hentLege();
        System.out.println("Navnet til legen: "
        + fastM.hentNavn());
        System.out.println("Avtalenummer: " + fastM.hentAvtalenummer());
        System.out.println("Pasientens Id: " + reseptM.hentPasientId());
        System.out.println("Antall bruk igjen: " + reseptM.hentReit());
        System.out.println("Resepten er gyldig: " + reseptM.bruk());
        System.out.println("Farge: " + reseptM.farge());
        System.out.println("Pris: " + reseptM.prisAaBetale());
        System.out.println("Id: " + reseptM.hentId());
        System.out.println();

        System.out.println();
        System.out.println("Info om legemiddel:");
        System.out.println("Navn: " + reseptP.hentLegemiddel().hentNavn()
        + " | Pris: " + reseptP.hentLegemiddel().hentPris()
        + " | Virkestoff: " + reseptP.hentLegemiddel().hentVirkestoff()
        + " | Styrke: " + reseptP.hentLegemiddel().hentNarkotiskStyrke()
        + " | Id: " + reseptP.hentLegemiddel().hentId());
        Fastlege fastP = (Fastlege) reseptP.hentLege();
        System.out.println("Navnet til legen: "
        + fastP.hentNavn());
        System.out.println("Avtalenummer: " + fastP.hentAvtalenummer());
        System.out.println("Pasientens Id: " + reseptP.hentPasientId());
        System.out.println("Antall bruk igjen: " + reseptP.hentReit());
        System.out.println("Resepten er gyldig: " + reseptP.bruk());
        System.out.println("Farge: " + reseptP.farge());
        System.out.println("Pris: " + reseptP.prisAaBetale());
        System.out.println("Id: " + reseptP.hentId());
        System.out.println();

        System.out.println("Info om legemiddel:");
        System.out.println("Navn: " + reseptB.hentLegemiddel().hentNavn()
        + " | Pris: " + reseptB.hentLegemiddel().hentPris()
        + " | Virkestoff: " + reseptB.hentLegemiddel().hentVirkestoff()
        + " | Id: " + reseptB.hentLegemiddel().hentId());
        Fastlege fastB = (Fastlege) reseptB.hentLege();
        System.out.println("Navnet til legen: "
        + fastB.hentNavn());
        System.out.println("Avtalenummer: " + fastB.hentAvtalenummer());
        System.out.println("Pasientens Id: " + reseptB.hentPasientId());
        System.out.println("Antall bruk igjen: " + reseptB.hentReit());
        System.out.println("Resepten er gyldig: " + reseptB.bruk());
        System.out.println("Farge: " + reseptB.farge());
        System.out.println("Pris: " + reseptB.prisAaBetale());
        System.out.println("Id: " + reseptB.hentId());
        System.out.println();
    }
}
