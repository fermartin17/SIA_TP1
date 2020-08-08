package Sokoban.Interfaces;

import java.util.List;

public interface UninformedSearch<T> {

    //retorna una lista con los pasos para ir desde start a goal
    List<T> search(T start, T goal);
}
