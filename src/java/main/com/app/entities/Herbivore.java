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
            if (isValidPrey(prey)) {
                worldMap.removeEntityByPoint(nextPoint);
            } else {
                // Перемістити хижака до обраної точки
                worldMap.updateEntityPosition(this.point, nextPoint);
                // Оновити внутрішню позицію хижака
                this.point = nextPoint;
            }

        }
    }
    @Override
    public Class<? extends Entity> getTypeOfPrey() {
        return Grass.class;
    }
    @Override
    public String toString() {
        return "\uD83D\uDC07"; // 🐇
    }
}
