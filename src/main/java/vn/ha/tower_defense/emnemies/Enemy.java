package vn.ha.tower_defense.emnemies;

import vn.ha.tower_defense.observers.Event;
import vn.ha.tower_defense.observers.Observer;
import vn.ha.tower_defense.scenes.PlayScene;
import vn.ha.tower_defense.tiles.Tile;

import java.awt.desktop.AboutEvent;
import java.util.Date;

public class Enemy implements Observer {

    private Position position;
    private int health = 100;

    private int speed = 16;

    private int spriteID;

    private Direction direction = Direction.DOWN;

    public Enemy(Position position, int spriteID) {
        this.position = position;
        this.spriteID = spriteID;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpriteID() {
        return spriteID;
    }

    public void setSpriteID(int spriteID) {
        this.spriteID = spriteID;
    }

    private void moveTo(Position position) {
        this.position = position;
    }

    public Position getForwardPosition(double distance) {
        return new Position(position.getX() + distance, position.getY());
    }

    public Position getBackwardPosition(double distance) {
        return new Position(position.getX() - distance, position.getY());
    }

    public Position getUpPosition(double distance) {
        return new Position(position.getX(), position.getY() - distance);
    }

    public Position getDownPosition(double distance) {
        return new Position(position.getX(), position.getY() + distance);
    }

    public void move(double distance) {
        moveTo(getNextPosition(distance));
    }

    private Position getNextPosition(double distance) {
        Position nextPosition;
        switch (this.direction) {
            case FORWARD -> {
                nextPosition = getForwardPosition(distance);
            }
            case BACKWARD -> {
                nextPosition = getBackwardPosition(distance);
            }
            case UP -> {
                nextPosition = getUpPosition(distance);
            }
            case DOWN -> {
                nextPosition = getDownPosition(distance);
            }
            default -> {
                nextPosition = this.position;
            }
        }

        return nextPosition;
    }

    public void findPath(Tile[][] map) {
//        if()
    }


    public boolean isEnd() {
        Position nextPosition = getNextPosition(this.speed);
        if (nextPosition.getX() < 0 || nextPosition.getY() < 0) {
            return true;
        }

        double x, y;

        double rightEdge = nextPosition.getX() + 32;
        double bottomEdge = nextPosition.getY() + 32;
        switch (this.direction) {
            case FORWARD -> {
                x = (rightEdge);
                y = (nextPosition.getY());
            }
            case DOWN -> {
                x = (nextPosition.getX());
                y = bottomEdge;
            }
            default -> {
                x = (nextPosition.getX());
                y = (nextPosition.getY());
            }
        }

        if (y > PlayScene.map.length * 32 || x > PlayScene.map[0].length * 32)
            return true;
        return false;
    }

    private boolean isOnRoad(Position position) {

        int x = (int) (position.getX()) / 32;
        int y = (int) (position.getY()) / 32;

        return PlayScene.map[y][x].isRoad();
    }



    public void turnLeft() {
        this.direction = this.direction.getLeftDirection();
    }
    public void turnRight() {
        this.direction = this.direction.getRightDirection();
    }

    public void turnAround() {
        this.direction = this.direction.getLeftDirection().getLeftDirection();
    }

    @Override
    public void update(Event event) {
        switch (event.getEventType()) {
            case UPDATE -> {
                if (!this.isEnd())
                    this.move(this.speed);
                else {
                    this.turnAround();
                }

                if(!isOnRoad(this.getPosition())) {

                }
            }
            default -> {
                System.out.println("Enemy do nothing");
            }
        }
    }

    public enum Direction {
        FORWARD(0), BACKWARD(2), UP(3), DOWN(1);

        private int id;
        private static Direction[] directions = {Direction.FORWARD, Direction.DOWN, Direction.BACKWARD, Direction.UP};

        Direction(int id) {

            this.id = id;
        }

        public int getId() {
            return id;
        }

        public Direction getLeftDirection() {
            int nextID = this.id + 1;
            if (nextID >= directions.length)
                nextID = 0;
            return directions[nextID];
        }

        public Direction getRightDirection() {
            int nextID = this.id - 1;
            if (nextID < 0)
                nextID = directions.length;
            return directions[nextID];
        }
    }
}
