package main.com.app.entities;

public abstract class Creature extends Entity{
    protected int speed;
    protected int healthPoint;
    public abstract void makeMove(WorldMap worldMap);
}
