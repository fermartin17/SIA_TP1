package Sokoban.Searchs.Uninformed;

import Sokoban.Interfaces.*;
import Sokoban.Model.MapImpl;

import java.util.*;

public class BFS<T extends Neighbors<T> & Comparable<T> & Stateful<T>> implements UninformedSearch<T> {

    public BFS(){ }

    public List<T> search(T initialState) {
        if (initialState == null) return null;
        //en vez de guardar los nodos, vamos guardando los paths
        Queue<Stack<T>> queue = new LinkedList<>();
        Set<T> visited = new HashSet<>();
        List<T> ret = new ArrayList<>();
        List<T> neighbors;
        Stack<T> stack = new Stack<>();
        stack.push(initialState);
        queue.add(stack);
        while(!queue.isEmpty()){
            Stack<T> currPath = queue.poll();
            T aux = currPath.peek();
            if(!visited.contains(aux)){
                visited.add(aux);
                if(aux.isDone()){
                    ret.addAll(currPath);
                    return ret;
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
        return null;
    }

    public String searchName() {return "BFS";}
}
