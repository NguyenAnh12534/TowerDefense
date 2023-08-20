package vn.ha.tower_defense.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import vn.ha.tower_defense.game.Game;
import vn.ha.tower_defense.game.GameScreen;
import vn.ha.tower_defense.game.GameState;

public class KeyBoardListener extends KeyAdapter {

    private GameScreen gameScreen;

    public KeyBoardListener(GameScreen gameScreen) {
        super();
        this.gameScreen = gameScreen;
    }

    public KeyBoardListener() {
        super();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("user typing");
        System.out.println(e.getKeyChar());
        switch (e.getKeyChar()) {
            case 'r': {
                System.out.println("user typed r");
                gameScreen.getCurrentScene().hanldeKeyTyped(e);
            }
            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_M:
                System.out.println("M");
                Game.currState = GameState.MENU;
                break;
            case KeyEvent.VK_P:
                System.out.println("P");
                Game.currState = GameState.PLAYING;
                break;
            default:
                System.out.println("Key isn't mapped");
        }
    }

}
