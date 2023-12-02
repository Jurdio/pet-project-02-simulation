package main.com.app.entities;

public class Tree extends Entity{
    public Tree(Point point) {
        super(point);
    }
    @Override
    public String toString(){
        return "\uD83C\uDF32"; //🌲
    }
}
