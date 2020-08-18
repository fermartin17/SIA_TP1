package Sokoban.Searchs.informed;

import Sokoban.Interfaces.Heuristic;
import Sokoban.Interfaces.Neighbors;
import Sokoban.Interfaces.SearchMethod;
import Sokoban.Interfaces.Stateful;
import Sokoban.Model.StatePackage;

import java.util.*;

public class IDA <T extends Neighbors<T> & Comparable<T> & Stateful<T>> implements SearchMethod<T> {
     StatePackage<T> statePackage;
     double omega;
     Heuristic<T> heuristic;


    public IDA(double omega, Heuristic<T> heuristic){
        this.omega = omega;
        this.heuristic = heuristic= new Heuristic<>() {
            @Override
            public Double evaluate(T elem) {
                return 0.0;
            }
        };;
    }

    @Override
    public String searchName() {
        return "Iterative Deepening A* using heuristic " + this.heuristic.toString();
    }

    public List<T> search(T state){
        PriorityQueue<StatePackage<T>> queue = new PriorityQueue<>(
                (s1, s2) -> (int) ( (1 - omega) * (s1.compareTo(s2)) + omega * s1.compareTo(s2, heuristic))
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
                if(neighbor.isValid()) queue.offer(new StatePackage<>(list, neighbor,aux.getCost() + 1.0));
            }
        }
        return list;
    }
}
