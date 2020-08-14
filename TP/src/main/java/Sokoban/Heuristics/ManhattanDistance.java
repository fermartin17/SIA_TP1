package Sokoban.Heuristics;

import Sokoban.Interfaces.Heuristic;
import Sokoban.Model.Position;
import Sokoban.Model.State;

import java.util.List;

public class ManhattanDistance implements Heuristic<State> {


    @Override
    public Double evaluate(State s) {
        List<Position> goals = s.getMap().getGoalsPositions();
        List<Position> boxes = s.getMap().getBoxPositions();
        return goals.stream().mapToDouble(g -> g.euclideanDistance((Position) boxes.stream())).min().stream().sum();
    }
}
