package Sokoban.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Result<T> {

    private String method;
    private boolean found;
    private int depth;
    private double cost;
    private int nodesExpanded;
    private int nodesInFrontier;
    private List<T> path;


    public Result(String method, boolean found, int depth,
                  double cost, int nodesExpanded,
                  int nodesInFrontier, List<T> path) {
        this.method = method;
        this.found = found;
        this.depth = depth;
        this.cost = cost;
        this.nodesExpanded = nodesExpanded;
        this.nodesInFrontier = nodesInFrontier;
        this.path = path;
    }
}
