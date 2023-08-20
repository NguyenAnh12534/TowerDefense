package vn.ha.tower_defense.game;

import vn.ha.tower_defense.exceptions.MapLoadException;
import vn.ha.tower_defense.exceptions.SpriteLoadException;
import javax.swing.JFrame;
import vn.ha.tower_defense.inputs.KeyBoardListener;

public class Game  extends JFrame  implements Runnable  {

    public static GameState currState = GameState.MENU;

    private final int FPS = 120;
    private final int UPS = 60;

    public Game() {
        GameScreen gameScreen = new GameScreen();
        add(gameScreen);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        addKeyListener(new KeyBoardListener(gameScreen));

    }

    public void start() throws SpriteLoadException, MapLoadException{
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        int frameCounter = 0;
        int updateCounter = 0;

        final double TIME_PER_RENDER = 1000000000.0 / FPS;
        final double TIME_PER_UPDATE = 1000000000.0 / UPS;

        long lastUpdate = System.nanoTime();
        long lastRender = System.nanoTime();
        long lastCheck = System.currentTimeMillis();

        while (true) {
            long currentTime = System.nanoTime();
            if (currentTime - lastRender >= TIME_PER_RENDER) {
                lastRender = currentTime;
                repaint();
                frameCounter++;
            }

            if (currentTime - lastUpdate >= TIME_PER_UPDATE) {
                lastUpdate = currentTime;
                updateCounter++;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                // System.out.println("FPS: " + frameCounter + " | " + "UPS: " + updateCounter);
                frameCounter = 0;
                updateCounter = 0;
            }
        }
    }

    public void update() {
        // System.out.println("Update ");
    }
}