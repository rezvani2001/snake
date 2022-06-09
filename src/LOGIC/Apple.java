package LOGIC;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Apple {
    public Coordinate coordinate;
    public static Node uiElement;

    public Apple(Coordinate coordinate) {
        this.coordinate = coordinate;
        if (uiElement == null) {
            uiElement = new Circle(10, Color.RED);
        }
    }
}
