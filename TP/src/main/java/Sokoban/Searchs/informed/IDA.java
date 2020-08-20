package Sokoban.Searchs.informed;

import Sokoban.Interfaces.Heuristic;
import Sokoban.Interfaces.Neighbors;
import Sokoban.Interfaces.SearchMethod;
import Sokoban.Interfaces.Stateful;
import Sokoban.Model.Result;
import Sokoban.Model.State;
import Sokoban.Model.StatePackage;

import java.util.*;

public class IDA<T extends Neighbors<T> & Comparable<T> & Stateful<T>> implements SearchMethod<T> {

    Heuristic<T> heuristic;
    Set<T> visited;
    private int totalNodesExpanded;
    private int nodesInFrontier;

    public IDA(Heuristic<T> heuristic){
        this.heuristic = heuristic;
        visited = new HashSet<>();
        this.totalNodesExpanded = 0;
        this.nodesInFrontier = 0;
    }

    @Override
    public Result<T> search(T state) {
        if(state == null) return new Result<>(searchName(), false, 0, 0.0,
                0, 0, null);
        double bound = heuristic.evaluate(state);
        StatePackage<T> statePackage = new StatePackage<>(new LinkedList<>(), state, 0);
        while(true){
            visited.add(state);
            statePackage.getHistory().add(state);
            StatePackage<T> aux = _search(statePackage, 0.0, bound);
            if(aux.getCost() == -1){ //found
                return new Result<>(searchName(), true, aux.getHistory().size(), aux.getHistory().size(),
                                    totalNodesExpanded, nodesInFrontier, aux.getHistory());
            }
            if(aux.getCost() == Double.MAX_VALUE) return new Result<>(searchName(), false, 0, 0.0,
                    0, 0, null);
            bound += aux.getCost();
            visited.clear();
            statePackage.getHistory().clear();
        }
    }

    public StatePackage<T> _search(StatePackage<T> path, double cost, double bound){
        totalNodesExpanded++;
        T currState = path.getCurrState();
        double f = cost + heuristic.evaluate(currState);
        if(f > bound) return new StatePackage<>(path.getHistory(), currState, f);
        if(currState.isDone()) {
            path.getHistory().add(currState);
            return new StatePackage<>(path.getHistory(), currState, -1); //found
        }

        this.nodesInFrontier--;
        double min = Double.MAX_VALUE;
        StatePackage<T> minState = new StatePackage<T>(new LinkedList<>(), null, Double.MAX_VALUE);
        List<T> neighbors = currState.getNeighbors();
        nodesInFrontier += neighbors.size();
        for(T neighbor : neighbors){
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
