package Sokoban.Heuristics;

import Sokoban.Interfaces.Heuristic;
import Sokoban.Model.Position;
import Sokoban.Model.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NodeExpander implements Heuristic<State> {

    private final int expandFactor;

    public NodeExpander(int expandFactor){
        this.expandFactor = expandFactor;
    }

    public Map<Integer, List<Position>> expandPosition(Position p){
        Map<Integer, List<Position>> ret = new TreeMap<>();
        for(int i = 0; i < expandFactor; i++) {
            if(i == 0){
                ret.put(i, new ArrayList<>(1));
                ret.get(i).add(new Position(p.getX(), p.getY()));
                continue;
            }
            ret.put(i, new ArrayList<>(8));
            ret.get(i).add(new Position(p.getX(), p.getY() - i)); //arriba
            ret.get(i).add(new Position(p.getX() + i, p.getY() - i)); //arriba a la derecha
            ret.get(i).add(new Position(p.getX() + i, p.getY())); //derecha
            ret.get(i).add(new Position(p.getX() + i, p.getY() + i)); // derecha abajo
            ret.get(i).add(new Position(p.getX(), p.getY() + i)); //abajo
            ret.get(i).add(new Position(p.getX() - i, p.getY() + i)); //abajo izquierda
            ret.get(i).add(new Position(p.getX() - i, p.getY())); //izquierda
            ret.get(i).add(new Position(p.getX() - i, p.getY() - i)); //arriba a la izquierda
        }
        return ret;
    }

    public double distanceBetweenRadius(Position boxPos, Position goalPos){
        Map<Integer, List<Position>> boxExpansion = expandPosition(boxPos);
        Map<Integer, List<Position>> goalExpansion = expandPosition(goalPos);
        return _distanceBetweenRadius(boxExpansion, goalExpansion, expandFactor);
    }

    public double _distanceBetweenRadius(Map<Integer, List<Position>> boxExpansion,
                                         Map<Integer, List<Position>> goalExpansion, int expandFactor){
        if(expandFactor < 0) return 0.0; //caso base, están en la misma posición
        double min = Double.MAX_VALUE, aux;
        for(Position boxPositionInExpansion : boxExpansion.get(expandFactor)){
            for(Position goalPositionInExpansion : goalExpansion.get(expandFactor)){
                aux = boxPositionInExpansion.manhattanDistance(goalPositionInExpansion);
                if(aux == 0){ //contacto entre radios, decrementar el radio
                    return _distanceBetweenRadius(boxExpansion, goalExpansion, expandFactor - 1);
                }
                if(aux < min) min = aux;
            }
        }
        return min;
    }

    @Override
    public Double evaluate(State elem) {
        List<Position> boxes = elem.getMap().getBoxPositions();
        List<Position> goals = elem.getMap().getGoalsPositions();
        double acum = 0;
        double min, aux;
        for(Position box : boxes){
            min = Double.MAX_VALUE;
            for(Position goal : goals){
                aux = distanceBetweenRadius(box, goal);
                if(aux < min) min = aux;
            }
            acum += min;
        }
        return acum;
    }
}
