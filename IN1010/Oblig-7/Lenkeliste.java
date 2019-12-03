import java.util.*;
class Lenkeliste<T> implements Liste<T> {
//Klassen Node som blir brukt i Lenkelista.
    class Node {
        Node neste = null;
        T data;

        Node(T x) {data = x;}
    }

    public Iterator<T> iterator() {return new LenkelisteIterator();}

    private class LenkelisteIterator implements Iterator <T> {
        private int indeks = 0;
        private Node peker = start;
        private T retur;

        @Override
        public boolean hasNext(){
            return peker != null;
        }

        @Override
        public T next(){
            retur = peker.data;
            peker = peker.neste;
            return retur;
        }

    }

//Start-node, peker på den første noden som inneholder data og neste/forrige.
    protected Node start = null;

//Hjelp til stoerrelse
    protected int iBruk = 0;

//Metode som finner størrelsen på Lenkelista ved hjelp av en while-løkke.
    @Override
    public int stoerrelse(){
        Node peker = start;
        int n = 0;

        while (peker != null) {n++; peker = peker.neste;}

        iBruk = n;

        return iBruk;
    }

//Legger til en node i en posisjon, "flytt nodene etter ett steg bakover".
    @Override
    public void leggTil(int pos, T x) throws UgyldigListeIndeks{
        if (pos > stoerrelse()) {throw new UgyldigListeIndeks(pos);}
        if (pos < 0) {throw new UgyldigListeIndeks(pos);}

        Node peker = start;
        Node ny = new Node(x);

        if (peker == null) {start = ny;}
        else if (pos == 0) {ny.neste = peker; start = ny;}
        else {
            for (int i = 1; i < pos; i++) {peker = peker.neste;}
            ny.neste = peker.neste;
            peker.neste = ny;
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
    public void sett(int pos, T x) throws UgyldigListeIndeks {
        if (pos >= stoerrelse()) {throw new UgyldigListeIndeks(pos);}
        if (pos < 0) {throw new UgyldigListeIndeks(pos);}

        Node peker = start;

        for (int i = 0; i < pos; i++) {peker = peker.neste;}

        peker.data = x;
    }

//Henter dataen tilordnet gitte posisjon.
    @Override
    public T hent(int pos) throws UgyldigListeIndeks {
        if (stoerrelse() == 0) {throw new UgyldigListeIndeks(-1);}
        if (pos >= stoerrelse()) {throw new UgyldigListeIndeks(pos);}
        if (pos < 0) {throw new UgyldigListeIndeks(pos);}

        Node peker = start;
        if (peker == null) {return null;}
        for (int i = 0; i < pos; i++) {peker = peker.neste;}

        return peker.data;
    }

//FJerner node på bestemt posisjon, returnerer data fra noden på gitt posisjon.
    @Override
    public T fjern(int pos) throws UgyldigListeIndeks {
        if (stoerrelse() == 0) {throw new UgyldigListeIndeks(-1);}
        if (pos >= stoerrelse()) {throw new UgyldigListeIndeks(pos);}
        if (pos < 0) {throw new UgyldigListeIndeks(pos);}

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
    public T fjern() throws UgyldigListeIndeks {
        if (stoerrelse() == 0) {throw new UgyldigListeIndeks(-1);}

        Node peker = start;

        start = start.neste;

        iBruk--;

        return peker.data;
    }
}
