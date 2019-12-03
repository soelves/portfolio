class Stabel<T> extends Lenkeliste<T> {

//Legger kun på, på slutten av lista.
    public void leggPaa(T x) throws UgyldigListeIndeks{
        leggTil(x);
    }

//Fjerner og returnerer den siste på lista.
    public T taAv() throws UgyldigListeIndeks{
        return fjern(stoerrelse() - 1);
    }
}
