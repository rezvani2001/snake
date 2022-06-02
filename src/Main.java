import GUI.Grass;
import LOGIC.Board;
import LOGIC.Snake;
import LOGIC.blocks.Block;
import LOGIC.blocks.BlockType;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(javafx.stage.Stage primaryStage) {
        double time = System.currentTimeMillis();
        new Board(30, 30);
        Grass.rand = new Random();

        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);


        for (int i = 0; i < Board.instance.width; i++) {
            for (int j = 0; j < Board.instance.height; j++) {
                Block block = Board.instance.getBlock(i, j);

                if (block.blockType == BlockType.WALL) {
                    Rectangle rectangle = new Rectangle(30, 30);

                    rectangle.setFill(Color.BLACK);
                    gridPane.add(rectangle, i, j);

                } else if (block.blockType == BlockType.GRASS) {
                    gridPane.add(new Grass(), i, j);
                }

            }
        }

        Snake snake = new Snake();

        for (int i = 0; i < snake.getBody().size(); i++) {
            gridPane.add(new Rectangle(30, 30, Color.GRAY), snake.getBody().get(i).getX(), snake.getBody().get(i).getY());
        }

        System.out.println(System.currentTimeMillis() - time);

        primaryStage.setScene(new Scene(gridPane, Board.instance.width * 30, Board.instance.height * 30));
        primaryStage.show();
    }
}
