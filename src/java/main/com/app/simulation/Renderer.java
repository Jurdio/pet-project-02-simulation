package main.com.app.simulation;

import main.com.app.entities.Point;

import main.com.app.entities.Entity;
import main.com.app.entities.WorldMap;

public class Renderer {
    public final String GROUND = "\uD83D\uDFEB"; // 🟫
    public void renderWorldMap(WorldMap map){
        String line;
        for (int i = 0; i < map.getMapSize(); i++){
            line = "";
            for (int j = 0; j < map.getMapSize(); j++){
                Entity entity = map.getEntityByPoint(new Point(i,j));
                line += entity != null ? entity.toString() : GROUND;
            }
            System.out.println(line);
        }
    }
}
