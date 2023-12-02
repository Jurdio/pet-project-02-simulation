package main.com.app.entities;

import main.com.app.simulation.AStarPathfinder;

import java.util.ArrayList;
import java.util.List;

public abstract class Creature<T extends Entity> extends Entity {
    protected int speed;
    protected int healthPoint;
    protected boolean hasNextPrey;
    protected Class<T> typeOfPrey;
    Creature(Point point, Class<T> typeOfPrey) {
        super(point);
        this.typeOfPrey = typeOfPrey;
        hasNextPrey = true;
    }
    public void makeMove(WorldMap worldMap) {
        List<Point> path = pathToNearestPrey(worldMap);

        if (isValidPath(path)) {
            Point nextPoint = path.get(1);
            Entity prey = worldMap.getEntityByPoint(nextPoint);

            makeAction(worldMap,prey,nextPoint);
        } else {
            hasNextPrey = false;
        }
    }

    public boolean isHasNextPrey() {
        return hasNextPrey;
    }

    public void setHealthPoint(int healthPoint){
        this.healthPoint = healthPoint;
    }
    public int getHealthPoint(){
        return healthPoint;
    }
    public abstract Class<? extends Entity> getTypeOfPrey();
    public abstract void makeAction(WorldMap map, Entity prey, Point nextPoint);
    boolean isValidPath(List<Point> path){
        return path != null && !path.isEmpty();
    }
    boolean isValidPrey(Entity entity){
        return entity != null && this.typeOfPrey.isAssignableFrom(entity.getClass());
    }
    public List<Point> pathToNearestPrey(WorldMap worldMap) {
        List<Point> path = new ArrayList<>();
        
        AStarPathfinder pathfinder = new AStarPathfinder(worldMap);
        Point preyPosition = findNearestPrey(worldMap, this.point, this.typeOfPrey);
        
        if (preyPosition != null) {
            path = pathfinder.findPath(this.point, new Point(preyPosition.getX(), preyPosition.getY()), this.typeOfPrey);
        } 
        
        return path;
    }
    public Point findNearestPrey(WorldMap worldMap, Point currentPointPositionOfCreature, Class<? extends Entity> preyType) {
        Point nearestPrey = null;
        double nearestDistance = Double.MAX_VALUE;
        List<? extends Entity> entities = worldMap.getListOfEntities(preyType);
        for (Entity entity : entities) {
            Point preyPosition = entity.getPoint();
            double distance = point.calculateDistance(currentPointPositionOfCreature, preyPosition);
            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestPrey = preyPosition;
            }
        }
        return nearestPrey;
    }
}
