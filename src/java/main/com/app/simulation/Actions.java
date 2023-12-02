package main.com.app.simulation;

import main.com.app.entities.*;

import java.util.ArrayList;
import java.util.List;

public class Actions {
    public void initActions(WorldMap simulationWorldMap) {
        spawnEntities(simulationWorldMap, EntityType.HERBIVORE, 6);
        spawnEntities(simulationWorldMap, EntityType.PREDATOR, 2);
        spawnEntities(simulationWorldMap, EntityType.ROCK, 13);
        spawnEntities(simulationWorldMap, EntityType.GRASS, 15);
        spawnEntities(simulationWorldMap, EntityType.TREE, 4);
    }
    public void turnActions(WorldMap simulationWorldMap) {
        if (!isGameOver(simulationWorldMap)){
            makeMoves(simulationWorldMap.getListOfEntities(Creature.class), simulationWorldMap);
        } else {
            Simulation.pauseSimulation();
        }
    }
    public boolean isGameOver(WorldMap simulationWorldMap){
        List<? extends Entity> predators = simulationWorldMap.getListOfEntities(Predator.class);
        for (Entity predator : predators) {
            if (((Predator) predator).isHasNextPrey()) {
                return false; // Якщо хоч один вовк має наступну жертву, гра не закінчена
            }
        }
        return true; // Якщо жоден вовк не має наступної жертви, гра завершена
    }
    private void spawnEntities(WorldMap map, EntityType type, int count) {
        for (int i = 0; i < count; i++) {
            switch (type) {
                case HERBIVORE:
                    SpawnEntity.spawnHerbivore(map);
                    break;
                case PREDATOR:
                    SpawnEntity.spawnPredator(map);
                    break;
                case ROCK:
                    SpawnEntity.spawnRock(map);
                    break;
                case GRASS:
                    SpawnEntity.spawnGrass(map);
                    break;
                case TREE:
                    SpawnEntity.spawnTree(map);
                    break;
            }
        }
    }
    private void makeMoves(List<? extends Entity> entities, WorldMap simulationWorldMap) {
        for (Entity entity : entities) {
            ((Creature) entity).makeMove(simulationWorldMap);
        }
    }
    enum EntityType {
        HERBIVORE, PREDATOR, ROCK, GRASS, TREE
    }
    static class SpawnEntity {
        static void spawnPredator(WorldMap map) {
            map.addEntityToMap(new Predator(map.getRandomPoint(), Herbivore.class));
        }

        static void spawnHerbivore(WorldMap map) {
            map.addEntityToMap(new Herbivore(map.getRandomPoint(), Grass.class));
        }

        static void spawnRock(WorldMap map) {
            map.addEntityToMap(new Rock(map.getRandomPoint()));
        }

        static void spawnGrass(WorldMap map) {
            map.addEntityToMap(new Grass(map.getRandomPoint()));
        }

        static void spawnTree(WorldMap map) {
            map.addEntityToMap(new Tree(map.getRandomPoint()));
        }
    }
}
