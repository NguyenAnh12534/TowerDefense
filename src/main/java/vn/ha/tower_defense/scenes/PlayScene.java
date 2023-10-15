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
import vn.ha.tower_defense.managers.EnemyManager;
import vn.ha.tower_defense.map.LevelBuilder;
import vn.ha.tower_defense.map.TileMap;
import vn.ha.tower_defense.observers.Event;
import vn.ha.tower_defense.observers.Observer;
import vn.ha.tower_defense.tiles.Tile;
import vn.ha.tower_defense.ui.bars.ActionBar;
import vn.ha.tower_defense.ui.bars.Bar;

public class PlayScene extends Scene {

    public static TileMap map;
    private Bar bottomBar;
    private GameScreen gameScreen;
    private Tile selectedTile;
    private Position mousePosition = new Position(0, 0);
    private EnemyManager enemyManager;
    private List<Observer> observers = new ArrayList<>();

    public PlayScene(GameScreen gameScreen) {
        super(gameScreen.getTileManager());
        this.gameScreen = gameScreen;
        this.enemyManager = new EnemyManager();
        this.attach(enemyManager);
        this.loadMap();
        this.bottomBar = new ActionBar(gameScreen);

    }


    private void loadMap() {
        map = LevelBuilder.getLevelBuilder().getMap();
        attachMapToGame();
    }

    private void attachMapToGame() {
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                this.attach(map.getTileAt(i, j));
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
        this.enemyManager.draw(g);
        this.bottomBar.draw(g);
    }


    @Override
    public void handleMouseClicked(MouseEvent e) {
//        if (e.getPoint().getY() >= this.bottomBar.getY()) {
//            this.bottomBar.handleMouseClicked(e);
//        } else {
//            map[mousePosition.getY() / 32][mousePosition.getX() / 32] = selectedTile;
//        }
    }

    @Override
    public void handleMouseMoved(MouseEvent e) {
//        if (e.getPoint().getY() >= this.bottomBar.getY()) {
//            this.bottomBar.handleMouseMoved(e);
//        } else {
//            setCurrentMousePoint(e);
//
//        }
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
//        if (e.getPoint().getY() >= this.bottomBar.getY()) {
//        } else {
//            setCurrentMousePoint(e);
//            map[mousePosition.getY() / 32][mousePosition.getX() / 32] = selectedTile;
//        }
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
        switch (event.getEventType()) {
            case UPDATE -> notifyAll(event);
            case SAVE_MAP -> this.loadMap();
        }
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
}
