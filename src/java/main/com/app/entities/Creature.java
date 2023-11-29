package main.com.app.entities;

import main.com.app.simulation.AStarPathfinder;
import java.util.List;

public abstract class Creature<T extends Entity> extends Entity {
    protected int speed;
    protected int healthPoint;

    Creature(Point point) {
        super(point);
    }

    public void makeMove(WorldMap worldMap) {
        AStarPathfinder pathfinder = new AStarPathfinder(worldMap);
        Class<? extends Entity> preyType = getTypeOfPrey();
        Point preyPosition = findNearestPrey(worldMap, this.point, preyType);
        if (preyPosition != null) {
            List<Point> path = pathfinder.findPath(this.point, new Point(preyPosition.getX(), preyPosition.getY()));

            // Якщо знайдено шлях, пересувати хижака
            if (path != null && !path.isEmpty()) {
                Point nextMove = path.get(1);

                // Перевірити, чи точка є позицією здобичі
                Entity prey = worldMap.getEntityByPoint(nextMove);
                if (prey != null && preyType.isAssignableFrom(prey.getClass())) {
                    worldMap.removeEntityByPoint(nextMove);
                    System.out.println("Predator ate Prey!");
                }

                // Перемістити хижака до обраної точки
                worldMap.updateEntityPosition(this.point, nextMove);

                // Оновити внутрішню позицію хижака
                this.point.setX(nextMove.getX());
                this.point.setY(nextMove.getY());
                System.out.println("End");
            }
        }
    }

    public abstract Class<? extends Entity> getTypeOfPrey();

    public Point findNearestPrey(WorldMap worldMap, Point currentPointPositionOfCreature, Class<? extends Entity> preyType) {
        Point nearestPrey = null;
        double nearestDistance = Double.MAX_VALUE;
        List<? extends Entity> entities = worldMap.getListOfEntities(preyType);
        for (Entity entity : entities) {
            Point preyPosition = entity.getPoint();
            double distance = worldMap.calculateDistance(currentPointPositionOfCreature, preyPosition);
            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestPrey = preyPosition;
            }
        }
        return nearestPrey;
    }
}
