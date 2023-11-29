package main.com.app.entities;

public class Tree extends Entity{
    Tree(Point point) {
        super(point);
    }

    @Override
    public String toString(){
        return "\uD83C\uDF32"; //🌲
    }
}
