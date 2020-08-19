package Sokoban.Searchs.Uninformed;

import Sokoban.Interfaces.Neighbors;
import Sokoban.Interfaces.Stateful;
import Sokoban.Interfaces.SearchMethod;
import Sokoban.Model.Result;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class DFS <T extends Neighbors<T> & Comparable<T> & Stateful<T>> implements SearchMethod<T> {

    private Deque<T> stack;
    private Set<T> history;
    private int totalNodesExpanded;
    private int nodesInFrontier;

    public DFS(){
        this.stack = new LinkedList<>();
        this.history = new HashSet<>();
        this.totalNodesExpanded = 0;
        this.nodesInFrontier = 0;
    }

    @Override
    public Result<T> search(T initial){
        if(initial == null) return new Result<>(searchName(), false, 0,0,totalNodesExpanded, 0, null);
        searchDFS(initial);
        if(stack.isEmpty()) return new Result<>(searchName(), false, 0,0,totalNodesExpanded, 0, null);
        stack.clear();
        return new Result<T>(searchName(), true, stack.size(), stack.size(), totalNodesExpanded, nodesInFrontier, (List<T>) stack);
    }

    private Boolean searchDFS(T state){
        totalNodesExpanded++;
        if(state.isDone()){
           this.stack.push(state);
           return true;
        }
        if(!state.isValid() || this.history.contains(state))
            return false;

        this.history.add(state);
        List<T> neighbors = state.getNeighbors();
        for(T neighbor : state.getNeighbors()) {
            nodesInFrontier = neighbors.size();
            if (searchDFS(neighbor)) {
                this.stack.push(state);
                return true;
            }
        }
        return false;
    }

    public String searchName() {return "DFS";}

}
