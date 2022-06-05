package LOGIC;

import LOGIC.blocks.BlockType;
import LOGIC.loopingArray.LoopingArray;

public class Snake {
    public static Snake instance;

    private final LoopingArray<Coordinate> body;
    private Direction direction;
    private Direction nextDirection;

    public Snake() {
        this.body = new LoopingArray<>();
        this.direction = Direction.RIGHT;
        this.nextDirection = Direction.RIGHT;

        instance = this;
        initiate();
    }

    private void initiate() {
        for (int i = 0; i < 3; i++) {
            body.addToBack(new Coordinate(i + 1, 14));
        }
    }

    public boolean move() {
        Coordinate head = body.get(0);
        Coordinate tail = body.get(body.size() - 1);
        body.remove(body.size() - 1);
        body.addToFront(tail);

        this.direction = this.nextDirection;

        switch (direction) {
            case UP:
                    tail.setX(head.getX());
                    tail.setY(head.getY() - 1);
                break;
            case DOWN:
                    tail.setX(head.getX());
                    tail.setY(head.getY() + 1);
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
        return body.size();
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        if (direction == Direction.UP && this.direction != Direction.DOWN) {
            this.nextDirection = direction;
        } else if (direction == Direction.DOWN && this.direction != Direction.UP) {
            this.nextDirection = direction;
        } else if (direction == Direction.LEFT && this.direction != Direction.RIGHT) {
            this.nextDirection = direction;
        } else if (direction == Direction.RIGHT && this.direction != Direction.LEFT) {
            this.nextDirection = direction;
        }
    }
}
