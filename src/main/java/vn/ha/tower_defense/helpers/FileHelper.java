package vn.ha.tower_defense.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import vn.ha.tower_defense.game.Game;
import vn.ha.tower_defense.map.MapSize;
import vn.ha.tower_defense.tiles.Tile;

public class FileHelper {

    public static void saveMap(Tile[][] tileMap) throws IOException {
        String directoryPath = "map/";
        String mapFile = "map.bin";

        Path filePath = Path.of(directoryPath, mapFile);
        if(!Files.exists(filePath))
        {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        }

        int mapWidth = tileMap[0].length;
        int mapHeight = tileMap.length;
        MapSize mapSize = new MapSize(mapWidth, mapHeight);
        ObjectOutputStream tileWriter = new ObjectOutputStream(new FileOutputStream(filePath.toString()));
        tileWriter.writeObject(mapSize);
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                tileWriter.writeObject(tileMap[y][x]);
            }
        }

        tileWriter.close();
    }

    public static Tile[][] loadMap(String tileMap) throws Exception {
        File mapFile = null;
        try {
            mapFile = new File(tileMap);
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        if (!mapFile.exists()) {
           return null;
        }
        InputStream inputStream = new FileInputStream(mapFile);

        ObjectInputStream mapReader = new ObjectInputStream(inputStream);

        MapSize mapSize = (MapSize) mapReader.readObject();

        Tile[][] map = new Tile[mapSize.getHeight()][mapSize.getWidth()];

        for (int y = 0; y < mapSize.getHeight(); y++) {
            for (int x = 0; x < mapSize.getWidth(); x++) {
                map[y][x] = (Tile) mapReader.readObject();
            }
        }
        mapReader.close();
        return map;
    }
}
