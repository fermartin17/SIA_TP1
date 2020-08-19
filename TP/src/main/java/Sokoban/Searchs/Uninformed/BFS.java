package Sokoban.Searchs.Uninformed;

import Sokoban.Interfaces.*;
import Sokoban.Model.Result;

import java.util.*;

public class BFS<T extends Neighbors<T> & Comparable<T> & Stateful<T>> implements SearchMethod<T> {

    public BFS(){ }

    public Result<T> search(T initialState) {
        if (initialState == null) return new Result<>(searchName(), false, 0, 0.0, 0, 0, null);
        //en vez de guardar los nodos, vamos guardando los paths
        Queue<Stack<T>> queue = new LinkedList<>();
        Set<T> visited = new HashSet<>();
        List<T> re = new ArrayList<>();
        List<T> neighbors;
        Stack<T> stack = new Stack<>();
        stack.push(initialState);
        queue.add(stack);
        int nodexExpanded = 0;
        while(!queue.isEmpty()){
            Stack<T> currPath = queue.poll();
            T aux = currPath.peek();
            nodexExpanded++;
            if(!visited.contains(aux)){
                visited.add(aux);
                if(aux.isDone()){
                    return  new Result<>(searchName(), true, currPath.size(),
                            (double) currPath.size(), nodexExpanded, queue.size(), currPath);
                }
                neighbors = aux.getNeighbors();
                for(T neighbor : neighbors){
                    if(!visited.contains(neighbor) && neighbor.isValid()){
                        currPath.push(neighbor);
                        queue.offer((Stack<T>) currPath.clone());
                        currPath.pop();
                    }
                }
            }
        }
        return new Result<>(searchName(), false, 0, 0, nodexExpanded, 0, null);
    }

    public String searchName() {return "BFS";}
}
