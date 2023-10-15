package vn.ha.tower_defense.map;

import vn.ha.tower_defense.tiles.Tile;

public class LevelBuilder {

    private static LevelBuilder levelBuilder;

    private static TileMap map = new TileMap();


    public static LevelBuilder getLevelBuilder() {
        if (levelBuilder == null) {
            levelBuilder = new LevelBuilder();
        }

        return levelBuilder;
    }

    public TileMap getMap() {
        return LevelBuilder.map;
    }
}
