package Sokoban.Searchs.Factories;

import Sokoban.Heuristics.EuclideanDistance;
import Sokoban.Heuristics.SimpleMinDistance;
import Sokoban.Heuristics.MinMatching;
import Sokoban.Heuristics.PlayerBoxGoalDistance;
import Sokoban.Interfaces.Heuristic;
import Sokoban.Model.Constants;

public class HeuristicFactory {

    public static Heuristic heuristic(String param){

        switch (param){
            case Constants.ConstantsValues.EuclideanDistance: return new EuclideanDistance();
            case Constants.ConstantsValues.ManhattanDistance: return new SimpleMinDistance();
            case Constants.ConstantsValues.MinMatching: return new MinMatching();
            case Constants.ConstantsValues.PlayerBoxGoalDistance: return new PlayerBoxGoalDistance();
        }
        return null;
    }
}