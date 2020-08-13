package Sokoban.Model;
import Sokoban.Interfaces.Map;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MapImpl implements Map {

    private TILES[][] map;
    private final String layout;
    private final int layout_rows;
    private final int layout_cols;
    private Position playerPosition;
    private List<Position> goalsPositions;


    public MapImpl(String layout, int rows, int cols, TILES[][] map){
       this.map = map;
       this.layout = layout;
       this.layout_rows = rows;
       this.layout_cols = cols;
       this.playerPosition = new Position();
       this.goalsPositions = new ArrayList<Position>();
    }

    @Override
    public void printMap(){
        char aux = 0;
        for(int i = 0; i < this.getLayout_rows(); i++){
            for(int j = 0; j < this.getLayout_cols(); j++){
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
        return null;
    }

    @Override
    public Boolean checkMap() {
        return null;
    }
}
