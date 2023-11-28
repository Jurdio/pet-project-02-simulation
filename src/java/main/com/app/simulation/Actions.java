package main.com.app.simulation;

import main.com.app.entities.*;

import java.util.Iterator;
import java.util.List;

public class Actions {
    public void initActions(WorldMap simulationWorldMap) {
        SpawnEntity.spawnPredator(simulationWorldMap);
        SpawnEntity.spawnHerbivore(simulationWorldMap);
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
    }
}
