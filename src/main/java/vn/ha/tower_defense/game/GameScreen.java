package vn.ha.tower_defense.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import vn.ha.tower_defense.inputs.GameMouseListener;
import vn.ha.tower_defense.managers.TileManager;
import vn.ha.tower_defense.scenes.MenuScene;
import vn.ha.tower_defense.scenes.PlayScene;
import vn.ha.tower_defense.scenes.EditScene;
import vn.ha.tower_defense.scenes.Scene;

public class GameScreen extends JPanel {
    private int width = 960;
    private int height = 960;
    private Render render = new Render();
    private Scene gameScene;
    private TileManager tileManager;
    private Map<GameState, Scene> gameScenes = new HashMap<>();

    public GameScreen(Game game) {
        this.tileManager = new TileManager();
        this.tileManager.loadAtlat(importImg());
        game.attachAll(this.tileManager.getTiles());

        loadScences();

        setSize();
        GameMouseListener mouseListener = new GameMouseListener(this);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        setupKeyBindings();
        requestFocus();

    }

    private void loadScences() {
        this.gameScenes.put(GameState.PLAYING, new PlayScene(this));
        this.gameScenes.put(GameState.EDIT, new EditScene(this));
        this.gameScenes.put(GameState.MENU, new MenuScene(this));
    }

    public Scene getCurrentScene() {
        return this.gameScene;
    }

    private void renderGameScene(Graphics g) {
        this.gameScene = this.gameScenes.get(Game.currState);
        render.renderScene(gameScene, g);
    }

    private void setSize() {
        Dimension dimension = new Dimension(width, height);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderGameScene(g);
    }

    // You can add more key bindings for different keys and actions if needed
    private void setupKeyBindings() {
        // Define the key events and their corresponding actions
        KeyStroke keyStrokeP = KeyStroke.getKeyStroke("pressed P");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeP, "switchToPlayingScene");
        this.getActionMap().put("switchToPlayingScene", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform your desired action here when SPACE key is pressed
                Game.currState = GameState.PLAYING;
            }
        });

        KeyStroke keyStrokeM = KeyStroke.getKeyStroke("pressed M");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStrokeM, "switchToMenuScene");
        this.getActionMap().put("switchToMenuScene", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform your desired action here when SPACE key is pressed
                Game.currState = GameState.MENU;
            }
        });

    }

    private BufferedImage importImg() {
        try {
            InputStream inputStream = new FileInputStream("src/main/resources/images/spriteatlas.png");
            return ImageIO.read(inputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public TileManager getTileManager() {
        return this.tileManager;
    }

    // Getter and Setter
    public int getWidth() {
        int a = this.width;
        return a;
    }

    public int getHeight() {
        return this.height;
    }

}
