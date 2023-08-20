package vn.ha.tower_defense.scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import vn.ha.tower_defense.managers.TileManager;
import vn.ha.tower_defense.tiles.Tile;

public abstract class  Scene implements Renderable {

    private TileManager tileManager;

    public Scene(TileManager tileManager) {
        this.tileManager = tileManager;
    }

    public abstract void handleMouseClicked(MouseEvent e);

    public abstract void handleMouseMoved(MouseEvent e);

    public abstract void handleMousePressed(MouseEvent e);

    public abstract void handleMouseReleased(MouseEvent e);

    public abstract void hanldeMouseDragged(MouseEvent e);

    public abstract void hanldeKeyTyped(KeyEvent e);

    public  Tile getSelectedTile() {
        System.out.println("This scene don't have selected tile");
        return null;
    }


    public  void setSelectedTile(Tile tile) {
        System.out.println("This scene don't have selected tile");
    }
}
