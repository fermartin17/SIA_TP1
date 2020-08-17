package Sokoban;

import Sokoban.Heuristics.EuclideanDistance;
import Sokoban.Heuristics.ManhattanDistance;
import Sokoban.Heuristics.MinMatching;
import Sokoban.Heuristics.PlayerBoxGoalDistance;
import Sokoban.Interfaces.GameMap;
import Sokoban.Interfaces.SearchMethod;
import Sokoban.Model.MapFactory;
import Sokoban.Model.State;
import Sokoban.Searchs.Uninformed.BFS;
import Sokoban.Searchs.Uninformed.DFS;
import Sokoban.Searchs.Uninformed.IDDFS;
import Sokoban.Searchs.informed.GGS;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Benchmark {

    MapFactory mapFactory;
    State initialState;
    List<SearchMethod<State>> searchMethods;
    List<Integer> levels;

    public Benchmark(){}

    public void setup(){
        levels = Arrays.asList(0, 1, 2, 3);
        mapFactory = new MapFactory();
        searchMethods = new ArrayList<>(3);
        searchMethods.add(new BFS<>());
        searchMethods.add(new DFS<>());
        searchMethods.add(new IDDFS<>(15));
        searchMethods = new ArrayList<>();
        searchMethods.add(new GGS<>(0));
        searchMethods.add(new GGS<>(0.5, new EuclideanDistance()));
        searchMethods.add(new GGS<>(0.5, new ManhattanDistance()));
        searchMethods.add(new GGS<>(0.5, new PlayerBoxGoalDistance()));
        searchMethods.add(new GGS<>(0.5, new MinMatching()));
        searchMethods.add(new GGS<>(1, new EuclideanDistance()));
        searchMethods.add(new GGS<>(1, new ManhattanDistance()));
        searchMethods.add(new GGS<>(1, new PlayerBoxGoalDistance()));
        searchMethods.add(new GGS<>(1, new MinMatching()));
    }

    public void setupInitialState(int level){
        GameMap gameMap = mapFactory.loadMap(0); //EASY MAP
        initialState = new State(gameMap, GameMap.DIRECTION.INITIAL);
    }

    public long measureTime(SearchMethod<State> method){
        long startTime = System.nanoTime();
        method.search(initialState);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public void runBenchmark(){
        setup();
        Map<Integer, List<Long>> totalResults = new TreeMap<>();
        List<Long> levelResults;
        for(Integer level : levels){
            setupInitialState(level);
            levelResults = new ArrayList<>(11);
            for(SearchMethod<State> method : searchMethods){
                levelResults.add(measureTime(method));
            }
            totalResults.put(level, levelResults);
        }
        saveResultsAsCSV(totalResults);
    }

    public void saveResultsAsCSV(Map<Integer, List<Long>> results){
        try {
            PrintWriter file = new PrintWriter("benchmarkResults.csv");
            for (Integer i : results.keySet()) {
                StringJoiner joiner = new StringJoiner(",");
                results.get(i).stream().map(String::valueOf).forEach(joiner::add);
                String collect =  joiner.toString();
                file.println(collect);
            }
            file.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }
}
