class TestResepter{
    public static void main(String[] args) {

        LegemiddelA a = new LegemiddelA("NavnA", 50, 2.5, 3);
        LegemiddelB b = new LegemiddelB("NavnB", 249.9, 2.9, 2);
        LegemiddelC c = new LegemiddelC("NavnC", 35.5, 9);
        Lege per = new Lege("Per", 420);

        Militærresepter reseptM = new Militærresepter(b, per, 101, 5);
        Presepter reseptP = new Presepter(c, per, 102, 6);
        BlåResept reseptB = new BlåResept(a, per, 104, 12);

        System.out.println("Info om legemiddel:");
        System.out.println("Navn: " + reseptM.hentLegemiddel().hentNavn()
        + " | Pris: " + reseptM.hentLegemiddel().hentPris()
        + " | Virkestoff: " + reseptM.hentLegemiddel().hentVirkestoff()
        + " | Styrke: " + reseptM.hentLegemiddel().hentVanedannendeStyrke()
        + " | Id: " + reseptM.hentLegemiddel().hentId());
        System.out.println("Navnet til legen: "
        + reseptM.hentLege().hentNavn());
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
        System.out.println("Navnet til legen: "
        + reseptP.hentLege().hentNavn());
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
        System.out.println("Navnet til legen: "
        + reseptB.hentLege().hentNavn());
        System.out.println("Pasientens Id: " + reseptB.hentPasientId());
        System.out.println("Antall bruk igjen: " + reseptB.hentReit());
        System.out.println("Resepten er gyldig: " + reseptB.bruk());
        System.out.println("Farge: " + reseptB.farge());
        System.out.println("Pris: " + reseptB.prisAaBetale());
        System.out.println("Id: " + reseptB.hentId());
        System.out.println();
    }
}
