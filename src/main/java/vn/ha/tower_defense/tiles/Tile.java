package vn.ha.tower_defense.tiles;

import vn.ha.tower_defense.observers.Event;
import vn.ha.tower_defense.observers.Observer;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class Tile implements Serializable, Observer {

    private static final long serialVersionUID = 2146597398744064633L;
    
    private Queue<Tile> layers = new LinkedList<>();
    private int[] ids = new int[4];
    private  int currentIndex = 0;
    private String name;
    private Integer ID;

    public Tile(String name, int ID) {
        this.ID = ID;
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


    public Queue<Tile> getLayers() {
        return this.layers;
    }

    @Override
    public void update(Event event) {
        switch (event.getEventType()) {
            case UPDATE -> {
                if(ids[0] == 0)
                    return;
                this.ID = ids[currentIndex];
                currentIndex++;
                if(currentIndex == ids.length)
                    currentIndex = 0;
                System.out.println("Update status "  + this.ID);

            }
            case OTHER -> {
                System.out.println("Other event");
            }
            default -> {
                System.out.println("No event");
            }
        }
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }
}
