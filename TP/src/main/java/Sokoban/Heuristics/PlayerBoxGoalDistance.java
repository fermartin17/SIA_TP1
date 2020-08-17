package Sokoban.Heuristics;

import Sokoban.Interfaces.Heuristic;
import Sokoban.Model.Position;
import Sokoban.Model.State;

import java.util.List;

public class PlayerBoxGoalDistance implements Heuristic<State> {

    //Ha = distManhattan(jugador, caja*) + distManhattan(caja, objetivo*)
    //caja* = caja más cercana al jugador
    //objetivo* = caja más cercana al objetivo

    @Override
    public Double evaluate(State s) {
        Position playerPosition = s.getMap().getPlayerPosition();
        List<Position> goals = s.getMap().getGoalsPositions();
        List<Position> boxes = s.getMap().getBoxPositions();
        Position nearestBox = boxes.get(0);
        double minPlayerBox = Double.MAX_VALUE;
        double minBoxGoal = Double.MAX_VALUE;
        double aux;
        //encontrar la caja que no está en us objetivo más cercana al jugador
        for(Position box : boxes){
            aux = playerPosition.manhattanDistance(box);
            if(aux < minPlayerBox){
                minPlayerBox = aux;
                nearestBox = box;
            }
        }
        //encontrar el objetivo más cercano para dicha caja
        for(Position p : goals){
            aux = nearestBox.manhattanDistance(p);
            if(aux < minBoxGoal){
                minBoxGoal = aux;
            }
        }
        return minPlayerBox + minBoxGoal;
    }
}
