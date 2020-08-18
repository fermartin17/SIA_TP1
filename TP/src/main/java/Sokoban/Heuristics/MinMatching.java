package Sokoban.Heuristics;

import Sokoban.Interfaces.GameMap;
import Sokoban.Interfaces.Heuristic;
import Sokoban.Model.Position;
import Sokoban.Model.State;

import java.util.Arrays;
import java.util.List;

//heurísitca para calcular el costo menor para un matcheo perfecto en un grafo completo bipartito

public class MinMatching implements Heuristic<State> {

    private int[][] costMatrix;
    private int[][] costMatrixReduced;

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
        for(int i = 0; i < size; i++){
            currBox = boxes.get(i);
            for(int j = 0; j < size; j++){
                currGoal = goals.get(j);
                //calcular a mano la distancia manhattan para encontrar los casos que dan infinito
                deltaY = currGoal.getY() - currBox.getY();
                deltaX = currGoal.getX() - currBox.getX();
                //si el goal está abajo pero tiene una pared arriba
                cost = Math.abs(deltaX) + Math.abs(deltaY);
                if(map.getMap()[currBox.getY()][currBox.getX() - 1] == GameMap.TILES.WALL && deltaY > 0){
                    cost = -1;
                }
                //si el goal está arriba pero tiene una pared abajo
                if(map.getMap()[currBox.getY()][currBox.getX() + 1] == GameMap.TILES.WALL && deltaY < 0){
                    cost = -1;
                }
                //si el goal está a la der pero tiene una pared a la izq
                if(map.getMap()[currBox.getY() - 1][currBox.getX()] == GameMap.TILES.WALL && deltaX > 0){
                    cost = -1;
                }
                //si el goal está a la izq pero tiene una pared a la der
                if(map.getMap()[currBox.getY() + 1][currBox.getX()] == GameMap.TILES.WALL && deltaX < 0){
                    cost = -1;
                }
                costMatrix[i][j] = cost;
            }
        }
    }

    public int sumMinDistancesForBox(){
        int ret = 0;
        int minValue = Integer.MAX_VALUE, minIndex = 0, aux;
        for(int i = 0; i < costMatrix.length; i++){
            for(int j = 0; j < costMatrix[0].length; j++){
                aux = costMatrixReduced[i][j];
                if(aux == 0) continue;
                if(aux < minValue){
                    minValue = aux;
                    minIndex = j;
                }
            }
            ret += costMatrix[i][minIndex];
        }
        return ret;
    }

    @Override
    public Double evaluate(State s) {
        //crea matriz de costos
        buildCostMatrix(s.getMap(), s.getMap().getBoxPositions(), s.getMap().getGoalsPositions());
        //correr el método húngaro sobre la matriz
        HungarianAlgorithm hungarianAlgorithm = new HungarianAlgorithm(costMatrix);
        costMatrixReduced = hungarianAlgorithm.findOptimalAssignment();
        //ahora el valor más chico != 0 de cada fila indica el objetivo más próximo
        //retornamos la suma de esos valores
        return (double) sumMinDistancesForBox();
    }

}
