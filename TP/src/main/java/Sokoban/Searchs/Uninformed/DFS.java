package Sokoban.Searchs.Uninformed;

import Sokoban.Interfaces.Neighbors;
import Sokoban.Interfaces.Stateful;
import Sokoban.Interfaces.UninformedSearch;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class DFS <T extends Neighbors<T> & Comparable<T> & Stateful<T>> implements UninformedSearch<T>{

    private Deque<T> stack;
    private Set<Integer> history;

    public DFS(){
        this.stack = new LinkedList<>();
        this.history = new HashSet<>();
    }

    @Override
    public List<T> search(T initial){
        if(initial == null) return null;
        searchDFS(initial);
        if(stack.isEmpty()) return null;
        List<T> ret = new ArrayList<>(stack);
        stack.clear();
        return ret;
    }

    private Boolean searchDFS(T state){
        if(state.isDone()){
           this.stack.push(state);
           return true;
        }
        if(!state.isValid() || this.history.contains(state.hashCode()))
            return false;

        this.history.add(state.hashCode());
        for(T neighbors : state.getNeighbors()) {
            if (searchDFS(neighbors)) {
                this.stack.push(state);
                return true;
            }
        }
        return false;
    }

    public String searchName() {return "DFS";}

}
