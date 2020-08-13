package Sokoban.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
@Setter
public class StatePackage implements Comparable<StatePackage> {

    private Queue<State> history;
    private State state;
    private double cost;

    public StatePackage(){}

    public StatePackage(Queue<State> states,State state,double cost){
        this.history = states;
        this.state = state;
        this.cost = cost;
    }

    @Override
    public int compareTo(StatePackage o) {
        return Double.valueOf(cost).compareTo(o.cost);
    }
}
