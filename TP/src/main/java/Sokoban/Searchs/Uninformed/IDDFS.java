package Sokoban.Searchs.Uninformed;

import Sokoban.Interfaces.Neighbors;
import Sokoban.Interfaces.Stateful;
import Sokoban.Interfaces.SearchMethod;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class IDDFS<T extends Neighbors<T> & Comparable<T> & Stateful<T>> implements SearchMethod<T> {

    private DLS<T> myDLS;
    private List<T> stack;
    private int jump;
    private int currenTry;
    private final int maxTries = 100;

    public IDDFS(int jump){
        this.myDLS = new DLS<>();
        this.stack = new LinkedList<>();
        this.jump = jump;
        this.currenTry = 0;
    }

    //In class said that all maps have solution so is not an infinite loop
    public List<T> search(T initial){
        for(int i = jump;;) {
            stack = myDLS.search(initial, i);
            if(stack != null && !stack.isEmpty()) return stack;
            if(currenTry < maxTries){
                currenTry++;
                i+= jump;
            }
            else
                return null;

        }
    }

    @Override
    public String searchName() { return "IDDFs"; }
}
