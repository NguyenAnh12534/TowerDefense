package vn.ha.tower_defense.scenes;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import vn.ha.tower_defense.game.GameScreen;
import vn.ha.tower_defense.game.Position;
import vn.ha.tower_defense.helpers.FileHelper;
import vn.ha.tower_defense.helpers.SpriteModifier;
import vn.ha.tower_defense.map.LevelBuilder;
import vn.ha.tower_defense.map.TileMap;
import vn.ha.tower_defense.observers.Event;
import vn.ha.tower_defense.observers.Observer;
import vn.ha.tower_defense.tiles.Tile;
import vn.ha.tower_defense.ui.bars.Bar;
import vn.ha.tower_defense.ui.bars.ToolBar;

public class EditScene extends Scene {
    private TileMap map;
    private Bar bottomBar;
    private GameScreen gameScreen;
    private Tile selectedTile;
    private Position mousePosition = new Position(0, 0);

    private List<Observer> observers = new ArrayList<>();

    public EditScene(GameScreen gameScreen) {
        super(gameScreen.getTileManager());
        this.gameScreen = gameScreen;
        loadMap();
        this.setBottomBar(new ToolBar(gameScreen));
    }

    private void loadMap() {
        try {
            this.map = LevelBuilder.getLevelBuilder().getMap();
            attachMapToGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void attachMapToGame() {
        for (int i = 0; i < this.map.getHeight(); i++) {
            for (int j = 0; j < this.map.getWidth(); j++) {
                this.attach(this.map.getTileAt(i, j));
            }
        }
    }

    private void setCurrentMousePoint(MouseEvent e) {
        mousePosition.setx((e.getX() / 32) * 32);
        mousePosition.setY((e.getY() / 32) * 32);
    }

    @Override
    public void render(Graphics g) {
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                BufferedImage newSprite = SpriteModifier.buildSprite(this.gameScreen.getTileManager(), map.getTileAt(y, x));
                g.drawImage(newSprite, x * 32, y * 32,
                        null);
            }
        }

        this.bottomBar.draw(g);
        if (selectedTile != null)
            g.drawImage(this.gameScreen.getTileManager().getSprite(selectedTile), mousePosition.getX(), mousePosition.getY(), null);

        g.dispose();
    }

    @Override
    public void handleMouseClicked(MouseEvent e) {
        if (e.getPoint().getY() >= this.bottomBar.getY()) {
            this.bottomBar.handleMouseClicked(e);
        } else {
            if (selectedTile.isLayer()) {
                map.getTileAt(mousePosition.getY() / 32, mousePosition.getX() / 32).addLayer(selectedTile);
                System.out.println("Add layer: " + selectedTile.getSpriteID());
            } else {
                Tile tileToUpdate = map.getTileAt(mousePosition.getY() / 32, mousePosition.getX() / 32);
                System.out.println("Change from: " + tileToUpdate.getSpriteID() + " to " + selectedTile.getSpriteID());

                tileToUpdate.setSpriteID(selectedTile.getSpriteID());
                tileToUpdate.setSpriteSheet(selectedTile.getSpriteIDs());
                tileToUpdate.setIsCorner(selectedTile.getIsCorner());
                tileToUpdate.setBlockedDirections(selectedTile.getBlockedDirections());
                tileToUpdate.setAnimationSpeed(selectedTile.getAnimationSpeed());

                if (selectedTile.getSpriteID() >= 29 && selectedTile.getSpriteID() <= 32) {
                    System.out.println();
                }
            }
        }
    }

    @Override
    public void handleMouseMoved(MouseEvent e) {
        if (e.getPoint().getY() >= this.bottomBar.getY()) {
            this.bottomBar.handleMouseMoved(e);
        } else {
            setCurrentMousePoint(e);
        }
    }

    @Override
    public void handleMousePressed(MouseEvent e) {
        if (e.getPoint().getY() >= this.bottomBar.getY()) {
            this.bottomBar.handleMousePressed(e);
        }
    }

    @Override
    public void handleMouseReleased(MouseEvent e) {
        if (e.getPoint().getY() >= this.bottomBar.getY()) {
            this.bottomBar.handleMouseReleased(e);
        }
    }

    @Override
    public void hanldeMouseDragged(MouseEvent e) {
        if (e.getPoint().getY() >= this.bottomBar.getY()) {
        } else {
            setCurrentMousePoint(e);
            if (selectedTile.isLayer()) {
                map.getTileAt(mousePosition.getY() / 32, mousePosition.getX() / 32).addLayer(selectedTile);
                System.out.println("Add layer: " + selectedTile.getSpriteID());
            } else {
                System.out.println("Change from: " + map.getTileAt(mousePosition.getY() / 32, mousePosition.getX() / 32).getSpriteID() + " to " + selectedTile.getSpriteID());
                map.getTileAt(mousePosition.getY() / 32, mousePosition.getX() / 32).setSpriteID(selectedTile.getSpriteID());
                map.getTileAt(mousePosition.getY() / 32, mousePosition.getX() / 32).setSpriteSheet(selectedTile.getSpriteIDs());

            }
        }
    }

    @Override
    public void hanldeKeyTyped(KeyEvent e) {
        System.out.println("rotate");
        if (selectedTile != null) {
            int id = selectedTile.getId() % 4 == 0 ? selectedTile.getId() - 3 : selectedTile.getId() + 1;
            selectedTile = this.gameScreen.getTileManager().getTile(id);
        }
    }

    // Getter and Setter
    public Tile getSelectedTile() {
        return this.selectedTile;
    }

    public void setSelectedTile(Tile sTile) {
        this.selectedTile = sTile;
    }

    @Override
    public void update(Event event) {
        notifyAll(event);
    }

    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void attachAll(List<? extends Observer> observers) {

    }

    @Override
    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyAll(Event event) {
        this.observers.forEach(observer -> {
            observer.update(event);
        });
    }

    @Override
    public void detachAll() {
        this.observers.clear();
    }

    public Bar getBottomBar() {
        return bottomBar;
    }

    public void setBottomBar(Bar bottomBar) {
        this.bottomBar = bottomBar;
        this.bottomBar.attach(this);
    }
}
