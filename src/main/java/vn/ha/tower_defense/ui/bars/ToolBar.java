package vn.ha.tower_defense.ui.bars;

import org.w3c.dom.Text;
import vn.ha.tower_defense.game.GameScreen;
import vn.ha.tower_defense.game.Position;
import vn.ha.tower_defense.helpers.FileHelper;
import vn.ha.tower_defense.helpers.SpriteModifier;
import vn.ha.tower_defense.map.LevelBuilder;
import vn.ha.tower_defense.observers.Event;
import vn.ha.tower_defense.observers.Observer;
import vn.ha.tower_defense.observers.Publisher;
import vn.ha.tower_defense.scenes.Scene;
import vn.ha.tower_defense.tiles.Tile;
import vn.ha.tower_defense.ui.buttons.GameButton;
import vn.ha.tower_defense.ui.buttons.TextButton;
import vn.ha.tower_defense.ui.buttons.TileButton;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ToolBar extends Bar implements Publisher {
    private int x, y, witdh, height = 320;
    private Color color = new Color(107, 201, 250, 98);
    private GameScreen gameScreen;
    private Position selectedTilePosition = new Position(700, 700);
    private List<TileButton> tileOptionBtns;
    private List<TextButton> textButtons;
    private List<Observer> observers = new ArrayList<>();

    public ToolBar(GameScreen gameScreen) {
        super(gameScreen);
        this.gameScreen = gameScreen;
        setSize(gameScreen);
        loadTileButtons(gameScreen);
        initializeTextButtons();
    }

    private void initializeTextButtons() {
        textButtons = new ArrayList<>();

        this.textButtons.add(new TextButton(0, 700, 100, 50, "SAVE"));
        this.loadPathButtons();
    }

    private void saveMap(Tile[][] map) {
        try {
            FileHelper.saveMap(map);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void drawTextButtons(Graphics g) {
        textButtons.forEach((textButton) -> {
            textButton.draw(g);
        });
    }

    private void loadTileButtons(GameScreen gameScreen) {
        int i = 0;
        this.tileOptionBtns = new ArrayList<>();
        for (Tile tile : gameScreen.getTileManager().getTiles()) {
            int id = tile.getId();
            if(id < 5 || (id % 4 == 1 && id > 16 )) {
                tileOptionBtns.add(
                        new TileButton(100 + x + i * 32 + i * (gameScreen.getTileManager().getSprite(tile).getWidth() / 10), y + 50, 32, 32,
                                tile));
                i++;
            }
        }
    }

    private void loadPathButtons(){
        TextButton startPathButton = new TextButton(70 + x + 32, y + 100, 34, 34, "Start");
        TextButton endPathButton = new TextButton(70 + x + 8 + 64, y + 100, 34, 34 , "End");
        this.textButtons.add(startPathButton);
        this.textButtons.add(endPathButton);
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
        this.drawTextButtons(g);
        Tile selectedTile = this.gameScreen.getCurrentScene().getSelectedTile();
        if (selectedTile != null) {
            g.drawImage(this.gameScreen.getTileManager().getSprite(selectedTile), selectedTilePosition.getX(),
                    selectedTilePosition.getY(), null);
        }
    }

    // Hanlde mouse event
    @Override
    public void handleMouseMoved(MouseEvent e) {
        List<GameButton> gameButtons = new ArrayList<>();
        gameButtons.addAll(this.tileOptionBtns);
        gameButtons.addAll(this.textButtons);
        for (GameButton gameButton : gameButtons) {
            gameButton.setIsHovered(false);
            if (gameButton.getBound().contains(e.getPoint())) {
                gameButton.setIsHovered(true);
                break;
            }
        }
    }

    @Override
    public void handleMousePressed(MouseEvent e) {
        List<GameButton> gameButtons = new ArrayList<>();
        gameButtons.addAll(this.tileOptionBtns);
        gameButtons.addAll(this.textButtons);
        for (GameButton gameButton : gameButtons) {
            gameButton.setIsPressed(false);
            if (gameButton.getBound().contains(e.getPoint())) {
                gameButton.setIsPressed(true);
                break;
            }
        }
    }

    @Override
    public void handleMouseClicked(MouseEvent e) {

        textButtons.forEach(textButton -> {
            if (textButton.getBound().contains(e.getPoint())) {
                if (textButton.getText().equals("SAVE")) {
                    handleSaveButtonClicked();
                }
            }
        });

        for (TileButton gameButton : tileOptionBtns) {
            if (gameButton.getBound().contains(e.getPoint())) {
                // Handle mouse clicked
                System.out.println(gameButton.getTile().getIsCorner());
                this.gameScreen.getCurrentScene().setSelectedTile(gameButton.getTile());
                break;
            }
        }
    }

    private void handleSaveButtonClicked() {
        saveMap(LevelBuilder.getLevelBuilder().getMap().getMap());
        System.out.println("Saving map");
        this.notifyAll(Event
                .builder()
                .message("Save map")
                .eventType(Event.EventType.SAVE_MAP)
                .build());
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
        this.observers.add(observer);
    }

    @Override
    public void attachAll(List<? extends Observer> observers) {
        this.observers.addAll(observers);
    }

    @Override
    public void detach(Observer observer) {

    }

    @Override
    public void notifyAll(Event event) {
        this.observers.forEach(observer -> observer.update(event));
    }

    @Override
    public void detachAll() {

    }
}
