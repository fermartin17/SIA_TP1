package Sokoban.Model;

import Sokoban.Interfaces.Heuristic;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StatePackage<T extends Comparable<T>> implements Comparable<StatePackage<T>> {

    private List<T> history;
    private T currState;
    private Double cost; //costo acumulado

    public StatePackage(){}

    public StatePackage(List<T> states, T currState, double cost){
        this.history = states;
        this.currState= currState;
        this.cost = cost;
    }

    //default compareTo, comparar por el costo
    @Override
    public int compareTo(StatePackage<T> o) {
        return cost.compareTo(o.cost);
    }

    //comparar estados en base a una heurística
    public int compareTo(StatePackage<T> o, Heuristic<T> heuristic) {
        return heuristic.evaluate(currState).compareTo(heuristic.evaluate(o.currState));
    }

    @Override
    public int hashCode(){
        return this.currState.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if(o == null || o.getClass() != this.getClass()) return false;
        if(o == this) return true;
        StatePackage<T> s = (StatePackage<T>) o;
        return this.currState.equals(s.currState);
    }

}
