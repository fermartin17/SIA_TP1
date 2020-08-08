package Sokoban.Searchs.Uninformed;

import Sokoban.Interfaces.Neighbors;
import Sokoban.Interfaces.UninformedSearch;

import java.util.List;

public class DFS <T extends Neighbors<T> & Comparable<T>> implements UninformedSearch<T> {

    public DFS(){
    }

    @Override
    public List<T> search(T start, T goal) {
        return null;
    }
}
