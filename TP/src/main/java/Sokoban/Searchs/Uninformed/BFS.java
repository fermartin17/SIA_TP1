package Sokoban.Searchs.Uninformed;

import Sokoban.Interfaces.Heuristic;
import Sokoban.Interfaces.Neighbors;
import Sokoban.Interfaces.UninformedSearch;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeSet;

public class BFS<T extends Neighbors<T> & Comparable<T>> implements UninformedSearch<T> {

    List<Heuristic<T>> heuristics;
    Queue<T> queue;

    public BFS(List<Heuristic<T>> heuristic){
        this.heuristics = heuristic;
        this.queue = new LinkedList<>();
    }

    public T search(T root){
        if( root == null ) return null;
        TreeSet<T> visited = new TreeSet<>();
        queue.clear();
        queue.add(root);
        while(!queue.isEmpty()){
            T aux = queue.poll();
            if(!visited.contains(aux)){
                int passEvaluations = 0;
                visited.add(aux);
                for(Heuristic<T> h : heuristics){
                    if(!h.evaluate(aux)){
                        queue.addAll(aux.getNeighbors(aux));
                        break;
                    }else{
                        passEvaluations++;
                    }
                }
                if (passEvaluations == heuristics.size()) return aux;
            }
        }
        return null;
    }
}
