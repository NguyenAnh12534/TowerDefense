package vn.ha.tower_defense.game;

public class Position {
    private int x, y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setx(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public int getX() {
        return this.x;
    }
}
