package main.com.app.entities;

import java.util.*;

public class WorldMap {
    private HashMap<Point, Entity> map;
    private Set<Point> generatedPoints;
    private Random random;
    private final int DEFAULT_SIZE = 10;
    private final int SIZE;
    public WorldMap() {
        SIZE = DEFAULT_SIZE;
        map = new HashMap<>(SIZE);
        random = new Random();
        generatedPoints = new HashSet<>();
    }
    public Point getRandomPoint() {
        Point randomPoint;
        do {
            int x = random.nextInt(SIZE);
            int y = random.nextInt(SIZE);
            randomPoint = new Point(x, y);
        } while (!generatedPoints.add(randomPoint) && generatedPoints.size() < 100); // Повторюємо, доки точка не додається до множини
        return randomPoint;
    }
    public void updateEntityPosition(Point oldPosition, Point newPosition) {
        Entity entity = map.remove(oldPosition);
        map.put(newPosition, entity);
        entity.setPoint(newPosition);
    }
    public void addEntityToMap(Entity entity) {
        map.put(entity.getPoint(), entity);
        generatedPoints.add(entity.getPoint());
    }
    public int getMapSize() {
        return SIZE;
    }
    public Entity getEntityByPoint(Point point) {
        return map.get(point);
    }
    public void removeEntityByPoint(Point point) {
        Entity removedEntity = map.remove(point);
        if (removedEntity != null) {
            generatedPoints.remove(point);
        }
    }
    public List<? extends Entity> getListOfEntities(Class<? extends Entity> entityType) {
        ArrayList<Entity> entityList = new ArrayList<>();
        for (Map.Entry<Point, Entity> entry : map.entrySet()) {
            if (entityType.isInstance(entry.getValue())) {
                entityList.add(entry.getValue());
            }
        }
        return entityList;
    }
}
