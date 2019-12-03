import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class VisuellRute extends StackPane{
    StackPane rute;
    public VisuellRute(){
        rute = new StackPane(new Rectangle(40,40,Color.RED));
    }

    public StackPane hentRute(){
        return rute;
    }
}
