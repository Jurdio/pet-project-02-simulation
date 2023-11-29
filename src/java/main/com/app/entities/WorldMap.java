package main.com.app.entities;

import java.util.*;

public class WorldMap {
    private HashMap<Point, Entity> map;
    private Set<Point> generatedPoints;
    private List<Predator> predators; // Додано список хижаків
    private List<Herbivore> herbivore;
    private Random random;
    private final int DEFAULT_SIZE = 10;
    private final int SIZE;

    public WorldMap() {
        SIZE = DEFAULT_SIZE;
        map = new HashMap<>(SIZE);
        random = new Random();
        generatedPoints = new HashSet<>();
        predators = new ArrayList<>(); // Ініціалізація списку хижаків
        herbivore = new ArrayList<>();
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
    }
    public HashMap<Point, Entity> getMap() {
        return map;
    }
    public void addEntityToMap(Entity entity) {
        map.put(entity.getPoint(), entity);
        generatedPoints.add(entity.getPoint());

        if (entity instanceof Predator) {
            predators.add((Predator) entity); // Додавання хижака до списку хижаків
        }
        if (entity instanceof Herbivore){
            herbivore.add((Herbivore) entity);
        }
    }
    public int getMapSize() {
        return SIZE;
    }
    public Entity getEntityByPoint(Point point) {
        return map.get(point);
    }
    public Point findNearestPrey(Point predatorPosition) {
        Point nearestPrey = null;
        double nearestDistance = Double.MAX_VALUE;
        for (Entity entity : getHerbivore()){
            Point preyPosition = entity.getPoint();
            double distance = calculateDistance(predatorPosition, preyPosition);
            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestPrey = preyPosition;
            }
        }
        return nearestPrey;
    }
    public void removeEntityByPoint(Point point) {
        Entity removedEntity = map.remove(point);
        if (removedEntity != null) {
            generatedPoints.remove(point);

            if (removedEntity instanceof Predator) {
                predators.remove(removedEntity); // Видалення хижака зі списку хижаків
            }
            if (removedEntity instanceof Herbivore) {
                herbivore.remove(removedEntity);
            }
        }
    }
    private double calculateDistance(Point p1, Point p2) {
        // Реалізуйте обчислення відстані між двома точками (наприклад, відстань Евкліда)
        return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
    }
    public List<Predator> getPredators() {
        // Повертаємо невибіркову копію списку хижаків
        return new ArrayList<>(predators);
    }
    public List<Herbivore> getHerbivore() {
        ArrayList<Herbivore> herbivoreArrayList = new ArrayList<>();
        for (Map.Entry<Point, Entity> entry : map.entrySet()) {
            Entity entity = entry.getValue();
            if (entity instanceof Herbivore){
                herbivoreArrayList.add((Herbivore) entity);
            }
        }
        return herbivoreArrayList;
    }
}
