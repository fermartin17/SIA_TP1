package Sokoban.Interfaces;

public interface Heuristic<T extends Comparable<T>> {

    Double evaluate(T elem);
}
