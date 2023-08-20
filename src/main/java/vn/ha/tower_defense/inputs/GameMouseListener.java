package vn.ha.tower_defense.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.event.MouseInputListener;

import vn.ha.tower_defense.game.GameScreen;

public class GameMouseListener implements MouseInputListener {

    private GameScreen gameScreen;

    public GameMouseListener(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public GameMouseListener() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.gameScreen.getCurrentScene().handleMouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        this.gameScreen.getCurrentScene().handleMousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        this.gameScreen.getCurrentScene().handleMouseReleased(e);

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        this.gameScreen.getCurrentScene().hanldeMouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.gameScreen.getCurrentScene().handleMouseMoved(e);
    }

}
