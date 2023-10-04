package vn.ha.tower_defense;

import vn.ha.tower_defense.exceptions.BaseException;
import vn.ha.tower_defense.game.Game;

import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class TowerDefenseApplication {

    public static void main(String[] args) {
        Game game = new Game();
        try {
            game.start();
        } catch (BaseException ez) {

        }

    }


}
