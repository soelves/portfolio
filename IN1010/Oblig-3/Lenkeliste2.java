class Lenkeliste2<T> implements Liste<T> {
//Klassen Node som blir brukt i Lenkelista.
    class Node {
        Node neste = null;
        T data;

        Node(T x) {data = x;}
    }

//Start-node, peker på den første noden som inneholder data og neste/forrige.
    private Node start = null;

//Hjelp til stoerrelse
    private int iBruk;

//Metode som finner størrelsen på Lenkelista ved hjelp av en while-løkke.
    @Override
    public int stoerrelse(){
        Node peker = start;
        int n = 0;

        while (peker != null) {n++; peker = peker.neste;}

        iBruk = n;

        return iBruk;
    }

//Legger til en node i en posisjon, "flytt nodene etter et steg bakover".
    @Override
    public void leggTil(int pos, T x) {

        Node peker = start;
        Node ny = new Node(x);

        if (peker == null) {start = ny;}
        else if (pos == 0) {ny.neste = peker; start = ny;}
        else {
            for (int i = 1; i < pos; i++) {peker = peker.neste;}

            ny = peker.neste;
            peker = ny;
        }

        iBruk++;
    }

//Legger til en ny node på slutten(eller starten, hvis den er tom) av lista.
    @Override
    public void leggTil(T x) {
        Node peker = start;
        Node ny = new Node(x);

        if (start != null) {
            while (peker.neste != null) {peker = peker.neste;}

            peker.neste = ny;
        } else {start = ny;}

        iBruk++;
    }

//Overskriver data i gitte posisjon.
    @Override
    public void sett(int pos, T x){
        Node peker = start;

        for (int i = 0; i < pos; i++) {peker = peker.neste;}

        peker.data = x;
    }

//Henter dataen tilordnet gitte posisjon.
    @Override
    public T hent(int pos){
        Node peker = start;
        if (peker == null) {return null;}
        for (int i = 0; i < pos; i++) {peker = peker.neste;}

        return peker.data;
    }

//FJerner node på bestemt posisjon, returnerer data fra noden på gitt posisjon.
    @Override
    public T fjern(int pos) {
        Node peker = start;

        if (peker.neste == null) {start = null; return peker.data;}

        for (int i = 1; i < pos; i++) {peker = peker.neste;}

        Node n = peker.neste;
        peker.neste = n.neste;

        iBruk--;

        return n.data;
    }

//Fjerner første i lista, returnerer dataen noden peker på.
    @Override
    public T fjern(){
        Node peker = start;

        start = start.neste;

        iBruk--;

        return peker.data;
    }
}
