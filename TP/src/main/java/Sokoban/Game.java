package Sokoban;

import Sokoban.Interfaces.GameMap;
import Sokoban.Interfaces.SearchMethod;
import Sokoban.Model.MapFactory;
import Sokoban.Model.Result;
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

    public Map<Long,Result<State>> answerData(SearchMethod<State> method){
        Map<Long,Result<State>> resp = new HashMap<Long,Result<State>>();
        Double time;
        Long startTime = System.currentTimeMillis();
        Result<State> aux = method.search(this.initialState);
        Long endTime = System.currentTimeMillis();
        resp.put(endTime - startTime,aux);
        return resp;
    }

    public void start(SearchMethod<State> searchMethod, int level){
        setup(searchMethod, level);
        setupInitialState();
        Map<Long,Result<State>> answer = answerData(this.searchMethod);

        for (Long key: answer.keySet()){
            Result<State> result = answer.get(key);
            for(State state : result.getPath()){
                state.getMap().printMap();
            }
            System.out.println("El tiempo de ejecucion fue " + key + " milisegundos");
            System.out.println("Numero de pasos: " + result.getPath().size());
            System.out.println("Cantidad de nodos frontera: " + result.getNodesInFrontier());
            System.out.println("Cantidad de nodos expandidos: " + result.getNodesExpanded());
            System.out.println("Profundidad: " + result.getDepth());
            System.out.println("Encontro respuesta: " + result.isFound());
            System.out.println("Tipo de algoritmo: " + result.getMethod());
        }

    }
}
