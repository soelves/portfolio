class SortertLenkeliste<T extends Comparable<T>> extends Lenkeliste<T> {

//Legger noder i stigende rekkefølge ut ifra data. Bruker Lenkeliste til hjelp.
    @Override
    public void leggTil(T x){
        Node peker = start;
        T data = x;
        int j = 1;

        if (peker == null) {super.leggTil(data);}
        else if (data.compareTo(hent(0)) < 0) {super.leggTil(0, data);}
        else if (data.compareTo(hent(stoerrelse() -1)) > 0) {
            super.leggTil(data);
        }
        else {
            while (data.compareTo(hent(j)) > 0) {peker = peker.neste; j++;}
            if (peker.neste == null) {
                j++;
                super.leggTil(j, data);
            }
            else {super.leggTil(j, data);}
        }
    }

//Fjerner og returnerer det siste, og da største elementet.
    @Override
    public T fjern(){
        return fjern(stoerrelse() - 1);
    }

    @Override
    public void sett(int pos, T x) throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggTil(int pos, T x) throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }
}
