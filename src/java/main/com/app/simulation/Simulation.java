package main.com.app.simulation;

import main.com.app.entities.WorldMap;


public class Simulation {
    private WorldMap map;
    private Actions actions;
    private Renderer renderer;
    private int turnCount;
    private boolean isGamePaused;

    public Simulation() {
        map = new WorldMap();
        actions = new Actions();
        renderer = new Renderer();
        turnCount = 0;
        isGamePaused = false;

        actions.initActions(map);
    }

    public void nextTurn() {
        renderer.renderWorldMap(map);
        actions.turnActions(map);
        turnCount++;
        System.out.println("Move in the game number :" + turnCount);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startSimulation() {
        while (!isGamePaused) {
            nextTurn();
        }
    }

    public void pauseSimulation() {
        isGamePaused = true;
    }
    public void resumeSimulation() {
        isGamePaused = true;
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.startSimulation();
    }
}
