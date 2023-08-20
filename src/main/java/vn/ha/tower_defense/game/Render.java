package vn.ha.tower_defense.game;


import java.awt.Graphics;

import vn.ha.tower_defense.managers.TileManager;
import vn.ha.tower_defense.scenes.Scene;

public class Render {


    public void renderScene(Scene gamScene, Graphics graphics) {
        gamScene.render(graphics);
    }
}
