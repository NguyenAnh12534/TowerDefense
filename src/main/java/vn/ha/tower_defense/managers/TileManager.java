package vn.ha.tower_defense.managers;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.ha.tower_defense.helpers.SpriteModifier;
import vn.ha.tower_defense.tiles.Tile;

import javax.imageio.ImageIO;

public class TileManager {

    public BufferedImage atlas;

    private List<Tile> tileList = new ArrayList();

    private static List<BufferedImage> badSprites = new ArrayList<>();
    private List<BufferedImage> sprites= new ArrayList<>();

    public TileManager() {
        loadAtlat(importImg());
    }

    public void loadAtlat(BufferedImage atlas) {
        this.atlas = atlas;
        addTiles();
    }
    private BufferedImage importImg() {
        try {
            InputStream inputStream = new FileInputStream("src/main/resources/images/spriteatlas.png");
            return ImageIO.read(inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private void addTiles() {
        for(int i = 0; i<10; i++) {
            for(int j=0; j< 4; j++) {
                double angle = j * 90;
                BufferedImage sprite = SpriteModifier.rotate(atlas.getSubimage(i * 32, 0, 32, 32), angle);
                sprites.add(sprite);
                badSprites.add(sprite);
                Tile tile = new Tile("", tileList.size() + 1);
                tileList.add(tile);
            }
        }

        markCorner();
        addAnimationForWaterSprite();
    }

    private void markCorner() {
        getTile(29).setIsCorner(true);
        getTile(30).setIsCorner(true);
        getTile(31).setIsCorner(true);
        getTile(32).setIsCorner(true);
    }

    private void addAnimationForWaterSprite() {
        int[] ids = {getTile(1).getId(), 5,9,13};
        int[] ids1 = {2,6,10,14};
        int[] ids2 = {3,7,11,15};
        int[] ids3 = {4,8,12,16};
        getTile(1).setSpriteSheet(ids);
        getTile(2).setSpriteSheet(ids1);
        getTile(3).setSpriteSheet(ids2);
        getTile(4).setSpriteSheet(ids3);
    }

    public void addTile(Tile tile) {
    }

    public Tile getTile(int index) {
        return this.tileList.get(index - 1);
    }

    public List<Tile> getTiles() {
        return this.tileList;
    }

    public BufferedImage getSprite(Tile tile) {
        return this.sprites.get(tile.getSpriteID()-1);
    }

    public static BufferedImage getSpriteBadWay(Tile tile) {
        return badSprites.get(tile.getSpriteID()-1);
    }

}
