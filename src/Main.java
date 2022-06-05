import GUI.Grass;
import LOGIC.Board;
import LOGIC.Direction;
import LOGIC.Snake;
import LOGIC.blocks.Block;
import LOGIC.blocks.BlockType;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.event.MouseEvent;
import java.util.Random;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    static FlowPane pane;
    static Snake snake;

    @Override
    public void start(javafx.stage.Stage primaryStage) {
        primaryStage.setTitle("Snake");
        init();

        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);

        pane.getChildren().add(gridPane);

        Thread thread = new Thread(() -> {
            Platform.runLater(() -> duplicateBoard(gridPane));
            try {
                Thread.sleep(1000);


                while (true) {
                    snake.move();
                    Platform.runLater(() -> {
                        duplicateBoard(gridPane);
//                        makeSnake(snake, gridPane);
                    });

                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        Scene scene = new Scene(pane, Board.instance.width * 30, Board.instance.height * 30);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, event ->

        {
            if (event.getCode().equals(KeyCode.W) || event.getCode().equals(KeyCode.UP)) {
                snake.setDirection(Direction.UP);
            } else if (event.getCode().equals(KeyCode.S) || event.getCode().equals(KeyCode.DOWN)) {
                snake.setDirection(Direction.DOWN);
            } else if (event.getCode().equals(KeyCode.A) || event.getCode().equals(KeyCode.LEFT)) {
                snake.setDirection(Direction.LEFT);
            } else if (event.getCode().equals(KeyCode.D) || event.getCode().equals(KeyCode.RIGHT)) {
                snake.setDirection(Direction.RIGHT);
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void init() {
        Grass.rand = new Random();
        new Board(30, 30);
        pane = new FlowPane();
        snake = new Snake();
    }

    public static void duplicateBoard(GridPane gridPane) {
        gridPane.getChildren().clear();

        gridPane.getChildren().addAll(Board.instance.getBoard().getChildren());
    }

    public static void makeSnake(Snake snake, GridPane gridPane) {
        for (int i = 0; i < snake.getBody().size(); i++) {
            gridPane.add(new Rectangle(30, 30, Color.GRAY),
                    snake.getBody().get(i).getX(), snake.getBody().get(i).getY());
        }
    }
}
