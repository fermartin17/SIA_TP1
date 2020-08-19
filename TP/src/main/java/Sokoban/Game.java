package Sokoban;

import Sokoban.Interfaces.GameMap;
import Sokoban.Interfaces.SearchMethod;
import Sokoban.Model.MapFactory;
import Sokoban.Model.State;

import java.util.*;

public class Game {

    Sokoban.Model.MapFactory mapFactory;
    State initialState;
    SearchMethod<State> searchMethod;
    int level;

    public Game(){}

    public void setup(SearchMethod<State> searchMethod, int level){
        this.level = level;
        this.searchMethod = searchMethod;
        mapFactory = new MapFactory();
    }

    public void setupInitialState(){
        GameMap gameMap = mapFactory.loadMap(this.level);
        initialState = new State(gameMap, GameMap.DIRECTION.INITIAL);
    }

    public Map<Long,List<State>> answerData(SearchMethod<State> method){
        Map<Long,List<State>> resp = new HashMap<Long,List<State>>();
        Double time;
        Long startTime = System.nanoTime();
        List<State> aux = (List<State>) method.search(this.initialState);
        Long endTime = System.nanoTime();
        resp.put(endTime - startTime,aux);
        return resp;
    }

    public void start(SearchMethod<State> searchMethod, int level){
        setup(searchMethod, level);
        setupInitialState();
        Map<Long,List<State>> answer = answerData(this.searchMethod);

        for (Long key: answer.keySet()){
            List<State> states = answer.get(key);
            for(State state : states){
                state.getMap().printMap();
            }
            System.out.println("El tiempo de ejecucion fue " + key);
        }

    }
}