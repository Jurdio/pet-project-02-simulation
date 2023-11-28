package main.com.app.entities;

public abstract class Entity {
    protected Point point;
    protected boolean isEaten;
    public Point getPoint() {
        return point;
    }
    Entity(){
        point = new Point();
    }

    public void setPoint(Point point) {
        this.point = point;
    }
    @Override
    public String toString(){
        return "G";
    };
}
