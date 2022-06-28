package LOGIC;

import GUI.Main;
import LOGIC.blocks.Block;
import LOGIC.blocks.BlockType;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class Board {
    public static Board instance;
    public static Apple apple;

    private final Block[][] board;
    public static int width;
    public static int height;

    public Board(int width, int height) {
        this.board = new Block[width][height];
        Board.width = width;
        Board.height = height;
        this.initBoard();
        Board.instance = this;
        newApple();
    }

    public static void newApple() {
        apple = new Apple(new Coordinate(Main.rand.nextInt(width - 2) + 1, Main.rand.nextInt(height - 2) + 1));
    }

    private void initBoard() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i == 0 || i == width - 1 || j == 0 || j == height - 1) {
                    board[i][j] = new Block(BlockType.WALL, new Coordinate(i, j));
                } else {
                    board[i][j] = new Block(BlockType.GRASS, new Coordinate(i, j));
                }
            }
        }
    }

    public GridPane getBoard() {
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #000000");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                gridPane.add(board[i][j].uiElement, i, j);
            }
        }
        return gridPane;
    }

    public static boolean checkSnakeWithBoard(Coordinate coordinate) {
        if (apple.coordinate.getX() == coordinate.getX() && apple.coordinate.getY() == coordinate.getY()) {
            newApple();
            Snake.instance.addToBack();
        }

        return coordinate.getX() <= 0 || coordinate.getX() >= width - 1
                || coordinate.getY() <= 0 || coordinate.getY() >= height - 1;
    }

    public Block getBlock(int x, int y) {
        return board[x][y];
    }

    public void setBlock(int x, int y, Block block) {
        board[x][y] = block;
    }
}
