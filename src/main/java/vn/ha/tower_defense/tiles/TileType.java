package vn.ha.tower_defense.tiles;

public enum TileType {
    GRASS(0), WATER(1), ROAD(2), HORIZONTAL_ROAD(3), VERTICAL_ROAD(4), BL_CORNER_ROAD(5), TR_CORNER_ROAD(6);

    public int id;

    private TileType(int id){
        this.id = id;
    }
}
