package LOGIC;

import LOGIC.blocks.Block;
import LOGIC.blocks.BlockType;

public class Board {
    public static Board instance;

    private Block[][] board;
    public int width;
    public int height;

    public Board(int width, int height) {
        this.board = new Block[width][height];
        this.width = width;
        this.height = height;
        this.initBoard();
        Board.instance = this;
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

    public Block getBlock(int x, int y) {
        return board[x][y];
    }

    public void setBlock(int x, int y, Block block) {
        board[x][y] = block;
    }
}
