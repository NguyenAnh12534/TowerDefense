package vn.ha.tower_defense.scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import vn.ha.tower_defense.game.Game;
import vn.ha.tower_defense.game.GameScreen;
import vn.ha.tower_defense.game.GameState;
import vn.ha.tower_defense.observers.Event;
import vn.ha.tower_defense.observers.Observer;
import vn.ha.tower_defense.ui.buttons.GameButton;
import vn.ha.tower_defense.ui.buttons.TextButton;

public class MenuScene extends Scene {

    GameButton button = new GameButton(100, 100, 100, 30, "PLAY");

    List<GameButton> gameButtons = new ArrayList<>();

    GameScreen gameScreen;

    public MenuScene(GameScreen gameScreen) {
        super(gameScreen.getTileManager());
        this.gameScreen = gameScreen;
        initButtons();
    }

    public void initButtons() {
        int btnWidth = 100;
        int btnHeight = 30;

        int centerX = this.gameScreen.getWidth() / 2 - btnWidth / 2;
        int centerY = this.gameScreen.getHeight() / 2 - btnHeight / 2;

        GameButton playButton = new TextButton(centerX, centerY, btnWidth, btnHeight, "PLAY");
        GameButton menuButton = new TextButton(centerX, centerY + btnHeight + 10, btnWidth, btnHeight, "SETTING");
        GameButton editButton = new TextButton(centerX, centerY + 2 * btnHeight + 20, btnWidth, btnHeight, "EDIT");
        GameButton exitButton = new TextButton(centerX, centerY + 3 * btnHeight + 30, btnWidth, btnHeight, "EXIT");

        gameButtons.add(playButton);
        gameButtons.add(menuButton);
        gameButtons.add(editButton);
        gameButtons.add(exitButton);
    }

    @Override
    public void render(Graphics g) {
        // TODO Auto-generated method stub
        for (GameButton gameButton : gameButtons) {
            gameButton.draw(g);
        }
    }

    @Override
    public void handleMouseClicked(MouseEvent e) {
        for (GameButton gameButton : gameButtons) {
            if (gameButton.getBound().contains(e.getPoint())) {
                switch (gameButton.getText()) {
                    case "PLAY":
                        Game.currState = GameState.PLAYING;
                        break;
                    case "EDIT":
                        Game.currState = GameState.EDIT;
                        break;
                    default:
                    Game.currState = GameState.MENU;
                        break;

                }
            }
        }

    }

    @Override
    public void handleMouseMoved(MouseEvent e) {
        for (GameButton gameButton : gameButtons) {
            gameButton.setIsHovered(false);
            if (gameButton.getBound().contains(e.getPoint())) {
                gameButton.setIsHovered(true);
            }
        }

    }

    @Override
    public void handleMousePressed(MouseEvent e) {
        for (GameButton gameButton : gameButtons) {
            if (gameButton.getBound().contains(e.getPoint())) {
                gameButton.setIsPressed(true);
            }
        }
    }

    @Override
    public void handleMouseReleased(MouseEvent e) {
        for (GameButton gameButton : gameButtons) {
            gameButton.setIsPressed(false);
            gameButton.setIsHovered(false);
        }
    }

    @Override
    public void hanldeMouseDragged(MouseEvent e) {

    }

    @Override
    public void hanldeKeyTyped(KeyEvent e) {

    }

    @Override
    public void update(Event event) {

    }

    @Override
    public void attach(Observer observer) {

    }



    @Override
    public void attachAll(List<? extends Observer> observers) {

    }

    @Override
    public void detach(Observer observer) {

    }

    @Override
    public void notifyAll(Event event) {

    }

    @Override
    public void detachAll() {

    }
}
