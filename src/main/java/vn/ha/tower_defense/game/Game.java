package vn.ha.tower_defense.game;

import vn.ha.tower_defense.exceptions.MapLoadException;
import vn.ha.tower_defense.exceptions.SpriteLoadException;

import javax.swing.JFrame;

import vn.ha.tower_defense.inputs.KeyBoardListener;
import vn.ha.tower_defense.observers.Event;
import vn.ha.tower_defense.observers.Observer;
import vn.ha.tower_defense.observers.Subject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game extends JFrame implements Runnable, Subject {

    public static GameState currState = GameState.MENU;
    private List<Observer> observers = new ArrayList<>();
    private static final int FPS = 120;
    public static final int UPS = 60;

    public Game() {
        GameScreen gameScreen = new GameScreen(this);
        add(gameScreen);
        attach(gameScreen);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        addKeyListener(new KeyBoardListener(gameScreen));
    }

    public void start() throws SpriteLoadException, MapLoadException {
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
        Event updateEvent = new Event("Update", Event.EventType.UPDATE);

        while (true) {
            long currentTime = System.nanoTime();
            if (currentTime - lastRender >= TIME_PER_RENDER) {
                lastRender = currentTime;
                repaint();
                frameCounter++;
            }

            if (currentTime - lastUpdate >= TIME_PER_UPDATE) {
                notifyAll(updateEvent);
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
        this.observers.remove(observer);
    }

    @Override
    public void notifyAll(Event event) {
        this.observers.forEach(observer -> observer.update(event));
    }


    @Override
    public void detachAll() {
        this.observers.clear();
    }
}