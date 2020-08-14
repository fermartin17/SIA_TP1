package Sokoban.Model;

import Sokoban.Interfaces.Heuristic;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
@Setter
public class StatePackage<T extends Comparable<T>> implements Comparable<StatePackage<T>> {

    private Queue<T> history;
    private T currState;
    private Double cost; //costo acumulado

    public StatePackage(){}

    public StatePackage(Queue<T> states,T currState, double cost){
        this.history = states;
        this.currState= currState;
        this.cost = cost;
    }

    public T getCurrState(){ return this.currState; }

    //default compareTo, comparar por el costo
    @Override
    public int compareTo(StatePackage<T> o) {
        return cost.compareTo(o.cost);
    }

    //comparar estados en base a una heurística
    public int compareTo(StatePackage<T> o, Heuristic<T> heuristic) {
        return heuristic.evaluate(currState).compareTo(heuristic.evaluate(o.currState));
    }

}
