package main.com.app.simulation;

import main.com.app.entities.Point;

import main.com.app.entities.Entity;
import main.com.app.entities.WorldMap;

public class Renderer {
    public final String GROUND = "\uD83D\uDFEB"; // 🟫

    public void renderWorldMap(WorldMap map) {
        clearConsole();

        String line;
        for (int i = 0; i < map.getMapSize(); i++) {
            line = "";
            for (int j = 0; j < map.getMapSize(); j++) {
                Entity entity = map.getEntityByPoint(new Point(i, j));
                line += entity != null ? entity.toString() : GROUND;
            }
            System.out.println(line);
        }
    }

    // Метод для очищення консолі
    public void clearConsole() {
        System.out.print("\033[H\033[J");
        System.out.flush();
    }
}