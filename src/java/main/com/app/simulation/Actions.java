package main.com.app.simulation;

import main.com.app.entities.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Actions {
    public void initActions(WorldMap simulationWorldMap) {
        SpawnEntity.spawnHerbivore(simulationWorldMap);
        SpawnEntity.spawnPredator(simulationWorldMap);


    }
    public void turnActions(WorldMap simulationWorldMap) {
        Iterator<Predator> iterator = simulationWorldMap.getPredators().iterator();
        while (iterator.hasNext()) {
            Predator predator = iterator.next();
            predator.makeMove(simulationWorldMap);
        }
    }
    static class SpawnEntity {
        static void spawnPredator(WorldMap map) {
            map.addEntityToMap(new Predator(), map.getRandomPoint());
        }
        static void spawnHerbivore(WorldMap map) {
            map.addEntityToMap(new Herbivore(), map.getRandomPoint());
        }
        static void spawnHerbivoreWithPoint(WorldMap map) { map.addEntityToMap(new Herbivore(new Point(1,1)), new Point(1,1));}
    }
}
