class TestLegemiddel{
    public static void main(String[] args) {

        LegemiddelA A = new LegemiddelA("NavnA", 50, 2.5, 3);

        LegemiddelB B = new LegemiddelB("NavnB", 49.9, 2.9, 2);

        LegemiddelC C = new LegemiddelC("NavnC", 35.5, 9);

        System.out.println("Navnet til A: " + A.hentNavn());
        System.out.println("Prisen til A: " + A.hentPris() + "kr");
        System.out.println("Virkestoffet til A: " + A.hentVirkestoff());
        System.out.println("Styrke til A: " + A.hentNarkotiskStyrke());
        System.out.println("Id: " + A.hentId());
        System.out.println();
        System.out.println("Navnet til B: " + B.hentNavn());
        System.out.println("Prisen til B: " + B.hentPris() + "kr");
        System.out.println("Virkestoffet til B: " + B.hentVirkestoff());
        System.out.println("Styrke til B: " + B.hentVanedannendeStyrke());
        System.out.println("Id: " + B.hentId());
        System.out.println();
        System.out.println("Navnet til C: " + C.hentNavn());
        System.out.println("Prisen til C: " + C.hentPris() + "kr");
        System.out.println("Virkestoffet til C: " + C.hentVirkestoff());
        System.out.println("Id: " + C.hentId());

    }
}
