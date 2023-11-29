package main.com.app.entities;

public abstract class Creature extends Entity{
    protected int speed;
    protected int healthPoint;

    Creature(Point point) {
        super(point);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Creature creature = (Creature) o;

        if (speed != creature.speed) return false;
        return healthPoint == creature.healthPoint;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + speed;
        result = 31 * result + healthPoint;
        return result;
    }

    public abstract void makeMove(WorldMap worldMap);
}
