package main.com.app.simulation;

import main.com.app.entities.Entity;
import main.com.app.entities.Point;
import main.com.app.entities.WorldMap;


import java.util.*;
import java.util.List;

public class AStarPathfinder {
    private WorldMap worldMap;

    public AStarPathfinder(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public List<Point> findPath(Point start, Point goal) {
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

            for (Point neighbor : getNeighbors(current)) {
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

        // No path found
        return null;
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

    private List<Point> getNeighbors(Point point) {
        List<Point> neighbors = new ArrayList<>();

        // Перевірка всіх можливих сусідів (верх, низ, ліво, право)
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                // Пропустити точку саму на себе
                if (dx == 0 && dy == 0) {
                    continue;
                }

                int newX = point.getX() + dx;
                int newY = point.getY() + dy;

                Point neighbor = new Point(newX, newY);

                // Перевірка, чи сусід знаходиться всередині меж карты та чи він не є блокованим
                if (isValidNeighbor(neighbor)) {
                    neighbors.add(neighbor);
                }
            }
        }

        return neighbors;
    }

    private boolean isValidNeighbor(Point neighbor) {
        // Перевірка, чи точка знаходиться всередині меж карты
        int mapSize = worldMap.getMapSize();
        if (neighbor.getX() < 0 || neighbor.getX() >= mapSize || neighbor.getY() < 0 || neighbor.getY() >= mapSize) {
            return false;
        }

        // Перевірка, чи точка не є блокованою (наприклад, трава або інший об'єкт)
        Entity entity = worldMap.getEntityByPoint(neighbor);
        return entity == null;
    }

}
