import java.io.*;
import java.lang.*;
import java.util.*;
public class BSTree implements BSTOper{

    static Node rot;
    static int nodeTeller = 0;

    private class Node {
        Node left, right, parent;

        Integer value;

        boolean root = false;

        @Override
        public String toString(){
            String s = Integer.toString(value);
            return s;
        }

        Node(){
            if(nodeTeller == 0){
                rot = this;
                nodeTeller++;
            }else{
                nodeTeller++;
            }
        }

        Node(int v){
            value = v;
            if(nodeTeller == 0){
                rot = this;
                nodeTeller++;
            }else{
                nodeTeller++;
            }
        }
    }




    //Tomt tre
    BSTree(){
        Node n = rot;
    }

    //Tre fra liste
    BSTree(Integer[] a){
        int lengde = a.length;

        Node peker = rot;

        for(int i = 0; i < lengde; i++){
            Node n = new Node(a[i]);

            if(i == 0){
                rot = n;
                peker = n;
                n.root = true;

            }else{
                steg(peker, n);
            }
        }
    }

    //Hjelpemetode, går nedover i treet, bytter pekere for noder som skal
    //byttes/flyttes.
    private void steg(Node peker, Node n){
        Integer pekerVerdi = peker.value;
        Integer nVerdi = n.value;

        if(pekerVerdi.compareTo(nVerdi) < 1){
            if(peker.right == null){
                peker.right = n;
                n.parent = peker;
            }else{
                steg(peker.right, n);
            }
        }

        if(pekerVerdi.compareTo(nVerdi) > 0){
            if(peker.left == null){
                peker.left = n;
                n.parent = peker;
            }else{
                steg(peker.left, n);
            }
        }
    }

    private Node findParent (Node n) {
        return n.parent;
    }
    private Node findGrandparent (Node n) {
        if(n.parent == null){
            return null;
        }

        return n.parent.parent;
    }
    //Fikset til å returnere null hvis den ikke finner en node med gitt verdi.
    private Node find(int value){
        Node n = rot;
        boolean stopp = false;
        if(n.value == value){
            return n;
        }
        while(stopp == false){
            if(n.value == value){
                stopp = true;
            }else{
                if(n.value > value){
                    if(n.left == null){return null;}
                    n = n.left;
                }
                if(n.value < value){
                    if(n.right == null){return null;}
                    n = n.right;
                }
            }
        }
        return n;
    }

    //metoder fra BSTOper

    @Override
    public void add( int value ){
        Node n = new Node(value);
        if(nodeTeller == 1){

        }else{
            steg(rot, n);
        }

    }

    //Hjelpemetode til remove.
    private void fjern(Node n, int fjern){
        Integer nVerdi = n.value;

        if(nVerdi == fjern){

        }

        if(nVerdi > fjern){
            n = n.left;
            fjern(n, fjern);
        }
        if(nVerdi < fjern){
            n = n.right;
            fjern(n, fjern);
        }



    }

    @Override
    public boolean remove( int value ){
        Node n = rot;
        Node m = rot;
        int bytte = 0;


        if(rot.value == null){
            return false;
        }else{
            fjern(n, value);
            bytte = findNearestSmallerThan(value);
            m = find(bytte);

            n.value = m.value;
            m.value = null;

            return true;
        }
    }

    @Override
    public int size(){
        return nodeTeller;
    }

    @Override
    public boolean existsInTree( int value ){
        Node n = find(value);

        if(n.value == value){
            return true;
        }else{
            return false;
        }
    }

