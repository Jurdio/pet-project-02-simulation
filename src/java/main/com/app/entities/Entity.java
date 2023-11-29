package main.com.app.entities;

import java.util.Objects;

public abstract class Entity {
    protected Point point;
    protected boolean isEaten;
    public Point getPoint() {
        return new Point(point.getX(),point.getY());
    }
    Entity(Point point){
        this.point = point;
    }
    public void setPoint(Point point) {
        this.point = point;
    }
    @Override
    public String toString(){
        return "G";
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        if (isEaten != entity.isEaten) return false;
        return Objects.equals(point, entity.point);
    }

    @Override
    public int hashCode() {
        int result = point != null ? point.hashCode() : 0;
        result = 31 * result + (isEaten ? 1 : 0);
        return result;
    }
}
