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
        List<State> list = new ArrayList<State>();

        Position pos = this.map.getPlayerPosition();
        MapImpl auxMap = this.map;

        auxMap.setPlayerPosition(new Position(pos.getX()+1, pos.getY()));
        list.add(new State(auxMap));
        auxMap.setPlayerPosition(new Position(pos.getX()-1, pos.getY()));
        list.add(new State(auxMap));
        auxMap.setPlayerPosition(new Position(pos.getX(), pos.getY()+1));
        list.add(new State(auxMap));
        auxMap.setPlayerPosition(new Position(pos.getX(), pos.getY()-1));
        list.add(new State(auxMap));

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
