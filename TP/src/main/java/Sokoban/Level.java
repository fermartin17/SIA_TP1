package Sokoban;

import Sokoban.Interfaces.Map;

public class Level implements Map {

    private int[][] map;
    private final String layout;
    private final int layout_rows;
    private final int layout_cols;

    public Level(String layout, int rows, int cols, int[][] map){
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

    @Override
    public void printMap(){
        //TODO
    }
}
