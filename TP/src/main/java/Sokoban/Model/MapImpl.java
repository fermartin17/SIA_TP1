package Sokoban.Model;
import Sokoban.Interfaces.Map;
import Sokoban.MapFactory;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Getter
@Setter
public class MapImpl implements Map {

    private TILES[][] map;
    private final String layout;
    private final int layout_rows;
    private final int layout_cols;
    private Position playerPosition;
    private List<Position> boxPositions;
    private List<Position> goalsPositions;
    private List<Position> deadlockPositions;


    public MapImpl(String layout, int rows, int cols){
       this.map = new TILES[rows][cols];
       this.layout = layout;
       this.layout_rows = rows;
       this.layout_cols = cols;
       this.playerPosition = new Position();
       this.boxPositions = new ArrayList<>();
       this.goalsPositions = new ArrayList<>();
    }

    @Override
    public void printMap(){
        char aux = 0;
        for(int i = 0; i < this.layout_rows; i++){
            for(int j = 0; j < this.layout_cols; j++){
                switch(getMap()[i][j]){
                    case OUT_OF_BOUNDS:
                    case FLOOR:
                        aux = ' ';
                        break;
                    case WALL:
                            aux = '#';
                    break;
                    case BLOCK:
                        aux = '$';
                        break;
                    case PLAYER:
                        aux = '@';
                        break;
                    case TARGET:
                        aux = '·';
                        break;
                }
                System.out.print(aux);
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    @Override
    public Boolean isWinner() {
        return goalsPositions.containsAll(boxPositions);
    }

    @Override
    public Boolean isValid() {
        //solo calcula si la caja está en alguna posición pre-calculada,
        //se podría hacer también un checkeo "dinámico" o dejarlo así y buscar
        //otra forma de podar
        return boxPositions.stream().noneMatch(b -> deadlockPositions.contains(b));
    }

    public void precalculateDeadlocks(){
        int aux;
        for(int i = 0; i < layout_rows; i++){
            for(int j = 0; j < layout_cols; j++){
                aux = 0;
                if(map[i][j] == TILES.FLOOR){
                    //arriba
                    aux += (map[i+1][j] == TILES.WALL)? 1: 0;
                    //derecha
                    aux += (map[i][j+1] == TILES.WALL)? 1: 0;
                    //abajo
                    aux += (map[i-1][j] == TILES.WALL)? 1: 0;
                    //izquierda
                    aux += (map[i][j-1] == TILES.WALL)? 1: 0;
                    //si la posicion tiene +2 paredes alrededor, es un deadlock
                    if(aux >= 2){
                        deadlockPositions.add(new Position(i,j));
                    }
                }
            }
        }
    }


    public void parse_map(){
        this.map = new TILES[layout_rows][layout_cols];
        String[] lines = layout.split("\n");
        char[] aux;
        for(int i = 0; i < lines.length; i++){
            aux = lines[i].toCharArray();
            for(int j = 0; j < aux.length; j++){
                switch(aux[j]){
                    case '~':
                        map[i][j] = Map.TILES.OUT_OF_BOUNDS;
                        break;
                    case ' ':
                        map[i][j] = Map.TILES.FLOOR;
                        break;
                    case '#':
                        map[i][j] = Map.TILES.WALL;
                        break;
                    case '$':
                        map[i][j] = Map.TILES.BLOCK;
                        boxPositions.add(new Position(i, j));
                        break;
                    case '@':
                        map[i][j] = Map.TILES.PLAYER;
                        playerPosition = new Position(i, j);
                        break;
                    case '.':
                        map[i][j] = Map.TILES.TARGET;
                        goalsPositions.add(new Position(i, j));
                        break;
                }
            }
        }
    }
}
