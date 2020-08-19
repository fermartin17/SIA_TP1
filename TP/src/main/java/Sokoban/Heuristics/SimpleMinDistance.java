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
        double min = Double.MAX_VALUE;
        for(Position box : boxes) {
            double aux;
            for (Position g : goals) {
                aux = box.manhattanDistance(g);
                if (aux < min) min = aux;
            }
            total += min;
        }
        return total;
    }

    @Override
    public String toString() {
        return "Simple Min Distance";
    }
}
