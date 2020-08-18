package Heuristics;

import Sokoban.Heuristics.MinMatching;
import org.junit.Assert;
import org.junit.Test;

public class MinMatchingTest {

    @Test
    public void sumMinDistancesBoxTest(){
        int[][] costMatrix = new int[3][3];
        costMatrix[0][0] = 0;
        costMatrix[0][1] = 2;
        costMatrix[0][2] = 8;
        costMatrix[1][0] = 1;
        costMatrix[1][1] = 9;
        costMatrix[1][2] = 8;
        costMatrix[2][0] = 4;
        costMatrix[2][1] = 4;
        costMatrix[2][2] = 0;
        MinMatching minMatching = new MinMatching();
        minMatching.setCostMatrix(costMatrix);
        //Assert.assertEquals(7, minMatching.sumMinDistancesForBox());
    }

}
