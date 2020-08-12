package Sokoban.Searchs.Uninformed;

import Sokoban.Interfaces.Neighbors;
import Sokoban.Interfaces.UninformedSearch;
import Sokoban.Model.State;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

@Getter
@Setter
public class DFS <T extends Neighbors<T> & Comparable<T>> {

    private Deque<State> stack;

    public DFS(){
        this.stack = new ArrayDeque<State>();
    }

    public Deque<State> search(State initial){
        searchDFS(initial);
        return stack;
    }

    private Boolean searchDFS(State state){
        if(state.getMap().isWinner()){
           this.stack.push(state);
           return true;
        }
        if(!state.getMap().checkMap() || state.getHistory().contains(state.getMap().hashCode()))
            return false;
        for(State neighbors : state.getNeighbors()) {
            if (searchDFS(neighbors)) {
                this.stack.push(state);
                return true;
            }
        }
        return false;
    }
}
