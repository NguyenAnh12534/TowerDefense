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
    private int spriteID;

    private Direction direction = Direction.FORWARD;

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

    public void findPath(Tile[][] map, double distance) {

        Position originalPosition = this.position;

        Position nextPosition = getNextPosition(distance);

        Tile nextTile = map[(int)nextPosition.getX() / 32][(int)nextPosition.getY() / 32];
        int x, y;
        try {
            while(!nextTile.isRoad()) {
                this.direction = this.direction.getNextDirection();
                this.position = getNextPosition(distance);
                x = ((int)this.position.getX() > 0 ? (int)this.position.getX() : 0) / 32;
                y = ((int)this.position.getY() > 0 ? (int)this.position.getY() : 0) / 32;
                nextTile = map[y][x];
            }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(this.position.getX() + " " + this.position.getY());
            ex.printStackTrace();
        }

        this.position = originalPosition;
    }

    @Override
    public void update(Event event) {
        switch (event.getEventType()) {
            case UPDATE -> {
                this.findPath(PlayScene.map, 32);
                this.move(10);
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

        public Direction getNextDirection() {
            int nextID = this.id + 1;
            if(nextID > 3)
                nextID = 0;
            return directions[nextID];
        }
    }
}
