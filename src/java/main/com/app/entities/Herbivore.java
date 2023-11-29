package main.com.app.entities;

public class Herbivore extends Creature {

    public Herbivore(Point point) {
        super(point);
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
