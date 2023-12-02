package main.com.app.entities;

public class Herbivore extends Creature {
    public Herbivore(Point point, Class typeOfPrey) {
        super(point, typeOfPrey);
        super.healthPoint = 3;
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
