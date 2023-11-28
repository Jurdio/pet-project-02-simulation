package main.com.app.simulation;

import main.com.app.entities.WorldMap;

public class Simulation {
    private WorldMap map;
    private Actions actions;
    private Renderer renderer;
    private int turnCount;

    public Simulation() {
        map = new WorldMap();
        actions = new Actions();
        renderer = new Renderer();
        turnCount = 0;

        actions.initActions(map);
    }

    public void nextTurn() {
        renderer.renderWorldMap(map);
        actions.turnActions(map);
        turnCount++;
    }

    public void startSimulation() {
        while (true) {
            nextTurn();
            // Додайте, можливо, затримку часу між ходами, щоб ви бачили результати.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void pauseSimulation() {
        // Зупинка симуляції, якщо потрібно
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.startSimulation();
    }
}
