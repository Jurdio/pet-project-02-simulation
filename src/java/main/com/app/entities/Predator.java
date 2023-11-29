package main.com.app.entities;

import main.com.app.simulation.AStarPathfinder;

import java.util.List;

public class Predator extends Creature {
    private int attackPower;
    public Predator() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Predator)) return false;

        Predator predator = (Predator) o;

        return attackPower == predator.attackPower;
    }

    @Override
    public int hashCode() {
        return attackPower;
    }

    @Override
    public void makeMove(WorldMap worldMap) {
        AStarPathfinder pathfinder = new AStarPathfinder(worldMap);
        Point preyPosition = worldMap.findNearestPrey(this.point);


        List<Point> path = pathfinder.findPath(this.point, new Point(1,2));//preyPosition);


        // Якщо знайдено шлях, пересувати хижака
        if (path != null && !path.isEmpty()) {
            Point nextMove = path.get(1); // Вибрати наступну точку шляху (перша точка - поточна позиція)

            // Перемістити хижака до обраної точки
            worldMap.updateEntityPosition(this.point, nextMove);

            // Оновити внутрішню позицію хижака
            this.point.setX(nextMove.getX());
            this.point.setY(nextMove.getY());
            System.out.println("End");
        }
    }

    @Override
    public String toString() {
        return "\uD83D\uDC3A"; // 🐺
    }
}
