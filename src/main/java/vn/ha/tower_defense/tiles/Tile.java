package vn.ha.tower_defense.tiles;

import vn.ha.tower_defense.game.Game;
import vn.ha.tower_defense.observers.Event;
import vn.ha.tower_defense.observers.Observer;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tile implements Serializable, Observer {

    private static final long serialVersionUID = 2146597398744064633L;
    private boolean isCorner = false;
    private List<Tile> layers = new LinkedList<>();
    private int[] spriteIDs = new int[4];
    private int spriteID;
    private int currentIndex = 0;
    private String name;
    private Integer ID;

    public Tile(String name, int ID) {
        this.ID = ID;
        this.spriteID = ID;
        this.name = name;
    }

    public Tile(Tile tile) {
        this.name = tile.name;
        this.ID = tile.ID;
    }


    public String getName() {
        return this.name;
    }

    public boolean getIsCorner() {
        return this.isCorner;
    }

    public void setIsCorner(boolean isCorner) {
        this.isCorner = isCorner;
    }

    public Integer getId() {
        return this.ID;
    }

    public void addLayer(Tile tile) {
        this.layers.add(tile);
    }


    public List<Tile> getLayers() {
        return this.layers;
    }

    @Override
    public void update(Event event) {
        switch (event.getEventType()) {
            case UPDATE -> {
                if (spriteIDs[0] == 0)
                    return;
                this.spriteID = spriteIDs[currentIndex];

                currentIndex++;

                if (currentIndex == spriteIDs.length)
                    currentIndex = 0;
            }
            case OTHER -> {
                System.out.println("Other event");
            }
            default -> {
                System.out.println("No event");
            }
        }
    }

    public void setSpriteID(int spriteID) {
        this.spriteID = spriteID;
    }

    public boolean isRoad() {
        return this.spriteID >= 29 && spriteID<=36;
    }

    public boolean isLayer() {
        return this.spriteID >= 17 && this.spriteID <= 28;
    }

    public void setSpriteSheet(int[] ids) {
        this.spriteIDs = ids;
    }

    public int getSpriteID() {
        return this.spriteID;
    }

    public int[] getSpriteIDs() {
        return this.spriteIDs;
    }
}
