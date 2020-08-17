package Model;

import Sokoban.Interfaces.GameMap;
import Sokoban.MapFactory;
import Sokoban.Model.MapImpl;
import Sokoban.Model.State;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class StateTest {

    State state;


    @Before
    public void setup(){
        MapFactory mapFactory = new MapFactory();
        GameMap map = (MapImpl) mapFactory.loadMap(1);
        state = new State(map, GameMap.DIRECTION.INITIAL);
    }

    @Test
    public void saveHashCodeTest(){
        Set<Integer> set = new HashSet<>();
        set.add(state.hashCode());
        Assert.assertTrue(set.contains(state.hashCode()));
    }

    @Test
    public void saveObjectTest(){
        Set<State> set = new HashSet<>();
        set.add(state);
        Assert.assertTrue(set.contains(state));
    }

    @Test
    public void neighborHashTest(){
        List<State> neighbors = state.getNeighbors();
        Set<State> set = new HashSet<>(state.getNeighbors());
        neighbors.stream().map(set::contains).forEach(Assert::assertTrue);
    }
}
