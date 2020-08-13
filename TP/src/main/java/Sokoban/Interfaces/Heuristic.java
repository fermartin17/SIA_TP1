package Sokoban.Interfaces;

public interface Heuristic<T extends Comparable<T>> {

    double evaluate(T elem);
}
