package LOGIC;

import LOGIC.blocks.BlockType;
import LOGIC.loopingArray.LoopingArray;

public class Snake {
    public static Snake instance;

    private final LoopingArray<Coordinate> body;
    private int size;
    private Direction direction;

    public Snake() {
        this.body = new LoopingArray<>();
        this.size = 0;
        this.direction = Direction.UP;
        instance = this;
        initiate();
    }

    private void initiate() {
        for (int i = 0; i < 3; i++) {
            body.addToBack(new Coordinate(i, 5));
        }
    }

    public boolean move() {
        Coordinate head = body.get(0);
        Coordinate tail = body.get(size - 1);
        body.remove(size - 1);
        body.addToFront(tail);

        switch (direction) {
            case UP:
                    tail.setX(head.getX());
                    tail.setY(head.getY() + 1);
                break;
            case DOWN:
                    tail.setX(head.getX());
                    tail.setY(head.getY() - 1);
                break;
            case LEFT:
                    tail.setX(head.getX() - 1);
                    tail.setY(head.getY());
                break;
            case RIGHT:
                    tail.setX(head.getX() + 1);
                    tail.setY(head.getY());
                break;
        }

        // todo check

        return false;
    }


    public LoopingArray<Coordinate> getBody() {
        return body;
    }

    public int getSize() {
        return size;
    }

    public Direction getDirection() {
        return direction;
    }



    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
