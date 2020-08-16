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

    public State(){}

    public State(GameMap map){
        this.map = (MapImpl) map;
    }

    @Override
    public int compareTo(State o) {
        return 0;
    }

    @Override
    public List<State> getNeighbors() {
        List<State> list = new ArrayList<>();
        MapImpl mapUp = new MapImpl(this.map);
        MapImpl mapRight = new MapImpl(this.map);
        MapImpl mapDown = new MapImpl(this.map);
        MapImpl mapLeft = new MapImpl(this.map);
        mapUp.movePlayer(GameMap.DIRECTION.UP);
        mapRight.movePlayer(GameMap.DIRECTION.RIGHT);
        mapDown.movePlayer(GameMap.DIRECTION.DOWN);
        mapLeft.movePlayer(GameMap.DIRECTION.LEFT);
        list.add(new State(mapUp));
        list.add(new State(mapRight));
        list.add(new State(mapDown));
        list.add(new State(mapLeft));
        return list;
    }

    public int hashcode(){
        return this.map.hashCode();
    }

    @Override
    public Boolean isDone() {
        return this.map.isWinner();
    }

    @Override
    public Boolean isValid() {
        return this.map.isValid();
    }
}
