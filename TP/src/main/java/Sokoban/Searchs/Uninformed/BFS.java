package Sokoban.Searchs.Uninformed;

import Sokoban.Interfaces.Heuristic;
import Sokoban.Interfaces.Neighbors;
import Sokoban.Interfaces.UninformedSearch;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class BFS<T extends Neighbors<T>> implements UninformedSearch<T> {

    Heuristic<T>[] heuristics;
    Queue<T> queue;

    public BFS(Heuristic<T>[] heuristic){
        this.heuristics = heuristic;
        this.queue = new LinkedList<>();
    }

    public T search(T root){
        TreeSet<T> visited = new TreeSet<>();
        queue.add(root);
        visited.add(root);
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
                if (passEvaluations == heuristics.length) return aux;
            }
        }
        return null;
    }
}
