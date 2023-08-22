package vn.ha.tower_defense.helpers;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;

import javax.imageio.ImageIO;

import vn.ha.tower_defense.managers.TileManager;
import vn.ha.tower_defense.tiles.Tile;

public class SpriteModifier {

    public static BufferedImage buildSprite(TileManager tileManager, Tile tile) {
        BufferedImage baseSprite = tileManager.getSprite(tile);

        if (tile.getLayers().isEmpty())
            return baseSprite;

        int width = baseSprite.getWidth();
        int height = baseSprite.getHeight();

        BufferedImage newSprite = new BufferedImage(width, height, baseSprite.getType());

        Graphics2D drawer = newSprite.createGraphics();
        drawer.drawImage(baseSprite, 0, 0, null);
        for (Tile layerTile : tile.getLayers()) {
            BufferedImage layerSprite = tileManager.getSprite(layerTile);
            drawer.drawImage(layerSprite, 0, 0, null);
        }

        drawer.dispose();

        return newSprite;
    }

    public static BufferedImage rotate(BufferedImage bimg, Double angle) {

        double sin = Math.abs(Math.sin(Math.toRadians(angle))),
                cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int w = bimg.getWidth();
        int h = bimg.getHeight();
        int neww = (int) Math.floor(w * cos + h * sin),
                newh = (int) Math.floor(h * cos + w * sin);
        BufferedImage rotated = new BufferedImage(neww, newh, bimg.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.translate((neww - w) / 2, (newh - h) / 2);
        graphic.rotate(Math.toRadians(angle), w / 2, h / 2);
        graphic.drawRenderedImage(bimg, null);
        graphic.dispose();
        return rotated;
    }

    public static boolean compareImage(File fileA, File fileB) {
        try {
            // take buffer data from botm image files //
            BufferedImage biA = ImageIO.read(fileA);
            DataBuffer dbA = biA.getData().getDataBuffer();
            int sizeA = dbA.getSize();
            BufferedImage biB = ImageIO.read(fileB);
            DataBuffer dbB = biB.getData().getDataBuffer();
            int sizeB = dbB.getSize();
            // compare data-buffer objects //
            if (sizeA == sizeB) {
                for (int i = 0; i < sizeA; i++) {
                    if (dbA.getElem(i) != dbB.getElem(i)) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Failed to compare image files ...");
            return false;
        }
    }
}
