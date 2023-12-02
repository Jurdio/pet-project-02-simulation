package main.com.app.entities;

import java.util.List;

public class Predator extends Creature {
    private final int attackPower;

    public Predator(Point point, Class typeOfPrey) {
        super(point, typeOfPrey);
        attackPower = 1;
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
                System.out.println("Тицьнув травоїдного!");
                ((Creature) prey).setHealthPoint(((Creature) prey).getHealthPoint() - attackPower);
                if (((Creature) prey).getHealthPoint() <= 0){
                    worldMap.removeEntityByPoint(nextPoint);
                    worldMap.updateEntityPosition(this.point, nextPoint);
                    System.out.println("Вбито травоїдного!");
                }
            } else {
                System.out.println("Я не біля цілі, йду далі");
                worldMap.updateEntityPosition(this.point, nextPoint);
            }
        } else {
            System.out.println("Я вже всіх зїв, ням!");
        }
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
