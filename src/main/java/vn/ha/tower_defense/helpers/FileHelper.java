package vn.ha.tower_defense.helpers;

import java.io.*;
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

    public static Tile[][] getMapFromFile(File mapFile) throws Exception{
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

    public static File getFileFromPath(String mapPath) throws FileNotFoundException {
        File mapFile = null;
        try {
            mapFile = new File(mapPath);
        }catch (Exception ex) {
            ex.printStackTrace();
        }

        if (!mapFile.exists()) {
            throw new FileNotFoundException();
        }
        return mapFile;
    }


    public static Tile[][] loadMapFromPath(String mapPath) throws Exception {
        File mapFile = getFileFromPath(mapPath);
        return getMapFromFile(mapFile);
    }
}
