package Sokoban.Model;
import Sokoban.Interfaces.Neighbors;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class State implements Comparable<State>, Neighbors<State> {
    private Set<Integer> history;
    private MapImpl map;

    public State(){}

    public State(Set<Integer> history, MapImpl map){
        this.history = history;
        this.map = map;
    }

    @Override
    public int compareTo(State o) {
        return 0;
    }

    @Override
    public List<State> getNeighbors() {
        List<State> list = new ArrayList<State>();

        Set<Integer> auxSet = this.history;
        auxSet.add(map.hashCode());

        Position pos = this.map.getPlayerPosition();
        MapImpl auxMap = this.map;

        auxMap.setPlayerPosition(new Position(pos.getX()+1, pos.getY()));
        list.add(new State(auxSet,auxMap));
        auxMap.setPlayerPosition(new Position(pos.getX()-1, pos.getY()));
        list.add(new State(auxSet,auxMap));
        auxMap.setPlayerPosition(new Position(pos.getX(), pos.getY()+1));
        list.add(new State(auxSet,auxMap));
        auxMap.setPlayerPosition(new Position(pos.getX(), pos.getY()-1));
        list.add(new State(auxSet,auxMap));

        return list;
    }
}
