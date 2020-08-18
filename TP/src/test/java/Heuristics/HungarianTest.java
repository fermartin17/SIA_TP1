package Heuristics;

import Sokoban.Heuristics.HungarianAlgorithm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HungarianTest {

    //http://citeseerx.ist.psu.edu/viewdoc/download;jsessionid=8BA204ED92639C0447CE1A5DE3D62E59?doi=10.1.1.46.947&rep=rep1&type=pdf

    int[][] cost;

    @Test
    public void hungarianMethodTest(){
        cost = new int[3][3];
        cost[0][0] = 2;
        cost[0][1] = 6;
        cost[0][2] = 9;
        cost[1][0] = 6;
        cost[1][1] = 1000;
        cost[1][2] = 1000;
        cost[2][0] = 1000;
        cost[2][1] = 1;
        cost[2][2] = 2;
        HungarianAlgorithm hungarianAlgorithm = new HungarianAlgorithm(cost);
        hungarianAlgorithm.findOptimalAssignment();
        Integer[][] expected = new Integer[cost.length][cost.length];
        expected[0][0] = 0;
        expected[0][1] = 0;
        expected[0][2] = 2;
        expected[1][0] = 0;
        expected[1][1] = 990;
        expected[1][2] = 989;
        expected[2][0] = 1003;
        expected[2][1] = 0;
        expected[2][2] = 0;
        //for(int i = 0; i < cost.length; i++){
        //    for(int j = 0; j< cost[0].length; j++){
        //        System.out.print(cost[i][j] + " ");
        //    }
        //    System.out.println();
        //}
        Assert.assertArrayEquals(expected, cost);
    }

    @Test
    public void oneSizeCostMatrixTest(){
        cost = new int[1][1];
        cost[0][0] = 2;
        HungarianAlgorithm hungarianAlgorithm = new HungarianAlgorithm(cost);
        hungarianAlgorithm.findOptimalAssignment();
    }

}
