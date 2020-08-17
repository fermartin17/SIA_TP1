package Sokoban.Model;
import Sokoban.Interfaces.GameMap;
import Sokoban.Interfaces.Neighbors;
import Sokoban.Interfaces.Stateful;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class State implements Comparable<State>, Neighbors<State>, Stateful<State> {

    private MapImpl map;
    private GameMap.DIRECTION givenDirection;

    public State(){}

    public State(GameMap map, GameMap.DIRECTION givenDirection){
        this.map = (MapImpl) map;
        this.givenDirection = givenDirection;
    }

    @Override
    public int compareTo(State o) {
        return 0;
    }

    @Override
    public List<State> getNeighbors() {
        List<State> list = new ArrayList<>(4);
        //this.map.printMap();
        MapImpl mapUp = new MapImpl(this.map);
        MapImpl mapRight = new MapImpl(this.map);
        MapImpl mapDown = new MapImpl(this.map);
        MapImpl mapLeft = new MapImpl(this.map);
        if(mapUp.movePlayer(GameMap.DIRECTION.UP)) list.add(new State(mapUp, GameMap.DIRECTION.UP));
        if(mapRight.movePlayer(GameMap.DIRECTION.RIGHT)) list.add(new State(mapRight, GameMap.DIRECTION.RIGHT));
        if(mapDown.movePlayer(GameMap.DIRECTION.DOWN)) list.add(new State(mapDown, GameMap.DIRECTION.DOWN));
        if(mapLeft.movePlayer(GameMap.DIRECTION.LEFT)) list.add(new State(mapLeft, GameMap.DIRECTION.LEFT));
        //mapUp.printMap();
        //mapRight.printMap();
        //mapDown.printMap();
        //mapLeft.printMap();
        return list;
    }

    @Override
    public Boolean isDone() {
        return this.map.isWinner();
    }

    @Override
    public Boolean isValid() {
        return this.map.isValid();
    }

    @Override
    public int hashCode(){
        return this.map.hashCode();
    }

    public boolean equals(Object o){
        if(o == null || o.getClass() != this.getClass()) return false;
        if(o == this) return true;
        State s = (State) o;
        return map.equals(s.map);
    }

}
