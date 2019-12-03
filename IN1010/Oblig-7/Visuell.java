import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import java.io.File;
import java.util.Scanner;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class Visuell extends Application implements EventHandler<MouseEvent>{

    Stage vindu;
    GridPane rutenettGP;
    Labyrint l = null;

    @Override
    public void handle(MouseEvent event){
        int x = l.kolonne;
        int y = l.rad;
        System.out.println(x);
        System.out.println(y);

        Liste<String> utveier = l.finnUtveiFra(y,x);
        boolean[][] sant = losningStringTilTabell(utveier.hent(0),x,y);
        System.out.println(sant);
    }

    @Override
    public void start(Stage s){
        vindu = s;
        vindu.setTitle("Soelve's Maze Solver");
        rutenettGP = new GridPane();


        File fil = new FileChooser().showOpenDialog(vindu);
        try{
            Scanner scanner = new Scanner(fil);
            l = Labyrint.lesFraFil(fil);

        }catch(Exception e) {}

        for(int i = 0; i < l.rad; i++){
            for(int j = 0; j < l.kolonne; j++){
                if(l.rutenett[i][j].toString() == "#"){
                    Rectangle r = new Rectangle(10,10,Color.BLACK);
                    r.setX(j);
                    r.setY(i);
                    rutenettGP.add(r,i,j);

                }
                if(l.rutenett[i][j].toString() == "."){
                    Rectangle r = new Rectangle(10,10,Color.YELLOW);
                    r.setX(j);
                    r.setY(i);
                    rutenettGP.add(r,i,j);
                    final int utveiX = i;
                    final int utveiY = j;

                    r.setOnMouseClicked(new EventHandler<MouseEvent>(){
                        @Override
                        public void handle(MouseEvent event){
                            int x = l.kolonne;
                            int y = l.rad;

                            Liste<String> utveier = l.finnUtveiFra(utveiX,utveiY);
                            boolean[][] sant = losningStringTilTabell(utveier.hent(0),x,y);

                            for(int a = 0; a < y; a++){
                                for(int b = 0; b < x; b++){
                                    if(sant[a][b] == true){
                                        Rectangle r = new Rectangle(10,10,Color.GREEN);
                                        r.setX(a);
                                        r.setY(b);
                                        rutenettGP.add(r,a,b);
                                    }
                                }
                            }

                        }
                    });
                }
            }
        }

        Scene scene = new Scene(rutenettGP);
        vindu.setScene(scene);
        vindu.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

        /**
     * Konverterer losning-String fra oblig 5 til en boolean[][]-representasjon
     * av losningstien.
     * @param losningString String-representasjon av utveien
     * @param bredde        bredde til labyrinten
     * @param hoyde         hoyde til labyrinten
     * @return              2D-representasjon av rutene der true indikerer at
     *                      ruten er en del av utveien.
     */
    static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
        boolean[][] losning = new boolean[hoyde][bredde];
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
        java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
        while (m.find()) {
            int x = Integer.parseInt(m.group(1));
            int y = Integer.parseInt(m.group(2));
            losning[y][x] = true;
        }
        return losning;
    }
}
