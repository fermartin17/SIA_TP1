package Sokoban.Heuristics;

import Sokoban.Interfaces.GameMap;
import Sokoban.Interfaces.Heuristic;
import Sokoban.Model.Position;
import Sokoban.Model.State;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//heurísitca para calcular el costo menor para un matcheo perfecto en un grafo completo bipartito

public class MinMatching implements Heuristic<State> {

    private int[][] costMatrix;
    private int[][] aux;
    private int[][] assignments;

    public MinMatching(){ }

    public void setCostMatrix(int[][] costMatrix){
        this.costMatrix = costMatrix;
    }

    //ver por que crea una matriz de 1x2
    public void buildCostMatrix(GameMap map, List<Position> boxes, List<Position> goals){
        int size = boxes.size();
        Position currBox, currGoal;
        int deltaX, deltaY;
        int cost;
        costMatrix = new int[size][size];
        aux = new int[size][size];
        for(int i = 0; i < size; i++){
            currBox = boxes.get(i);
            for(int j = 0; j < size; j++){
                currGoal = goals.get(j);
                //calcular a mano la distancia manhattan para encontrar los casos que dan infinito
                deltaY = currGoal.getY() - currBox.getY();
                deltaX = currGoal.getX() - currBox.getX();
                //si el goal está abajo pero tiene una pared arriba
                cost = Math.abs(deltaX) + Math.abs(deltaY);
                if(map.getMap()[currBox.getY()][currBox.getX() - 1] == GameMap.TILES.WALL && deltaY < 0){
                    cost = 10000;
                }
                //si el goal está arriba pero tiene una pared abajo
                if(map.getMap()[currBox.getY()][currBox.getX() + 1] == GameMap.TILES.WALL && deltaY > 0){
                    cost = 10000;
                }
                //si el goal está a la der pero tiene una pared a la izq
                if(map.getMap()[currBox.getY() - 1][currBox.getX()] == GameMap.TILES.WALL && deltaX < 0){
                    cost = 10000;
                }
                //si el goal está a la izq pero tiene una pared a la der
                if(map.getMap()[currBox.getY() + 1][currBox.getX()] == GameMap.TILES.WALL && deltaX > 0){
                    cost = 10000;
                }
                aux[i][j] = costMatrix[i][j] = cost;
            }
        }
    }

    @Override
    public Double evaluate(State s) {
        //reducir los cálculos para las cajas que no están en la posición final
        List<Position> boxes = s.getMap().getBoxPositions();
        List<Position> boxesReduced = new LinkedList<>(boxes);
        List<Position> goals = s.getMap().getGoalsPositions();
        List<Position> goalsReduced = new LinkedList<>(goals);
        for(Position box : boxes){
            boolean found = false;
            for(Position goal : goals){
                if(box.equals(goal)){
                    goalsReduced.remove(goal);
                    found = true;
                }
            }
            if(found) boxesReduced.remove(box);
        }
        if(boxesReduced.isEmpty()) return 0.0;
        //crea matriz de costos
        buildCostMatrix(s.getMap(), boxesReduced, goalsReduced);
        //correr el método húngaro sobre la matriz
        HungarianAlgorithm hungarianAlgorithm = new HungarianAlgorithm(aux);
        assignments = hungarianAlgorithm.findOptimalAssignment();
        //retornamos la suma de esos valores
        double aux = Arrays.stream(assignments).mapToInt(assignment -> costMatrix[assignment[0]][assignment[1]]).sum();
        double dist = distPlayerToMoveBox(boxesReduced, goalsReduced, s.getMap().getPlayerPosition());
        return aux + dist;
    }

    //calc player dist to opposite side of the box
    public double distPlayerToMoveBox(List<Position> boxesReduced, List<Position> goalsReduced, Position playerPos){
        int minVal = Integer.MAX_VALUE;
        int minRow = -1;
        for(int[] assignment : assignments){
            if(assignment[1] < minVal){
                minVal = assignment[1];
                minRow = assignment[0];
            }
        }
        Position box = boxesReduced.get(minRow);
        Position targetGoal = goalsReduced.get(minVal);
        double deltaX = targetGoal.getX() - box.getX();
        double deltaY = targetGoal.getY() - box.getY();
        boolean moreX = deltaX > deltaY;
        Position playerTarget;
        if(moreX){
            if( deltaX > 0){
                playerTarget = new Position(box.getX() -1 , box.getY());
            }else{
                playerTarget = new Position(box.getX() + 1, box.getY());
            }
        }else{
            if( deltaY > 0){
                playerTarget = new Position(box.getX(), box.getY() - 1);
            }else{
                playerTarget = new Position(box.getX(), box.getY() + 1);
            }
        }
        return playerPos.manhattanDistance(playerTarget);
    }

    @Override
    public String toString() {
        return "Min Matching";
    }

}
