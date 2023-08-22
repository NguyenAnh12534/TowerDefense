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
                System.out.println("Update status " + this.spriteID);

            }
            case OTHER -> {
                System.out.println("Other event");
            }
            default -> {
                System.out.println("No event");
            }
        }
    }

    public void setSpriteSheet(int[] ids) {
        this.spriteIDs = ids;
    }

    public int getSpriteID() {
        return this.spriteID;
    }
}