    //Fikset, ser nå først om den har en mindre under seg, hvis den har det,
    //så går den til høyre og venstre til den finner den nærmeste.
    //Hvis den ikke har noen under seg, går den oppover og ser om den har
    //noen mindre enn seg selv over seg. Hvis den har det, blir det
    //returnert. Hvis den treffer rota, og rota er større enn det vi skal
    //finne noe mindre enn, da er det vi leter etter ikke i treet, fordi da
    //er utgangspunktet vårt det minste i treet.
    @Override
    public int findNearestSmallerThan( int value ){

        Node ns = find(value);
        boolean stopp = false;

        if(ns == null){
            return -1;
        }
        int verdi = ns.value;

        if(ns.left != null){
            ns = ns.left;
            while(stopp == false){

                if(value > ns.value){
                    if(ns.right == null){
                        stopp = true;
                    }else{
                        ns = ns.right;
                    }
                }else if(value < ns.value){
                    if(ns.left == null){
                        stopp = true;
                    }else{
                        ns = ns.left;
                    }
                }
            }
            return ns.value;
        }else{
            while(stopp == false){
                if(ns.parent.value == rot.value && verdi < rot.value){
                    return -1;
                }else if(ns.parent.value < ns.value){
                    return ns.parent.value;
                }else{
                    ns = ns.parent;
                }
            }
        }
        return -1;
    }

    @Override
    public void addAll( int[] integers ){
        int lengde = integers.length;

        Node peker = rot;

        for(int i = 0; i < lengde; i++){
            Node n = new Node(integers[i]);

            if(nodeTeller == 0){
                rot = n;
                peker = n;
                n.root = true;

            }else{
                steg(peker, n);
            }
        }
    }

    //Hjelpemetode, går ned til den minste noden, og legger til noder etter
    //denne noden er funnet.
    private void inorderTraversal(ArrayList l, Node n){

        if(n.left == null){
            l.add(n.value);
            if(n.right != null){
                inorderTraversal(l, n.right);
            }
        }else{
            inorderTraversal(l, n.left);
            l.add(n.value);
            if(n.right != null){
                inorderTraversal(l, n.right);

            }
        }
    }

    //Ferdig
    @Override
    public int[] sortedArray(){
        ArrayList<Integer> liste = new ArrayList();
        Node n = rot;

        inorderTraversal(liste, n);

        int[] sortert = new int[liste.size()];
        for(int i = 0;i < sortert.length; i++){
            sortert[i] = liste.get(i).intValue();
        }

        return sortert;
    }

    //Hjelpemetode, går gjennom lista, legger kun til innenfor rekkevidden.
    //Ser at det ikke er en optimal løsning, men var det beste jeg fikk til
    //å fungere.
    private void finnInnenfor(ArrayList l, Node n, int low, int high){

        if(n.left == null){
            if(n.value > low && n.value < high){
                l.add(n.value);
            }
            if(n.right != null){
                finnInnenfor(l, n.right, low, high);
            }
        }else{
            finnInnenfor(l, n.left, low, high);
            if(n.value > low && n.value < high){
                l.add(n.value);
            }
            if(n.right != null){
                finnInnenfor(l, n.right, low, high);

            }
        }


    }

    @Override
    public int[] findInRange (int low, int high){
        ArrayList<Integer> liste = new ArrayList();
        Node n = rot;

        finnInnenfor(liste, n, low, high);

        int[] innenfor = new int[liste.size()];
        for(int i = 0;i < innenfor.length; i++){
            innenfor[i] = liste.get(i).intValue();
        }

        return innenfor;
    }

    //Hjelpemetode, beveger seg i en retning nedover
    private void gaaNed(String retning, Node n){

        if(retning == "left"){
            if(n.left == null){
            }else{
                n = n.left;
                gaaNed(retning, n);
            }
        }
        if(retning == "right"){
            if(n.right == null){

            }else{
                n = n.right;
                gaaNed(retning, n);
            }
        }
    }

    //Ikke feridg.
/*    @Override
    public String toString(){
        String s = "";

        s = s + Integer.toString(rot.value) + "\n";
        s = s + Integer.toString(rot.left.value) + " "
        + Integer.toString(rot.right.value) + "\n";


        return s;
    }*/
}
