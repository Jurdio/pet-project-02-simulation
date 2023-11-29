package main.com.app.entities;

public class Herbivore extends Creature{

    public Herbivore(Point point) {
        super(point);
    }

    @Override
    public String toString(){
        return "\uD83D\uDC07"; //🐇
    }



    @Override
    public void makeMove(WorldMap simulationWorldMap) {

    }

}
