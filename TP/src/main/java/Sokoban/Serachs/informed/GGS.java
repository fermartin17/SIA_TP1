package Sokoban.Serachs.informed;

import Sokoban.Interfaces.Neighbors;
import Sokoban.Interfaces.UninformedSearch;
import Sokoban.Model.State;
import Sokoban.Model.StatePackage;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

@NoArgsConstructor
public class GGS<T extends Neighbors<T> & Comparable<T>> implements UninformedSearch<T> {

    StatePackage statePackage;

    public Queue<State> search(State state){
        this.statePackage = new StatePackage(new LinkedList<State>(),state,0);
        PriorityQueue<StatePackage> queue = new PriorityQueue<StatePackage>();
        Queue<State> list = new LinkedList<State>();

        while(!queue.isEmpty()){
            StatePackage aux = queue.poll();
            aux.getHistory().add(aux.getState());
            list = aux.getHistory();

            if(aux.getState().getMap().isWinner())
                return list;

            for(State neighbor : aux.getState().getNeighbors()){
                if(neighbor.getMap().checkMap()) {
                    queue.add(new StatePackage(list,neighbor,aux.getCost()));
                }
            }
        }
        return list;
    }

    @Override
    public Collection<T> search(T start, T goal) {
        return null;
    }


}
