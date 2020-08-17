package Model;

import Sokoban.Interfaces.GameMap;
import Sokoban.Model.MapFactory;
import Sokoban.Model.MapImpl;
import Sokoban.Model.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MapImplTest {

    MapImpl map;

    @Before
    public void setup(){
        MapFactory mapFactory = new MapFactory();
        map = (MapImpl) mapFactory.loadMap(0);
    }


    @Test
    public void correctPlayerPositionTest(){
        Assert.assertEquals(new Position(7,7), map.getPlayerPosition());
    }

    @Test
    public void correctGoalsPositionTest(){
        Assert.assertEquals(2, map.getGoalsPositions().size());
        Assert.assertEquals(new Position(7,1), map.getGoalsPositions().get(0));
        Assert.assertEquals(new Position(7,2), map.getGoalsPositions().get(1));
    }

    @Test
    public void correctBoxesPositionTest(){
        Assert.assertEquals(2, map.getBoxPositions().size());
        Assert.assertEquals(new Position(6,7), map.getBoxPositions().get(0));
        Assert.assertEquals(new Position(8,7), map.getBoxPositions().get(1));
    }

    @Test
    public void cloneMapTest(){
        GameMap.TILES[][] original = new GameMap.TILES[2][2];
        original[0][0] = original[1][1] = GameMap.TILES.FLOOR;
        original[0][1] = original[1][0] = GameMap.TILES.WALL;
        GameMap.TILES[][] expected = new GameMap.TILES[2][2];
        expected[0][0] = expected[1][1] = GameMap.TILES.FLOOR;
        expected[0][1] = expected[1][0] = GameMap.TILES.WALL;
        GameMap.TILES[][] result = map.cloneBoard(original);
        Assert.assertArrayEquals(expected[0], result[0]);
        Assert.assertArrayEquals(expected[1], result[1]);
    }

    @Test
    public void getTargetPositionTest(){
        Position original = new Position(0,0);
        Position expected = new Position(0,-1);
        Assert.assertEquals(expected, map.getTargetPosition(original, GameMap.DIRECTION.UP));
    }


    @Test
    public void getTargetPositionTest2(){
        Position original = new Position(0,0);
        Position expected = new Position(1,0);
        Assert.assertEquals(expected, map.getTargetPosition(original, GameMap.DIRECTION.RIGHT));
    }

    @Test
    public void getTargetPositionTest3(){
        Position original = new Position(0,0);
        Position expected = new Position(0,1);
        Assert.assertEquals(expected, map.getTargetPosition(original, GameMap.DIRECTION.DOWN));
    }

    @Test
    public void getTargetPositionTest4(){
        Position original = new Position(0,0);
        Position expected = new Position(-1,0);
        Assert.assertEquals(expected, map.getTargetPosition(original, GameMap.DIRECTION.LEFT));
    }

    @Test
    public void isWinnerTest(){
        List<Position> goalPositions = new ArrayList<>(2);
        goalPositions.add(new Position(0,0));
        goalPositions.add(new Position(3,4));
        List<Position> boxPositions = new ArrayList<>(2);
        boxPositions.add(new Position(0,0));
        boxPositions.add(new Position(3,4));
        map.setGoalsPositions(goalPositions);
        map.setBoxPositions(boxPositions);
        Assert.assertTrue(map.isWinner());
    }

    @Test
    public void isWinnerTest2(){
        List<Position> goalPositions = new ArrayList<>(2);
        goalPositions.add(new Position(0,0));
        goalPositions.add(new Position(3,4));
        List<Position> boxPositions = new ArrayList<>(2);
        boxPositions.add(new Position(0,0));
        boxPositions.add(new Position(2,4));
        map.setGoalsPositions(goalPositions);
        map.setBoxPositions(boxPositions);
        Assert.assertFalse(map.isWinner());
    }

    @Test
    public void isValidTest(){
        List<Position> deadlockPositions = new ArrayList<>(2);
        deadlockPositions.add(new Position(0,0));
        deadlockPositions.add(new Position(3,4));
        List<Position> boxPositions = new ArrayList<>(2);
        boxPositions.add(new Position(1,1));
        boxPositions.add(new Position(2,4));
        map.setDeadlockPositions(deadlockPositions);
        map.setBoxPositions(boxPositions);
        Assert.assertTrue(map.isValid());
    }

    @Test
    public void isValidTest2(){
        List<Position> deadlockPositions = new ArrayList<>(2);
        deadlockPositions.add(new Position(0,0));
        deadlockPositions.add(new Position(3,4));
        List<Position> boxPositions = new ArrayList<>(2);
        boxPositions.add(new Position(0,0));
        boxPositions.add(new Position(2,4));
        map.setDeadlockPositions(deadlockPositions);
        map.setBoxPositions(boxPositions);
        Assert.assertFalse(map.isValid());
    }

    @Test
    public void canMoveDirectionTest(){
        Position targetPos = map.getTargetPosition(map.getPlayerPosition(), GameMap.DIRECTION.UP);
        Assert.assertTrue(map.canMoveDirection(true, targetPos, GameMap.DIRECTION.UP));
    }

    @Test
    public void canMoveDirectionTest2(){
        Position targetPos = map.getTargetPosition(map.getPlayerPosition(), GameMap.DIRECTION.RIGHT);
        Assert.assertTrue(map.canMoveDirection(true, targetPos, GameMap.DIRECTION.RIGHT));
    }

    @Test
    public void canMoveDirectionTest3(){
        Position targetPos = map.getTargetPosition(map.getPlayerPosition(), GameMap.DIRECTION.DOWN);
        Assert.assertFalse(map.canMoveDirection(true, targetPos, GameMap.DIRECTION.DOWN));
    }

    @Test
    public void canMoveDirectionTest4(){
        Position targetPos = map.getTargetPosition(map.getPlayerPosition(), GameMap.DIRECTION.LEFT);
        Assert.assertTrue(map.canMoveDirection(true, targetPos, GameMap.DIRECTION.LEFT));
    }

    @Test
    public void movePlayerDontChangeMapTest(){
        MapImpl savedCopy = new MapImpl(map);
        MapImpl otherMap = new MapImpl(map);
        Position target = otherMap.getTargetPosition(otherMap.getPlayerPosition(), GameMap.DIRECTION.UP);
        otherMap.moveObjectOnMap(otherMap.getPlayerPosition(), target);
        Assert.assertArrayEquals(savedCopy.getBoard(), map.getBoard());
        Assert.assertEquals(map.getPlayerPosition(), savedCopy.getPlayerPosition());
        Assert.assertEquals(map.getBoxPositions(), savedCopy.getBoxPositions());
    }

}
