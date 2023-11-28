package main.com.app.entities;


import main.com.app.simulation.AStarPathfinder;

import java.util.List;


public class Predator extends Creature {
    private int attackPower;
    public Predator(){
        super();
    }
    @Override
    public void makeMove(WorldMap worldMap) {
        AStarPathfinder pathfinder = new AStarPathfinder(worldMap);
        Point preyPosition = new Point(1,1); // Знаходження найближчого травоїдного
        System.out.println("Виконуємо хід хижаком");

        if (preyPosition != null) {
            System.out.println("Початок алгоритм");
            List<Point> path = pathfinder.findPath(this.point, preyPosition);

            // Вивести шлях
            System.out.println("Шлях: " + path);

            // Якщо знайдено шлях, пересувати хижака
            if (path != null && !path.isEmpty()) {
                Point nextMove = path.get(1); // Вибрати наступну точку шляху (перша точка - поточна позиція)

                // Перемістити хижака до обраної точки
                worldMap.updateEntityPosition(this.point, nextMove);

                // Оновити внутрішню позицію хижака
                this.point.setX(nextMove.getX());
                this.point.setY(nextMove.getY());
                System.out.println("Кінець");
            } else {
                System.out.println("Шлях не знайдено");
            }
        }
    }


    @Override
    public String toString() {
        return "\uD83D\uDC3A"; // 🐺
    }
}
