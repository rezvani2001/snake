package LOGIC;

import LOGIC.loopingArray.LoopingArray;

public class Snake {
    public static Snake instance;

    private final LoopingArray<Coordinate> body;
    private Direction direction;
    private Direction nextDirection;
    private boolean isAlive;

    public Snake() {
        this.body = new LoopingArray<>();
        this.direction = Direction.RIGHT;
        this.nextDirection = Direction.RIGHT;
        this.isAlive = true;
        instance = this;
        initiate();
    }

    private void initiate() {
        for (int i = 2; i > -1; i--) {
            body.addToBack(new Coordinate(i + 1, 14));
        }
    }

    public void move() {
        if (isAlive) {
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


            check();
        }
    }

    public void addToBack() {
        body.addToBack(new Coordinate(body.get(body.size() - 1).getX() + (body.get(body.size() - 1).getX() - body.get(body.size() - 2).getX()),
                body.get(body.size() - 1).getY() + (body.get(body.size() - 1).getY() - body.get(body.size() - 2).getY())));
    }

    public void check() {
        if (Board.checkSnakeWithBoard(body.get(0)) || checkCrash()) {
            System.out.println("Game over");
            isAlive = false;
        }
    }

    public boolean checkCrash() {
        Coordinate coordinate = body.get(body.size() - 1);

        for (int i = 0; i < body.size() - 1; i++) {
            Coordinate bod = body.get(i);
            if (coordinate.getX() == bod.getX() && coordinate.getY() == bod.getY()) {
                return true;
            }
        }

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
