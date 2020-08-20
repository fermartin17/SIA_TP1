package Sokoban.Searchs.Uninformed;

import Sokoban.Interfaces.Neighbors;
import Sokoban.Interfaces.Stateful;
import Sokoban.Interfaces.SearchMethod;
import Sokoban.Model.Result;
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
    private int totalNodesExpanded;
    private int nodesInFrontier;

    public IDDFS(int jump){
        this.myDLS = new DLS<>();
        this.stack = new LinkedList<>();
        this.jump = jump;
        this.currenTry = 0;
        this.totalNodesExpanded = 0;
        this.nodesInFrontier = 0;
    }

    //In class said that all maps have solution so is not an infinite loop
    public Result<T> search(T initial){
        Result<T> result;
        if(initial == null) return new Result<T>(searchName(), false, 0,0,totalNodesExpanded, nodesInFrontier, null);
        for(int i = jump;;) {
            result = myDLS.search(initial, i);
            if(result.isFound()){
                result.setMethod(searchName());
                return result;
            }
            if(currenTry < maxTries){
                currenTry++;
                i+= jump;
            }
            else
                return new Result<T>(searchName(), false, 0,0,totalNodesExpanded, 0, null);
        }
    }

    @Override
    public String searchName() { return "IDDFS"; }
}
