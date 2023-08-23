package vn.ha.tower_defense.map;

import vn.ha.tower_defense.tiles.Tile;

public class LevelBuilder {

    private static LevelBuilder levelBuilder;

    private static Tile[][] map = new Tile[20][30];


    public static LevelBuilder getLevelBuilder() {
        if (levelBuilder == null) {
            levelBuilder = new LevelBuilder();
        }

        return levelBuilder;
    }

    public Tile[][] getMap() {

        if (map[0][0] == null) {
            buildMap();
        }

        return LevelBuilder.map;
    }

    private void buildMap() {
        
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                map[y][x] = new Tile( null, 1);
            }
        }
    }

    public void setMap(Tile[][] map) {
        LevelBuilder.map = map;
    }
}
