package vn.ha.tower_defense.map;

import vn.ha.tower_defense.helpers.FileHelper;
import vn.ha.tower_defense.tiles.Tile;

import java.io.File;

public class TileMap {
    Tile[][] map = new Tile[20][30];

    public TileMap() {
        this.buildMap();
    }

    public Tile[][] getMap() {
        return map;
    }

    private void buildMap() {
        File mapFile = new File("map/map.bin");
        if (mapFile.exists()) {
            try {
                this.map = FileHelper.getMapFromFile(mapFile);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            buildNewMap();
        }
    }

    public void buildNewMap() {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                map[y][x] = new Tile(null, 1);
            }
        }
    }

    public void setMap(Tile[][] map) {
        this.map = map;
    }

    public void reloadMap() {
        this.buildMap();
    }

    public int getWidth() {
        if (this.getHeight() <= 0)
            return 0;

        return this.map[0].length;
    }

    public int getHeight() {
        return this.map.length;
    }

    public Tile getTileAt(int verticalIndex, int horizontalIndex) {
        return this.map[verticalIndex][horizontalIndex];
    }
}
