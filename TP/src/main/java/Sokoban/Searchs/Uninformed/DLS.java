package Sokoban.Searchs.Uninformed;

import Sokoban.Interfaces.Neighbors;
import Sokoban.Interfaces.UninformedSearch;
import Sokoban.Model.State;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class DLS <T extends Neighbors<T> & Comparable<T>> {

    private Deque<State> stack;
    private Set<Integer> history;

    public DLS(){
        this.stack = new ArrayDeque<State>();
        this.history = new HashSet<Integer>();
    }

    public Deque<State> search(State initial,int depth){
        searchDLS(initial,depth);
        return stack;
    }

    private Boolean searchDLS(State state,int depth){
        if(state.getMap().isWinner()){
            this.stack.push(state);
            return true;
        }
        if(depth == 0 || !state.getMap().checkMap() || this.history.contains(state.getMap().hashCode()))
            return false;

        this.history.add(state.getMap().hashCode());
        for(State neighbors : state.getNeighbors()) {
            if (searchDLS(neighbors,depth-1)) {
                this.stack.push(state);
                return true;
            }
        }
        return false;
    }
}
