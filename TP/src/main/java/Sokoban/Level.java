package Sokoban;

import Sokoban.Interfaces.Map;

public class Level implements Map {

    private int[][] map;
    private final String layout;
    private final int layout_rows;
    private final int layout_cols;

    public Level(String layout, int rows, int cols){
       this.map = parse_map(layout, rows, cols);
       this.layout = layout;
       this.layout_rows = rows;
       this.layout_cols = cols;
    }

    private int[][] parse_map(String layout, int rows, int cols){
        int[][] map = new int[rows][cols];
        String[] lines = layout.split("\n");
        char[] aux;
        for(int i = 0; i < lines.length; i++){
            aux = lines[i].toCharArray();
            for(int j = 0; j < aux.length; j++){
                switch(aux[j]){
                    case '~':
                        map[i][j] = -1;
                        break;
                    case ' ':
                        map[i][j] = 0;
                        break;
                    case '#':
                        map[i][j] = 1;
                        break;
                    case '$':
                        map[i][j] = 2;
                        break;
                    case '@':
                        map[i][j] = 3;
                        break;
                    case '.':
                        map[i][j] = 4;
                        break;
                }
            }
        }
        return map;
    }


    public int getLayoutRows(){
        return this.layout_rows;
    }

    public int getLayoutCols(){
        return this.layout_cols;
    }

   public void setMap(int[][] map){
        this.map = map;
   }

    @Override
    public int[][] getMap() {
        return this.map;
    }

    @Override
    public String getLayout(){
        return this.layout;
    }
}
