package main.com.app.entities;

import java.util.List;

public class Herbivore extends Creature {
    public Herbivore(Point point, Class typeOfPrey) {
        super(point, typeOfPrey);
        super.healthPoint = 3;
    }

    @Override
    public void makeMove(WorldMap worldMap) {
        List<Point> path = pathToNearestPrey(worldMap);
        // Якщо знайдено шлях, пересувати хижака
        if (isValidPath(path)) {
            Point nextPoint = path.get(1);
            // Перевірити, чи точка є позицією здобичі
            Entity prey = worldMap.getEntityByPoint(nextPoint);
            makeAction(worldMap,prey,nextPoint);

        }
    }
    @Override
    public Class<? extends Entity> getTypeOfPrey() {
        return Grass.class;
    }

    @Override
    public void makeAction(WorldMap worldMap, Entity prey, Point nextPoint) {
        if (isValidPrey(prey)) {
            worldMap.removeEntityByPoint(nextPoint);
        }

        worldMap.updateEntityPosition(this.point, nextPoint);
    }

    @Override
    public String toString() {
        return "\uD83D\uDC07"; // 🐇
    }
}
