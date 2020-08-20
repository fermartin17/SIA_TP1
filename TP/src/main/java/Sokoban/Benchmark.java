package Sokoban;

import Sokoban.Heuristics.EuclideanDistance;
import Sokoban.Heuristics.SimpleMinDistance;
import Sokoban.Heuristics.MinMatching;
import Sokoban.Heuristics.PlayerBoxGoalDistance;
import Sokoban.Interfaces.GameMap;
import Sokoban.Interfaces.SearchMethod;
import Sokoban.Model.MapFactory;
import Sokoban.Model.Result;
import Sokoban.Model.State;
import Sokoban.Searchs.Uninformed.BFS;
import Sokoban.Searchs.Uninformed.DFS;
import Sokoban.Searchs.Uninformed.IDDFS;
import Sokoban.Searchs.informed.HPA;
import Sokoban.Searchs.informed.IDA;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Benchmark {

    MapFactory mapFactory;
    State initialState;
    List<SearchMethod<State>> searchMethods;
    List<Integer> levels;

    public Benchmark(){}

    public void setup(){
        levels = Arrays.asList(0, 1, 2, 3, 4, 5);
        mapFactory = new MapFactory();
        searchMethods = new ArrayList<>();
        searchMethods.add(new BFS<>());
        searchMethods.add(new DFS<>());
        searchMethods.add(new IDDFS<>(15));
        searchMethods.add(new HPA<>(0));
        searchMethods.add(new HPA<>(0.5, new SimpleMinDistance()));
        searchMethods.add(new HPA<>(0.5, new PlayerBoxGoalDistance()));
        searchMethods.add(new HPA<>(0.5, new MinMatching()));
        searchMethods.add(new HPA<>(1, new SimpleMinDistance()));
        searchMethods.add(new HPA<>(1, new PlayerBoxGoalDistance()));
        searchMethods.add(new HPA<>(1, new MinMatching()));
        searchMethods.add(new IDA<>(new EuclideanDistance()));
        searchMethods.add(new IDA<>(new SimpleMinDistance()));
        searchMethods.add(new IDA<>(new PlayerBoxGoalDistance()));
        searchMethods.add(new IDA<>(new MinMatching()));
    }

    public void setupInitialState(int level){
        GameMap gameMap = mapFactory.loadMap(level);
        initialState = new State(gameMap, GameMap.DIRECTION.INITIAL);
    }

    public List<Long> measureTime(SearchMethod<State> method){
        List<Long> times = new ArrayList<>(5);
        for(int i = 0; i < 5; i++){
            long startTime = System.currentTimeMillis();
            Result<State> ret =  method.search(initialState);
            long endTime = System.currentTimeMillis();
            if(i == 0) times.add((long) ret.getPath().size());
            times.add(endTime - startTime);
        }
        return times;
    }

    public void runBenchmark(){
        setup();
        Map<Integer, List<List<Long>>> totalResults = new TreeMap<>();
        List<List<Long>> levelResults;
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

    public void saveResultsAsCSV(Map<Integer, List<List<Long>>> results){
        try {
            PrintWriter file = new PrintWriter("benchmarkResults.csv");
            file.println("6," + searchMethods.size() + ",5"); //levels, number of methods, times per method
            for (Integer i : results.keySet()) {
                file.println(i);
                for(List<Long> methodTimes : results.get(i)){
                    StringJoiner joiner = new StringJoiner(",");
                    methodTimes.stream().map(String::valueOf).forEach(joiner::add);
                    String collect = joiner.toString();
                    file.println(collect);
                }
            }
            file.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }
}
