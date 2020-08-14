package Sokoban.Searchs.informed;

import Sokoban.Interfaces.Heuristic;
import Sokoban.Interfaces.Neighbors;
import Sokoban.Interfaces.Stateful;
import Sokoban.Interfaces.UninformedSearch;
import Sokoban.Model.State;
import Sokoban.Model.StatePackage;
import lombok.NoArgsConstructor;

import java.util.*;

public class GGS<T extends Neighbors<T> & Comparable<T> & Stateful<T>> {

    StatePackage<T> statePackage;
    int omega;
    Heuristic<T> heuristic;

    public GGS(int omega, Heuristic<T> heuristic){
        this.omega = omega;
        this.heuristic = heuristic;
    }

    public Queue<T> search(T state){
        PriorityQueue<StatePackage<T>> queue = new PriorityQueue<>(
                (s1, s2) -> (1 - omega) * (s1.compareTo(s2)) + omega * s1.compareTo(s2, heuristic)
        );
        Queue<T> list = new LinkedList<>();
        statePackage = new StatePackage<>(new LinkedList<T>(),state,0);
        queue.offer(statePackage);
        while(!queue.isEmpty()){
            StatePackage<T> aux = queue.poll();
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
