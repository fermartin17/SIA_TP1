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

    public MinMatching(){ }

    public void setCostMatrix(int[][] costMatrix){
        this.costMatrix = costMatrix;
    }

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
                if(map.getMap()[currBox.getX()][currBox.getY() - 1] == GameMap.TILES.WALL && deltaY > 0){
                    cost = -1;
                }
                //si el goal está arriba pero tiene una pared abajo
                if(map.getMap()[currBox.getX()][currBox.getY() + 1] == GameMap.TILES.WALL && deltaY < 0){
                    cost = -1;
                }
                //si el goal está a la der pero tiene una pared a la izq
                if(map.getMap()[currBox.getX() - 1][currBox.getY()] == GameMap.TILES.WALL && deltaX > 0){
                    cost = -1;
                }
                //si el goal está a la izq pero tiene una pared a la der
                if(map.getMap()[currBox.getX() + 1][currBox.getY()] == GameMap.TILES.WALL && deltaX < 0){
                    cost = -1;
                }
                costMatrix[i][j] = cost;
            }
        }
    }

    public int sumMinDistancesForBox(){
        int ret = 0;
        int min, aux;
        for (int[] matrix : costMatrix) {
            min = Integer.MAX_VALUE;
            for (int j = 0; j < costMatrix.length; j++) {
                aux = matrix[j];
                if (aux > 0 && aux < min) {
                    min = aux;
                }
            }
            ret += min;
        }
        return ret;
    }

    @Override
    public Double evaluate(State s) {
        //crea matriz de costos
        buildCostMatrix(s.getMap(), s.getMap().getBoxPositions(), s.getMap().getGoalsPositions());
        //correr el método húngaro sobre la matriz
        HungarianAlgorithm hungarianAlgorithm = new HungarianAlgorithm(costMatrix);
        costMatrix = hungarianAlgorithm.findOptimalAssignment();
        //ahora el valor más chico != 0 de cada fila indica el objetivo más próximo
        //retornamos la suma de esos valores
        return (double) sumMinDistancesForBox();
    }

}
