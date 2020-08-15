package Model;


import Sokoban.Model.Position;
import org.junit.Assert;
import org.junit.Test;

public class PositionTest {

    @Test
    public void manhattanDistanceTest(){
        Position p1 = new Position(0,0);
        Position p2 = new Position(3,7);
        Assert.assertEquals(10, (int) p1.manhattanDistance(p2));
        Assert.assertEquals(10, (int) p2.manhattanDistance(p1));
    }

    @Test
    public void manhattanDistanceTest3(){
        Position p1 = new Position(2,3);
        Position p2 = new Position(5,9);
        Assert.assertEquals(9, (int) p1.manhattanDistance(p2));
        Assert.assertEquals(9, (int) p2.manhattanDistance(p1));
    }

    @Test
    public void euclideanDistanceTest(){
        Position p1 = new Position(0,0);
        Position p2 = new Position(3,7);
        Assert.assertEquals(Math.sqrt(58), p1.euclideanDistance(p2), 0.1);
        Assert.assertEquals(Math.sqrt(58), p2.euclideanDistance(p1), 0.1);
    }

    @Test
    public void euclideanDistanceTest2(){
        Position p1 = new Position(2,3);
        Position p2 = new Position(5,9);
        Assert.assertEquals(Math.sqrt(45), p1.euclideanDistance(p2), 0.1);
        Assert.assertEquals(Math.sqrt(45), p2.euclideanDistance(p1), 0.1);
    }


}
