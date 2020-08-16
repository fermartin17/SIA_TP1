package Sokoban.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Position {
    private int x,y;

    public Position(){}
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double euclideanDistance(Position other){
        double deltaX = Math.abs(other.x - x);
        double deltaY = Math.abs(other.y - y);
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public double manhattanDistance(Position other){
        return Math.abs(other.x - x) + Math.abs(other.y - y);
    }

    public boolean equals(Object o){
        if(o == null || o.getClass() != this.getClass()) return false;
        if(o == this) return true;
        Position p = (Position) o;
        return this.x == p.x && this.y == p.y;
    }
}
