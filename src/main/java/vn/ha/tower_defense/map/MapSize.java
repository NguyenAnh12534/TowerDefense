package vn.ha.tower_defense.map;

import java.io.Serializable;

public class MapSize implements Serializable{
    int width;
    int height;

    public MapSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setWidth(int widht) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
