package vn.ha.tower_defense.ui.buttons;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import vn.ha.tower_defense.managers.TileManager;
import vn.ha.tower_defense.tiles.Tile;

public class TileButton extends GameButton {
    private Tile tile;

    public TileButton(int x, int y, int width, int height, Tile tile) {
        super(x, y, width, height);
        this.tile = tile;
    }


    @Override
    public void draw(Graphics g) {
        drawTile(g);
        super.drawAffect(g);
    }

    private void drawTile(Graphics g) {
        g.drawImage(TileManager.getSpriteBadWay(tile), super.getX(), super.getY(), null);
    }

    public Tile getTile() {
        return this.tile;
    }
}
