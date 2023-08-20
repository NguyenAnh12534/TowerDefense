package vn.ha.tower_defense.helpers;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

public interface LoadSave {
    
    public static BufferedImage loadAtlat(){
        InputStream inputStream = LoadSave.class.getResourceAsStream("/images/spriteatlas.png");
        try {
            return ImageIO.read(inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
