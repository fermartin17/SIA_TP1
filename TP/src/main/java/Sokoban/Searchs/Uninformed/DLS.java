package Sokoban.Searchs.Uninformed;

import Sokoban.Interfaces.Neighbors;
import Sokoban.Interfaces.Stateful;
import Sokoban.Interfaces.UninformedSearch;
import Sokoban.Model.State;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class DLS <T extends Neighbors<T> & Comparable<T> & Stateful<T>> {

    private Deque<T> stack;
    private Set<T> history;

    public DLS(){
        this.stack = new LinkedList<>();
        this.history = new HashSet<T>();
    }

    public List<T> search(T initial,int depth){
        if(initial == null || depth < 0) return null;
        searchDLS(initial, depth);
        if(stack.isEmpty()){
            history.clear();
            stack.clear();
            return null;
        }
        List<T> ret = new ArrayList<>(stack);
        history.clear();
        stack.clear();
        return ret;
    }

    private Boolean searchDLS(T state, int depth){
        if(state.isDone()){
            this.stack.push(state);
            return true;
        }
        if(depth == 0 || !state.isValid() || this.history.contains(state)) return false;
        this.history.add(state);
        for(T neighbors : state.getNeighbors()) {
            if (searchDLS(neighbors,depth-1)) {
                this.stack.push(state);
                return true;
            }
        }
        return false;
    }

    //private Boolean searchDLS(T state,int depth){
    //    if(state.isDone()){
    //        this.stack.push(state);
    //        return true;
    //    }
    //    if(depth == 0 || !state.isValid() || this.history.contains(state))
    //        return false;
    //    this.history.add(state);
    //    for(T neighbors : state.getNeighbors()) {
    //        if (searchDLS(neighbors,depth-1)) {
    //            this.stack.push(state);
    //            return true;
    //        }
    //    }
    //    return false;
    //}

}
