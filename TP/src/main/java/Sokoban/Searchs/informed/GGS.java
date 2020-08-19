package Sokoban.Searchs.informed;

import Sokoban.Interfaces.*;
import Sokoban.Model.StatePackage;

import java.util.*;

public class GGS<T extends Neighbors<T> & Comparable<T> & Stateful<T>> implements SearchMethod<T> {

    StatePackage<T> statePackage;
    double omega;
    Heuristic<T> heuristic;

    public GGS(double omega){
        this.omega = omega;
        this.heuristic = new Heuristic<>() {
            @Override
            public Double evaluate(T elem) {
                return 0.0;
            }
        };
    }

    public GGS(double omega, Heuristic<T> heuristic){
        this.omega = omega;
        this.heuristic = heuristic;
    }


    @Override
    public String searchName() {
        if(omega == 0) return "Global greedy";
        if(omega == 0.5) return "A* using heuristic " + heuristic.toString();
        if(omega == 1) return "using only heuristic " + heuristic.toString();
        return "";
    }

    public List<T> search(T state){
        PriorityQueue<StatePackage<T>> queue = new PriorityQueue<>(
                (s1, s2) ->  (int) (2 * (  (1 - omega) * (s1.compareTo(s2)) +  omega * s1.compareTo(s2, heuristic)))
        );
        List<T> list = new LinkedList<>();
        Set<StatePackage<T>> visited = new HashSet<>();
        statePackage = new StatePackage<>(new LinkedList<>(), state,0);
        queue.offer(statePackage);
        while(!queue.isEmpty()){
            StatePackage<T> aux = queue.poll();
            if(visited.contains(aux)) continue;
            visited.add(aux);
            aux.getHistory().add(aux.getCurrState());
            list = aux.getHistory();
            if(aux.getCurrState().isDone()) return list;
            for(T neighbor : aux.getCurrState().getNeighbors()){
                if(neighbor.isValid()) queue.offer(new StatePackage<>(new LinkedList<T>(list), neighbor,aux.getCost() + 1.0));
            }
        }
        return list;
    }

}
