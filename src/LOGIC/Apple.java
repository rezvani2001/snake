package LOGIC;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Apple {
    public Coordinate coordinate;
    public Node uiElement;

    public Apple(Coordinate coordinate) {
        this.coordinate = coordinate;
        this.uiElement = new Circle(15, Color.RED);
    }
}
