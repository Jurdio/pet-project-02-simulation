package main.com.app.entities;

import java.util.Objects;

public abstract class Entity {
    protected Point point;
    protected boolean isEaten;
    Entity(Point point){
        this.point = point;
    }
    public Point getPoint() {
        return new Point(point.getX(),point.getY());
    }
    public void setPoint(Point point) {
        this.point = point;
    }
    @Override
    public String toString(){
        return "G";
    };
}
