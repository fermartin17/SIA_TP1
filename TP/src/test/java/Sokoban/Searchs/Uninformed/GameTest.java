package Sokoban.Searchs.Uninformed;

import Sokoban.Heuristics.*;
import Sokoban.Interfaces.GameMap;
import Sokoban.Interfaces.SearchMethod;
import Sokoban.Model.MapFactory;
import Sokoban.Model.State;
import Sokoban.Searchs.informed.GGS;
import Sokoban.Searchs.informed.IDA;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameTest {

    State initialState;
    List<SearchMethod<State>> uninformedSearchMethods;
    List<SearchMethod<State>> informedSearchMethods;

    @Before
    public void setup(){
        MapFactory mapFactory = new MapFactory();
        GameMap gameMap = mapFactory.loadMap(1); //EASY MAP
        initialState = new State(gameMap, GameMap.DIRECTION.INITIAL);
        uninformedSearchMethods = new ArrayList<>(3);
        uninformedSearchMethods.add(new BFS<>());
        uninformedSearchMethods.add(new DFS<>());
        uninformedSearchMethods.add(new IDDFS<>(15));
        informedSearchMethods = new ArrayList<>();
        informedSearchMethods.add(new GGS<>(0));
        informedSearchMethods.add(new GGS<>(1, new EuclideanDistance()));
        informedSearchMethods.add(new GGS<>(1, new ManhattanDistance()));
        informedSearchMethods.add(new GGS<>(1, new PlayerBoxGoalDistance()));
        informedSearchMethods.add(new GGS<>(1, new MinMatching()));
        informedSearchMethods.add(new IDA<>(new EuclideanDistance()));
    }

    @Test
    public void testUninformedMethods(){
        for(SearchMethod<State> method : uninformedSearchMethods){
            List<State> ret = (List<State>) method.search(initialState);
            Assert.assertNotNull(ret);
            ret.forEach(s -> s.getMap().printMap());
        }
    }

    @Test
    public void testInformedMethods(){
        for(SearchMethod<State> method : informedSearchMethods){
            List<State> ret = (List<State>) method.search(initialState);
            Assert.assertNotNull(ret);
            ret.forEach(s -> s.getMap().printMap());
        }
    }


}
