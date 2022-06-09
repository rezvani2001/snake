package GUI;

import LOGIC.Apple;
import LOGIC.Board;
import LOGIC.Direction;
import LOGIC.Snake;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Main extends Application {
    static StackPane pane;
    static Snake snake;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(javafx.stage.Stage primaryStage) {
        primaryStage.setTitle("Snake");
        init();

        AnchorPane anchorPane = new AnchorPane();

        pane.getChildren().addAll(Board.instance.getBoard(), anchorPane);

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(750);


                while (true) {
                    snake.move();
                    Platform.runLater(() -> {
                        anchorPane.getChildren().clear();
                        showApple(anchorPane);
                        makeSnake(snake, anchorPane);
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
        Board.rand = new Random();
        new Board(30, 30);
        pane = new StackPane();
        snake = new Snake();
    }

    public static void showApple(AnchorPane anchorPane) {
        anchorPane.getChildren().add(Apple.uiElement);
        AnchorPane.setLeftAnchor(Apple.uiElement, Board.apple.coordinate.getX() * 30.0);
        AnchorPane.setTopAnchor(Apple.uiElement, Board.apple.coordinate.getY() * 30.0);
    }

    public static void makeSnake(Snake snake, AnchorPane anchorPane) {
        for (int i = 0; i < snake.getBody().size(); i++) {
            Circle circle = new Circle(15, Color.GHOSTWHITE.brighter());
            anchorPane.getChildren().add(circle);
            AnchorPane.setLeftAnchor(circle, snake.getBody().get(i).getX() * 30.0);
            AnchorPane.setTopAnchor(circle, snake.getBody().get(i).getY() * 30.0);
        }
    }
}
