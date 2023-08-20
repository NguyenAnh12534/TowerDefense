package vn.ha.tower_defense;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import vn.ha.tower_defense.exceptions.BaseException;
import vn.ha.tower_defense.exceptions.EnrichableException;
import vn.ha.tower_defense.exceptions.ExceptionHandler;
import vn.ha.tower_defense.game.Game;

public class TowerDefenseApplication {

    public static void main(String[] args) {


        TowerDefenseApplication test = new TowerDefenseApplication();
        Game game = new Game();
        try {
            game.start();
        } catch (BaseException ez) {

        }

    }


}
