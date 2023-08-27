package vn.ha.tower_defense.managers;

import vn.ha.tower_defense.emnemies.Enemy;
import vn.ha.tower_defense.emnemies.Position;
import vn.ha.tower_defense.observers.Event;
import vn.ha.tower_defense.observers.Observer;
import vn.ha.tower_defense.observers.Subject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class EnemyManager implements Observer {
    private List<BufferedImage> enemySprites = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();

    public EnemyManager() {
        loadEnemies();
    }

    private void loadEnemies() {
        BufferedImage atlast = loadAtlast("/images/spriteatlas.png");

        BufferedImage oreSprite = atlast.getSubimage(0, 32, 32, 32);
        enemySprites.add(oreSprite);

        Enemy ore = new Enemy(new Position(0, 0), enemySprites.size());
        enemies.add(ore);

    }

    private BufferedImage loadAtlast(String mapPath) {
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(mapPath);
            return ImageIO.read(inputStream);

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void draw(Graphics g) {
        enemies.forEach(enemy -> {
            drawEnemy(enemy, g);
        });
    }
    private void drawEnemy(Enemy enemy, Graphics g) {
        g.drawImage(getEnemySprite(enemy), (int)enemy.getPosition().getX(), (int)enemy.getPosition().getY(), null);
    }

    public BufferedImage getEnemySprite(Enemy enemy) {
        return enemySprites.get(enemy.getSpriteID() - 1);
    }

    public List<BufferedImage> getEnemySprites() {
        return this.enemySprites;
    }

    public void moveAllEnemies(Event event) {
        this.enemies.forEach(enemy -> {
            enemy.update(event);
        });
    }


    @Override
    public void update(Event event) {
        moveAllEnemies(new Event("MOVE", Event.EventType.UPDATE));
    }
}
