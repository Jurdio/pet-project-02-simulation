package main.com.app.simulation;

import main.com.app.entities.*;

import java.util.Iterator;

public class Actions {
    public void initActions(WorldMap simulationWorldMap) {
        SpawnEntity.spawnHerbivore(simulationWorldMap);
        SpawnEntity.spawnPredator(simulationWorldMap);
        SpawnEntity.spawnHerbivore(simulationWorldMap);
        SpawnEntity.spawnPredator(simulationWorldMap);
        SpawnEntity.spawnHerbivore(simulationWorldMap);
        SpawnEntity.spawnRock(simulationWorldMap);
        SpawnEntity.spawnRock(simulationWorldMap);
        SpawnEntity.spawnRock(simulationWorldMap);
        SpawnEntity.spawnRock(simulationWorldMap);
        SpawnEntity.spawnRock(simulationWorldMap);
        SpawnEntity.spawnRock(simulationWorldMap);
        SpawnEntity.spawnRock(simulationWorldMap);
        SpawnEntity.spawnRock(simulationWorldMap);
        SpawnEntity.spawnGrass(simulationWorldMap);
        SpawnEntity.spawnGrass(simulationWorldMap);
        SpawnEntity.spawnGrass(simulationWorldMap);


    }
    public void turnActions(WorldMap simulationWorldMap) {
        // Виконати хід для хижаків
        for (Predator predator : simulationWorldMap.getPredators()) {
            predator.makeMove(simulationWorldMap);
        }

        // Виконати хід для травоїдних
        for (Herbivore herbivore : simulationWorldMap.getHerbivore()) {
            herbivore.makeMove(simulationWorldMap);
        }
    }
    static class SpawnEntity {
        static void spawnPredator(WorldMap map) {
            map.addEntityToMap(new Predator(map.getRandomPoint(),Herbivore.class));
        }
        static void spawnHerbivore(WorldMap map) {
            map.addEntityToMap(new Herbivore(map.getRandomPoint(), Grass.class));
        }
        static void spawnRock(WorldMap map){
            map.addEntityToMap(new Rock(map.getRandomPoint()));
        }
        static void spawnGrass(WorldMap map){
            map.addEntityToMap(new Grass(map.getRandomPoint()));
        }

    }
}
