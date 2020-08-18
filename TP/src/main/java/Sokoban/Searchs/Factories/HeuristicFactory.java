package Sokoban.Searchs.Factories;

import Sokoban.Heuristics.EuclideanDistance;
import Sokoban.Heuristics.ManhattanDistance;
import Sokoban.Heuristics.MinMatching;
import Sokoban.Heuristics.PlayerBoxGoalDistance;
import Sokoban.Interfaces.Heuristic;
import Sokoban.Interfaces.SearchMethod;
import Sokoban.Model.Constants;
import Sokoban.Model.State;
import Sokoban.Searchs.Uninformed.BFS;
import Sokoban.Searchs.Uninformed.DFS;
import Sokoban.Searchs.Uninformed.IDDFS;
import Sokoban.Searchs.informed.GGS;
import Sokoban.Searchs.informed.IDA;

public class HeuristicFactory {

    public static Heuristic heuristic(String param){

        switch (param){
            case Constants.ConstantsValues.EuclideanDistance: return new EuclideanDistance();
            case Constants.ConstantsValues.ManhattanDistance: return new ManhattanDistance();
            case Constants.ConstantsValues.MinMatching: return new MinMatching();
            case Constants.ConstantsValues.PlayerBoxGoalDistance: return new PlayerBoxGoalDistance();
        }
        return null;
    }
}
