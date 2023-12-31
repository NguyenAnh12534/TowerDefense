package vn.ha.tower_defense.ui.bars;

import java.util.ArrayList;
import java.util.List;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import vn.ha.tower_defense.game.GameScreen;
import vn.ha.tower_defense.game.Position;
import vn.ha.tower_defense.observers.Event;
import vn.ha.tower_defense.observers.Observer;
import vn.ha.tower_defense.tiles.Tile;
import vn.ha.tower_defense.ui.buttons.GameButton;
import vn.ha.tower_defense.ui.buttons.TileButton;

public class ActionBar extends Bar {
    private int x, y, witdh, height = 320;
    private Color color = new Color(107, 201, 250, 98);
    private GameScreen gameScreen;
    private Position selectedTilePosition = new Position(700, 700);
    private List<TileButton> tileOptionBtns;

    public ActionBar(GameScreen gameScreen) {
        super(gameScreen);
        this.gameScreen = gameScreen;
        setSize(gameScreen);
        loadTileButtons(gameScreen);
    }

    private void loadTileButtons(GameScreen gameScreen) {
        this.tileOptionBtns = new ArrayList<>();
    }

    private void setSize(GameScreen gameScreen) {
        this.witdh = gameScreen.getWidth();
        this.x = 0;
        this.y = gameScreen.getHeight() - this.height;
    }

    @Override
    public void draw(Graphics g) {
        super.drawBackground(g);
        super.drawTilesButton(g);
        Tile selectTile = this.gameScreen.getCurrentScene().getSelectedTile();
        if (selectTile != null) {
            g.drawImage(this.gameScreen.getTileManager().getSprite(selectTile), selectedTilePosition.getX(),
                    selectedTilePosition.getY(), null);
        }
    }

    @Override
    public void handleMouseMoved(MouseEvent e) {
        for (GameButton gameButton : tileOptionBtns) {
            gameButton.setIsHovered(false);
            if (gameButton.getBound().contains(e.getPoint())) {
                gameButton.setIsHovered(true);
                break;
            }
        }
    }

    @Override
    public void handleMousePressed(MouseEvent e) {
        for (GameButton gameButton : tileOptionBtns) {
            gameButton.setIsPressed(false);
            if (gameButton.getBound().contains(e.getPoint())) {
                gameButton.setIsPressed(true);
                break;
            }
        }
    }

    @Override
    public void handleMouseClicked(MouseEvent e) {

        for (TileButton gameButton : tileOptionBtns) {
            if (gameButton.getBound().contains(e.getPoint())) {
                // Handle mouse clicked
                this.gameScreen.getCurrentScene().setSelectedTile(gameButton.getTile());
                break;
            }
        }
    }

    @Override
    public void handleMouseReleased(MouseEvent e) {
        for (GameButton gameButton : tileOptionBtns) {
            gameButton.setIsPressed(false);
            gameButton.setIsHovered(false);
        }

    }

    // Getter and Setter
    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getWidth() {
        return this.witdh;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    protected List<TileButton> getTileButtons() {
        return this.tileOptionBtns;
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
