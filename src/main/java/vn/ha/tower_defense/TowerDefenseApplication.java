package vn.ha.tower_defense;

import vn.ha.tower_defense.exceptions.BaseException;
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
