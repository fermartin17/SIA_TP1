package Sokoban.Searchs.Uninformed;

import Sokoban.Interfaces.Neighbors;
import Sokoban.Interfaces.Stateful;
import Sokoban.Model.Result;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class DLS <T extends Neighbors<T> & Comparable<T> & Stateful<T>> {

    private Deque<T> stack;
    private Set<T> history;
    private int totalNodesExpanded;
    private int nodesInFrontier;

    public DLS(){
        this.stack = new LinkedList<>();
        this.history = new HashSet<>();
        this.totalNodesExpanded = 0;
        this.nodesInFrontier = 0;
    }

    public Result<T> search(T initial,int depth){
        this.history.clear();
        this.stack.clear();
        if(initial == null || depth < 0) return new Result<T>(searchName(), false, 0,0,totalNodesExpanded, nodesInFrontier, null);
        searchDLS(initial, depth);
        if(stack.isEmpty()){
            this.history.clear();
            this.stack.clear();
            this.nodesInFrontier = 0;
            this.totalNodesExpanded = 0;
            return new Result<T>(searchName(), false, 0,0,totalNodesExpanded, nodesInFrontier, null);
        }
        return new Result<T>(searchName(), true, stack.size(), stack.size(), totalNodesExpanded, nodesInFrontier, (List<T>)stack);
    }

    private Boolean searchDLS(T state, int depth){
        totalNodesExpanded++;
        if(state.isDone()){
            this.stack.push(state);
            return true;
        }

        this.nodesInFrontier--;
        if(depth == 0 || !state.isValid() || this.history.contains(state)) return false;
        this.history.add(state);
        List<T> neighbors = state.getNeighbors();
        for(T neighbor : neighbors) {
            this.nodesInFrontier += neighbors.size();
            if (searchDLS(neighbor,depth-1)) {
                this.stack.push(state);
                return true;
            }
        }
        return false;
    }

    public String searchName(){
        return "DLS";
    }

}
