package vn.ha.tower_defense.managers;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.ha.tower_defense.helpers.SpriteModifier;
import vn.ha.tower_defense.tiles.Tile;

public class TileManager {

    Tile GRASS, HORIZONTAL_ROAD, VERTICAL_ROAD, BL_CORNER_ROAD, TR_CORNER_ROAD, WATER, CHARACTER;

    private BufferedImage atlas;

    private List<Tile> tileList = new ArrayList();

    private Map<String, Tile> tileMap = new HashMap<>();

    public void loadAtlat(BufferedImage atlas) {
        this.atlas = atlas;
        addTiles();
    }

    private void addTiles() {

        int id = 0;
        for(int i = 0; i<10; i++) {
            for(int j=0; j< 4; j++) {
                double angle = j * 90;
                BufferedImage sprite = SpriteModifier.rotate(atlas.getSubimage(i * 32, 0, 32, 32), angle);
                Tile tile = new Tile(sprite, "", tileList.size() + 1);
                tileList.add(tile);
            }

        }  
    }

    public void addTile(Tile tile) {
    }

    private Tile tileAt(int x, int y) {
        return new Tile(atlas.getSubimage(x * 32, y * 32, 32, 32));
    }

    private Tile tileAt(int x, int y, double angle) {
        return new Tile(SpriteModifier.rotate(atlas.getSubimage(x * 32, y * 32, 32, 32), angle));
    }

    public Tile getTile(int index) {
        return this.tileList.get(index - 1);
    }

    public Tile getTile(int x, int y) {
        return new Tile(atlas.getSubimage(x * 32, y * 32, 32, 32));
    }

    public List<Tile> getTiles() {
        return this.tileList;
    }

}
