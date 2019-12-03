public class TilTekst{
    public String tekst(int[] l){
        String s = "";
        for(int i = 0; i < l.length; i++){
            s = s + Integer.toString(l[i]) + " ";
        }
        return s;
    }
}
