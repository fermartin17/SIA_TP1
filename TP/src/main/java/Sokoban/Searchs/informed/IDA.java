package Sokoban.Searchs.informed;

import Sokoban.Interfaces.Heuristic;
import Sokoban.Interfaces.Neighbors;
import Sokoban.Model.StatePackage;

public class IDA <T extends Neighbors<T> & Comparable<T>>{
    private StatePackage<T> statePackage;
    private double omega;
    private Heuristic<T> heuristic;
    private GGS myGGS;


    public IDA(double omega, Heuristic<T> heuristic){
        this.omega = omega;
        this.heuristic = heuristic;
        this.myGGS = new GGS(0.5,this.heuristic);
    }
}
