package Sokoban.Heuristics;

import Sokoban.Interfaces.Heuristic;
import Sokoban.Model.Position;
import Sokoban.Model.State;

import java.util.List;

public class PlayerBoxGoalDistance implements Heuristic<State> {

    //Ha = distManhattan(jugador, caja*) + distManhattan(caja, objetivo*)
    //caja* = caja más cercana al jugador
    //objetivo* = objetivo mas cerca de la caja

    @Override
    public Double evaluate(State s) {
        Position playerPosition = s.getMap().getPlayerPosition();
        List<Position> goals = s.getMap().getGoalsPositions();
        List<Position> boxes = s.getMap().getBoxPositions();
        Position nearestBox = null;
        double minPlayerBox = Double.MAX_VALUE;
        double minBoxGoal = Double.MAX_VALUE;
        double aux;
        //encontrar la caja que no está en su objetivo más cercana al jugador
        for(Position box : boxes){
            if(!goals.contains(box)) continue;
            aux = playerPosition.manhattanDistance(box);
            if(aux < minPlayerBox){
                minPlayerBox = aux;
                nearestBox = box;
            }
        }
        if(nearestBox == null) return 0.0;
        //encontrar el objetivo no ocupado más cercano para dicha caja
        for(Position g : goals){
            aux = nearestBox.manhattanDistance(g);
            if(aux < minBoxGoal){
                minBoxGoal = aux;
            }
        }
        return minPlayerBox + minBoxGoal;
    }
}
