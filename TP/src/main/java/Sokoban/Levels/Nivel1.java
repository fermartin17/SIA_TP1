package Sokoban.Levels;

import Sokoban.Interfaces.Map;

public class Nivel1 implements Map {

    int[][] map;

    private final String layout;
    private final int layout_rows = 9;
    private final int layout_cols = 28;

    {
        layout ="~~~~~~###~~~~~~\n" +
                "~~~~~~#.#~~~~~~\n" +
                "~~#####.#####~~\n" +
                "~##         ##~\n" +
                "##  # # # #  ##\n" +
                "#  ##     ##  #\n" +
                "# ##  # #  ## #\n" +
                "#     $@$     #\n" +
                "####  ###  ####\n" +
                "~~~#### ####~~~\n" ;
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
