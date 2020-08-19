package Sokoban.Searchs.informed;

import Sokoban.Interfaces.*;
import Sokoban.Model.Result;
import Sokoban.Model.StatePackage;

import java.util.*;

public class HPA<T extends Neighbors<T> & Comparable<T> & Stateful<T>> implements SearchMethod<T> {

    StatePackage<T> statePackage;
    double omega;
    Heuristic<T> heuristic;

    public HPA(double omega){
        this.omega = omega;
        this.heuristic = new Heuristic<>() {
            @Override
            public Double evaluate(T elem) {
                return 0.0;
            }
        };
    }

    public HPA(double omega, Heuristic<T> heuristic){
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

    public Result<T> search(T state){
        if(state == null) return new Result<>(searchName(), false, 0, 0.0, 0, 0, null);
        PriorityQueue<StatePackage<T>> queue;
        if(omega == 0){
             queue = new PriorityQueue<>((s1, s2) -> s1.compareTo(s2));
        }else if(omega == 0.5){
            queue = new PriorityQueue<>((sp1, sp2) -> {
                int cost = sp1.compareTo(sp2);
                int heur = sp1.compareTo(sp2, heuristic);
                if(cost + heur == 0) return heur;
                return cost + heur;
            });
        }else{
            queue = new PriorityQueue<>((s1, s2) -> s1.compareTo(s2, heuristic));
        }
        List<T> list = new LinkedList<>();
        Set<StatePackage<T>> visited = new HashSet<>();
        statePackage = new StatePackage<>(new LinkedList<>(), state,0);
        queue.offer(statePackage);
        int nodesExpanded = 0;
        while(!queue.isEmpty()){
            nodesExpanded++;
            StatePackage<T> aux = queue.poll();
            if(visited.contains(aux)) continue;
            visited.add(aux);
            aux.getHistory().add(aux.getCurrState());
            list = aux.getHistory();
            if(aux.getCurrState().isDone()){
                return new Result<>(searchName(), true, list.size(), list.size(), nodesExpanded, queue.size(), list);
            }
            for(T neighbor : aux.getCurrState().getNeighbors()){
                if(neighbor.isValid()) queue.offer(new StatePackage<>(new LinkedList<T>(list), neighbor,aux.getCost() + 1.0));
            }
        }
        return new Result<>(searchName(), false, 0, 0.0, 0, 0, null);
    }

}
