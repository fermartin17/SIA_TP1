package Sokoban.Heuristics;

import Sokoban.Interfaces.Heuristic;
import Sokoban.Model.Position;
import Sokoban.Model.State;

import java.util.List;

public class EuclideanDistance implements Heuristic<State> {

    @Override
    public Double evaluate(State s) {
        List<Position> goals = s.getMap().getGoalsPositions();
        List<Position> boxes = s.getMap().getBoxPositions();
        double min = Double.MAX_VALUE;
        double aux;
        for(Position b : boxes){
            for(Position g : goals){
                aux = b.euclideanDistance(g);
                if(aux < min) min = aux;
            }
        }
        return min;
    }
}
