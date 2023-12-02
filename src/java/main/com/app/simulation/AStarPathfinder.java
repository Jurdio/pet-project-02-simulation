package main.com.app.simulation;

import main.com.app.entities.Entity;
import main.com.app.entities.Point;
import main.com.app.entities.WorldMap;

import java.util.*;

public class AStarPathfinder {
    private WorldMap worldMap;
    public AStarPathfinder(WorldMap worldMap) {
        this.worldMap = worldMap;
    }
    public <T extends Entity> List<Point> findPath(Point start, Point goal, Class<T> targetType) {
        Set<Point> openSet = new HashSet<>();
        Set<Point> closedSet = new HashSet<>();
        Map<Point, Point> cameFrom = new HashMap<>();
        Map<Point, Integer> gScore = new HashMap<>();
        Map<Point, Integer> fScore = new HashMap<>();

        openSet.add(start);
        gScore.put(start, 0);
        fScore.put(start, heuristicCostEstimate(start, goal));

        while (!openSet.isEmpty()) {
            Point current = getLowestFScore(openSet, fScore);

            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }

            openSet.remove(current);
            closedSet.add(current);

            for (Point neighbor : getNeighbors(current, targetType)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeGScore = gScore.get(current) + distanceBetween(current, neighbor);

                if (!openSet.contains(neighbor) || tentativeGScore < gScore.get(neighbor)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, tentativeGScore + heuristicCostEstimate(neighbor, goal));

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }
        return Collections.emptyList();
    }
    private Point getLowestFScore(Set<Point> openSet, Map<Point, Integer> fScore) {
        return openSet.stream().min(Comparator.comparingInt(fScore::get)).orElse(null);
    }
    private List<Point> reconstructPath(Map<Point, Point> cameFrom, Point current) {
        List<Point> path = new ArrayList<>();
        path.add(current);

        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(0, current);
        }

        return path;
    }
    private int heuristicCostEstimate(Point start, Point goal) {
        return Math.abs(goal.getX() - start.getX()) + Math.abs(goal.getY() - start.getY());
    }
    private int distanceBetween(Point a, Point b) {
        return Math.abs(b.getX() - a.getX()) + Math.abs(b.getY() - a.getY());
    }
    private <T extends Entity> List<Point> getNeighbors(Point point, Class<T> targetType) {
        List<Point> neighbors = new ArrayList<>();

        // Check all possible neighbors (up, down, left, right)
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                // Skip the point itself
                if (dx == 0 && dy == 0) {
                    continue;
                }

                int newX = point.getX() + dx;
                int newY = point.getY() + dy;

                Point neighbor = new Point(newX, newY);

                // Check if the neighbor is valid based on the WorldMap and the target type
                if (isValidNeighbor(neighbor, targetType)) {
                    neighbors.add(neighbor);
                }
            }
        }

        return neighbors;
    }
    private <T extends Entity> boolean isValidNeighbor(Point neighbor, Class<T> targetType) {
        // Check if the point is within the bounds of the map
        int mapSize = worldMap.getMapSize();
        if (neighbor.getX() < 0 || neighbor.getX() >= mapSize || neighbor.getY() < 0 || neighbor.getY() >= mapSize) {
            return false;
        }

        // Check if the point is not blocked by another object
        Entity entity = worldMap.getEntityByPoint(neighbor);
        return entity == null || targetType.isAssignableFrom(entity.getClass());
    }
}
