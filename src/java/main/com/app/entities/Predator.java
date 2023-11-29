package main.com.app.entities;

import main.com.app.simulation.AStarPathfinder;

import java.util.List;

public class Predator extends Creature {
    private int attackPower;

    public Predator(Point point, Class typeOfPrey) {
        super(point, typeOfPrey);
    }


    @Override
    public Class<? extends Entity> getTypeOfPrey() {
        return Herbivore.class;
    }

    @Override
    public String toString() {
        return "\uD83D\uDC3A"; // 🐺
    }
}
