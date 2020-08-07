package Sokoban.Interfaces;

public interface Heuristic<T extends Comparable<T>> {

    boolean evaluate(T elem);
}
