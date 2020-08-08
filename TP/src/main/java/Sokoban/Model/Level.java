package Sokoban.Model;

import Sokoban.Interfaces.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Level implements Map {

    private TILES[][] map;
    private final String layout;
    private final int layout_rows;
    private final int layout_cols;

    public Level(String layout, int rows, int cols, TILES[][] map){
       this.map = map;
       this.layout = layout;
       this.layout_rows = rows;
       this.layout_cols = cols;
    }

    public int getLayoutRows(){
        return this.layout_rows;
    }

    public int getLayoutCols(){
        return this.layout_cols;
    }

   public void setMap(TILES[][] map){
        this.map = map;
   }

    @Override
    public TILES[][] getMap() {
        return this.map;
    }

    @Override
    public String getLayout(){
        return this.layout;
    }

    @Override
    public void printMap(){
        char aux = 0;
        for(int i = 0; i < getLayoutRows(); i++){
            for(int j = 0; j < getLayoutCols(); j++){
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
}
