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

    public BFS(List<Heuristic<T>> heuristic){
        this.heuristics = heuristic;
    }

    public T search(T root){
        if( root == null ) return null;
        Queue<T> queue = new LinkedList<>();
        TreeSet<T> visited = new TreeSet<>();
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
