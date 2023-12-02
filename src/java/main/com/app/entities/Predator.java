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

        if (isValidPath(path)) {
            Point nextPoint = path.get(1);
            Entity prey = worldMap.getEntityByPoint(nextPoint);

            makeAction(worldMap,prey,nextPoint);
        }
    }

    @Override
    public Class<? extends Entity> getTypeOfPrey() {
        return Herbivore.class;
    }
    private void attackPrey(Entity prey){
        ((Creature) prey).setHealthPoint(((Creature) prey).getHealthPoint() - attackPower);
    }
    @Override
    public void makeAction(WorldMap worldMap, Entity prey, Point nextPoint) {
        if (isValidPrey(prey)) {
            attackPrey(prey);

            if (((Creature) prey).getHealthPoint() <= 0){
                worldMap.removeEntityByPoint(nextPoint);
                worldMap.updateEntityPosition(this.point, nextPoint);
                System.out.println("Вбито травоїдного!");
            }
        } else {
            worldMap.updateEntityPosition(this.point, nextPoint);
        }
    }

    @Override
    public String toString() {
        return "\uD83D\uDC3A"; // 🐺
    }
}
