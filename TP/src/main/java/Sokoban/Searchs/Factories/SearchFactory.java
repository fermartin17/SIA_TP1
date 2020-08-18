package Sokoban.Searchs.Factories;


import Sokoban.Interfaces.SearchMethod;
import Sokoban.Model.Constants;
import Sokoban.Model.State;
import Sokoban.Model.StatePackage;
import Sokoban.Searchs.Uninformed.BFS;
import Sokoban.Searchs.Uninformed.DFS;
import Sokoban.Searchs.Uninformed.IDDFS;
import Sokoban.Searchs.informed.GGS;
import Sokoban.Searchs.informed.IDA;

public class SearchFactory {

    private SearchFactory(){}

    public static SearchMethod searchMethod(String[] params){

        switch (params[0]){
            case Constants.ConstantsValues.DFS: return new DFS<State>();
            case Constants.ConstantsValues.BFS: return new BFS<State>();
            case Constants.ConstantsValues.IDDFS: return new IDDFS<State>(50);
            case Constants.ConstantsValues.GGS: return new GGS<State>(1,HeuristicFactory.heuristic(params[1]));
            case Constants.ConstantsValues.A: return new GGS<State>(0.5,HeuristicFactory.heuristic(params[1]));
            case Constants.ConstantsValues.IDA: return new IDA<State>(HeuristicFactory.heuristic(params[1]));
        }

        return  null;
    }
}