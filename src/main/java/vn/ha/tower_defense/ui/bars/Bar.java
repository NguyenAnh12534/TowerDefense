package vn.ha.tower_defense.ui.bars;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.MouseEvent;

import vn.ha.tower_defense.game.GameScreen;

import vn.ha.tower_defense.observers.Publisher;
import vn.ha.tower_defense.ui.buttons.GameButton;
import vn.ha.tower_defense.ui.buttons.TileButton;

public abstract class Bar implements Publisher {
    private GameScreen gameScreen;

    public Bar(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    // get attribute from subclass
    public abstract int getX();

    public abstract int getY();

    public abstract int getWidth();

    public abstract int getHeight();

    public abstract Color getColor();

    public abstract void setColor(Color color);

    abstract protected List<TileButton> getTileButtons();

    public abstract void draw(Graphics g);

    protected void drawBackground(Graphics g) {
        for (int yCor = this.getY(); yCor < gameScreen.getHeight(); yCor += 32) {
            for (int xCor = this.getX(); xCor < this.getWidth(); xCor += 32) {
                g.setColor(this.getColor());
                g.fillRect(xCor, yCor, 32, 32);
            }
        }
    }

    protected void drawTilesButton(Graphics g) {
        for (GameButton tileButton : getTileButtons()) {
            tileButton.draw(g);
        }
    }

    // Handle mouse event
    abstract public void handleMouseMoved(MouseEvent e);

    abstract public void handleMousePressed(MouseEvent e);

    abstract public void handleMouseClicked(MouseEvent e);

    abstract public void handleMouseReleased(MouseEvent e);

}
