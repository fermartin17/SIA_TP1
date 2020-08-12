package Sokoban.Searchs.Uninformed;

import Sokoban.Interfaces.Neighbors;
import Sokoban.Model.State;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;
import java.util.Deque;

@Getter
@Setter
public class IDDFS<T extends Neighbors<T> & Comparable<T>>  {

    private DLS myDLS;
    private Deque<State> stack;

    public IDDFS(){
        this.myDLS = new DLS();
        this.stack = new ArrayDeque<State>();
    }

    //In class said that all maps have solution so is not an infinite loop
    public Deque<State> search(State initial,int jump){
        for(int i = jump;;) {
            stack = myDLS.search(initial, i);
            if(stack.isEmpty())
                i+=jump;
            else
                return stack;
        }
    }
}
