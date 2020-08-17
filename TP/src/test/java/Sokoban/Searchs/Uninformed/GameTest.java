package Sokoban.Searchs.Uninformed;

import Sokoban.Interfaces.GameMap;
import Sokoban.Interfaces.UninformedSearch;
import Sokoban.MapFactory;
import Sokoban.Model.State;
import Sokoban.Model.StatePackage;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GameTest {

    @Test
    public void solveBFSTest(){
        MapFactory mapFactory = new MapFactory();
        GameMap gameMap = mapFactory.loadMap(0);
        State initialState = new State(gameMap, GameMap.DIRECTION.INITIAL);
        UninformedSearch<State> uninformedSearch = new BFS<>();
        List<State> ret = (List<State>) uninformedSearch.search(initialState);
        Assert.assertNotNull(ret);
        ret.forEach(s -> s.getMap().printMap());
    }

    @Test
    public void solveDFSTest(){
        MapFactory mapFactory = new MapFactory();
        GameMap gameMap = mapFactory.loadMap(0);
        State initialState = new State(gameMap, GameMap.DIRECTION.INITIAL);
        UninformedSearch<State> uninformedSearch = new DFS<>();
        List<State> ret = (List<State>) uninformedSearch.search(initialState);
        Assert.assertNotNull(ret);
        ret.forEach(s -> s.getMap().printMap());
    }

    @Test
    public void solveIDDFSTest(){
        MapFactory mapFactory = new MapFactory();
        GameMap gameMap = mapFactory.loadMap(0);
        State initialState = new State(gameMap, GameMap.DIRECTION.INITIAL);
        UninformedSearch<State> uninformedSearch = new IDDFS<>(30);
        List<State> ret = (List<State>) uninformedSearch.search(initialState);
        Assert.assertNotNull(ret);
        ret.forEach(s -> s.getMap().printMap());
    }
}
