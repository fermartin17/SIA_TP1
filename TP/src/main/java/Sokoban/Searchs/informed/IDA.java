package Sokoban.Searchs.informed;

import Sokoban.Interfaces.Heuristic;
import Sokoban.Interfaces.Neighbors;
import Sokoban.Interfaces.SearchMethod;
import Sokoban.Interfaces.Stateful;
import Sokoban.Model.State;
import Sokoban.Model.StatePackage;

import java.util.*;

public class IDA<T extends Neighbors<T> & Comparable<T> & Stateful<T>> implements SearchMethod<T> {

    Heuristic<T> heuristic;
    Set<T> visited;

    public IDA(Heuristic<T> heuristic){
        this.heuristic = heuristic;
        visited = new HashSet<>();
    }

    @Override
    public Collection<T> search(T state) {
        double bound = heuristic.evaluate(state);
        StatePackage<T> statePackage = new StatePackage<>(new LinkedList<>(), state, 0);
        while(true){
            visited.add(state);
            statePackage.getHistory().add(state);
            StatePackage<T> aux = _search(statePackage, 0.0, bound);
            if(aux.getCost() == -1) return aux.getHistory();
            if(aux.getCost() == Double.MAX_VALUE) return null;
            bound += aux.getCost();
            visited.clear();
            statePackage.getHistory().clear();
        }
    }

    public StatePackage<T> _search(StatePackage<T> path, double cost, double bound){
        T currState = path.getCurrState();
        double f = cost + heuristic.evaluate(currState);
        if(f > bound) return new StatePackage<>(path.getHistory(), currState, f);
        if(currState.isDone()) {
            path.getHistory().add(currState);
            return new StatePackage<>(path.getHistory(), currState, -1); //found
        }
        double min = Double.MAX_VALUE;
        StatePackage<T> minState = new StatePackage<T>(new LinkedList<>(), null, Double.MAX_VALUE);
        for(T neighbor : currState.getNeighbors()){
            if(!neighbor.isValid() || visited.contains(neighbor)) continue;
            visited.add(neighbor);
            StatePackage<T> newPath = new StatePackage<T>(new LinkedList<>(path.getHistory()), neighbor, cost + 1);
            newPath.getHistory().add(neighbor);
            StatePackage<T> aux = _search(newPath, cost + 1, bound);
            newPath.getHistory().remove(neighbor);
            if(aux.getCost() == -1) return aux;
            if(aux.getCost() < min){
                min = aux.getCost();
                minState = aux;
            }
        }
        return minState;
    }

    @Override
    public String searchName() {
        return "IDA with heuristic " + heuristic.toString();
    }

}
