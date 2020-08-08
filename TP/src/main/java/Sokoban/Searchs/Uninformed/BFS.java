package Sokoban.Searchs.Uninformed;

import Sokoban.Interfaces.Heuristic;
import Sokoban.Interfaces.Neighbors;
import Sokoban.Interfaces.UninformedSearch;

import java.util.*;

public class BFS<T extends Neighbors<T> & Comparable<T>> implements UninformedSearch<T> {

    Heuristic<T> heuristic;

    public BFS(){
        this.heuristic = new Heuristic<>() {
            @Override
            public boolean evaluate(T elem) {
                return true;
            }
        };
    }

    public BFS(Heuristic<T> heuristic){
        this.heuristic = heuristic;
    }

    public List<T> search(T root, T goal) {
        if (root == null && goal == null) return null;
        //en vez de guardar los nodos, vamos guardando los paths
        Queue<Stack<T>> queue = new LinkedList<>();
        TreeSet<T> visited = new TreeSet<>();
        List<T> ret = new ArrayList<>();
        List<T> neighbors = null;
        Stack<T> stack = new Stack<>();
        stack.push(root);
        queue.add(stack);
        while(!queue.isEmpty()){
            Stack<T> currPath = queue.poll();
            T aux = currPath.peek();
            if(!visited.contains(aux)){
                visited.add(aux);
                if(aux.equals(goal)){
                    ret.addAll(currPath);
                    return ret;
                }
                if(heuristic.evaluate(aux)){
                    neighbors = aux.getNeighbors();
                    for(T neighbor : neighbors){
                        if(!visited.contains(neighbor)){
                            currPath.push(neighbor);
                            queue.offer((Stack<T>)currPath.clone());
                            currPath.pop();
                        }
                    }

                }
            }
        }
        return null;
    }
}
