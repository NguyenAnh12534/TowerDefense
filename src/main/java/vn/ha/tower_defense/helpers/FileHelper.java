package vn.ha.tower_defense.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import vn.ha.tower_defense.map.MapSize;
import vn.ha.tower_defense.tiles.Tile;

public class FileHelper {

    public boolean createFile(String fileName) {
        try {
            File file = new File("src/main/resources/files/map.txt");
            if (file.exists())
                return false;
            file.createNewFile();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    // 1 2 3

    public static void saveMap(double[][] map) throws Exception {
        File mapFile = new File("src/main/resources/files/map.txt");
        if (!mapFile.exists()) {
            mapFile.createNewFile();
        }

        int y = map.length;
        int x = map[0].length;
        try (PrintWriter fileWriter = new PrintWriter(mapFile)) {

            fileWriter.println(y + " " + x);
            for (int i = 0; i < y; i++) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < x; j++) {

                    stringBuilder.append(map[i][j]);
                    if (j < x - 1) {
                        stringBuilder.append(" ");
                    }
                }

                fileWriter.println(stringBuilder.toString());
                stringBuilder.setLength(0);
            }
        }

    }

    public static String getMainPath() {
        return System.getProperty("user.dir") + "/src/main";
    }

    public static String getMainPath(String to) {
        return System.getProperty("user.dir") + "/src/main" + to;
    }

    public static double[][] readMap(String fileName) throws Exception {

        File mapFile = new File("src/main/resources/files/map.txt");
        mapFile.createNewFile();
        double[][] map = null;

        try (Scanner fileScanner = new Scanner(mapFile)) {

            double y = fileScanner.nextDouble();
            double x = fileScanner.nextDouble();

            map = new double[(int) y][(int) x];

            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    map[i][j] = fileScanner.nextDouble();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

    public static void saveMap(Tile[][] tileMap) throws IOException {
        File mapFile = new File("src/main/resources/files/map.bin");

        if (!mapFile.exists()) {
            mapFile.createNewFile();
        }

        int mapWidth = tileMap[0].length;
        int mapHeight = tileMap.length;
        MapSize mapSize = new MapSize(mapWidth, mapHeight);

        ObjectOutputStream tileWriter = new ObjectOutputStream(new FileOutputStream(mapFile));
        tileWriter.writeObject(mapSize);
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                tileWriter.writeObject(tileMap[y][x]);
            }
        }

        tileWriter.close();
    }

    public static Tile[][] loadMap(String tileMap) throws Exception {
        File mapFile = new File("src/main/resources/files/map.bin");

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
