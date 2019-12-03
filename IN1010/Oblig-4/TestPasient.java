class TestPasient {
    public static void main(String[] args) {
        Pasient Sølve = new Pasient("Sølve Selander", "070498");

        LegemiddelA Ibux = new LegemiddelA("Ibux", 349.9, 2.5, 3);
        LegemiddelB EpiPen = new LegemiddelB("EpiPen", 249.9, 2.9, 2);
        LegemiddelC Valium = new LegemiddelC("Valium", 555.5, 9);
        Fastlege vegar = new Fastlege("Vegar Beider", 420);

        Militærresepter reseptM = new Militærresepter(EpiPen, vegar, Sølve, 5);
        Presepter reseptP = new Presepter(Valium, vegar, Sølve, 6);
        BlåResept reseptB = new BlåResept(Ibux, vegar, Sølve, 12);

        Sølve.leggTil(reseptM);
        System.out.println(Sølve);
        System.out.println(vegar);
        System.out.println(Ibux);
        System.out.println(EpiPen);
        System.out.println(Valium);
        System.out.println(reseptB);
        System.out.println(reseptM);
        System.out.println(reseptP);
    }
}
