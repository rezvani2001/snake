package LOGIC.blocks;

import GUI.templates.Grass;
import LOGIC.Coordinate;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block {
    public BlockType blockType;
    public Coordinate coordinate;
    public Node uiElement;

    public Block() {
    }

    public Block(BlockType blockType, Coordinate coordinate) {
        if (blockType == BlockType.WALL) {
            Rectangle rectangle = new Rectangle(30, 30);

            rectangle.setFill(Color.BLACK);
            uiElement = rectangle;
        } else if (blockType == BlockType.GRASS) {
            this.uiElement = new Grass();
        }

    }
}
