package vn.ha.tower_defense.tiles;

import vn.ha.tower_defense.observers.Event;
import vn.ha.tower_defense.observers.Observer;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class Tile implements Serializable, Observer {

    private static final long serialVersionUID = 2146597398744064633L;
    
    private transient BufferedImage sprite;

    private Queue<Tile> layers = new LinkedList<>();

    private String name;
    private Integer ID;

    public Tile(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public Tile(BufferedImage sprite, String name, int ID) {
        this.sprite = sprite;
        this.ID = ID;
        this.name = name;
    }

    public Tile(Tile tile) {
        this.name = tile.name;
        this.ID = tile.ID;
        this.sprite = tile.getSprite();
    }

    public BufferedImage getSprite() {
        return this.sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
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

    public Tile deQueue() {
        return this.layers.poll();
    }

    public Queue<Tile> getLayers() {
        return this.layers;
    }

    @Override
    public void update(Event event) {
        switch (event.getEventType()) {
            case UPDATE -> {
                System.out.println("Update status");
            }
            case OTHER -> {
                System.out.println("Other event");
            }
            default -> {
                System.out.println("No event");
            }
        }
    }
}
