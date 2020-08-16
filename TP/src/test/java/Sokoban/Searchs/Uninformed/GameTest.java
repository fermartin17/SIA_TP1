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
    public void solveTest(){
        MapFactory mapFactory = new MapFactory();
        GameMap gameMap = mapFactory.loadMap(1);
        State initialState = new State(gameMap, GameMap.DIRECTION.INITIAL);
        UninformedSearch<State> uninformedSearch = new DFS<>();
        List<State> ret = (List<State>) uninformedSearch.search(initialState);
        Assert.assertNotNull(ret);
        ret.forEach(s -> s.getMap().printMap());
    }
}
