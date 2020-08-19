package Sokoban.Heuristics;

import Sokoban.Interfaces.Heuristic;
import Sokoban.Model.Position;
import Sokoban.Model.State;

import java.util.List;

public class SimpleMinDistance implements Heuristic<State> {

    //Suma de las distancias mas cortas entre cajas y objetivos
    @Override
    public Double evaluate(State s) {
        List<Position> goals = s.getMap().getGoalsPositions();
        List<Position> boxes = s.getMap().getBoxPositions();
        double total = 0;
        for(Position box : boxes) {
            double min = Double.MAX_VALUE;
            double aux;
            for (Position g : goals) {
                aux = box.manhattanDistance(g);
                if (aux < min) min = aux;
            }
            total += min;
        }
        return total;
    }

}
