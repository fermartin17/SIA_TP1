package Sokoban.Interfaces;

import Sokoban.Model.Result;

import java.util.Collection;
import java.util.List;

public interface SearchMethod<T> {

    //método para hacer más fácil el testeo
    String searchName();

    //retorna una lista con los pasos para ir desde start a goal
    Result search(T state);
}
