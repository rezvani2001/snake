package GUI;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Grass extends GridPane {
    public static Random rand;

    public Grass() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int random = rand.nextInt(10);
                this.add(new Rectangle(3, 3, random < 5 ? Color.GREEN.brighter() :
                        random > 8 ? Color.GREEN.darker() : Color.GREEN), i, j);
            }
        }
    }
}
