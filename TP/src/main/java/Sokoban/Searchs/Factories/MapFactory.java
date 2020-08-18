package Sokoban.Searchs.Factories;

import Sokoban.Interfaces.GameMap;
import Sokoban.Interfaces.GameMap.*;
import Sokoban.Model.Level;

public class MapFactory{

    //se podría leer de un archivo
    private static final LevelInfo[] levels = new LevelInfo[]{
            new LevelInfo(9, 16,
                    "~~~~~~###~~~~~~\n" +
                            "~~~~~~#.#~~~~~~\n" +
                            "~~#####.#####~~\n" +
                            "~##         ##~\n" +
                            "##  # # # #  ##\n" +
                            "#  ##     ##  #\n" +
                            "# ##  # #  ## #\n" +
                            "#     $@$     #\n" +
                            "####  ###  ####\n" +
                            "~~~#### ####~~~\n"
            )};

    public GameMap loadGameMap(int index){
        if(index < 0 || index > levels.length) return null;
        LevelInfo li = levels[index];
        TILES[][] map = parse_map(li);
        return new Level(li.getLayout(), li.getLayoutRows(), li.getLayoutCols(), map);
    }

    private TILES[][] parse_map(LevelInfo li){
        TILES[][] map = new TILES[li.layout_rows][li.layout_cols];
        String[] lines = li.layout.split("\n");
        char[] aux;
        for(int i = 0; i < lines.length; i++){
            aux = lines[i].toCharArray();
            for(int j = 0; j < aux.length; j++){
                switch(aux[j]){
                    case '~':
                        map[i][j] = TILES.OUT_OF_BOUNDS;
                        break;
                    case ' ':
                        map[i][j] = TILES.FLOOR;
                        break;
                    case '#':
                        map[i][j] = TILES.WALL;
                        break;
                    case '$':
                        map[i][j] = TILES.BLOCK;
                        break;
                    case '@':
                        map[i][j] = TILES.PLAYER;
                        break;
                    case '.':
                        map[i][j] = TILES.TARGET;
                        break;
                }
            }
        }
        return map;
    }

    private static class LevelInfo{
        String layout;
        int layout_rows;
        int layout_cols;
        public LevelInfo(int layout_rows, int layout_cols, String layout){
            this.layout = layout;
            this.layout_rows = layout_rows;
            this.layout_cols = layout_cols;
        }

        public String getLayout() {
            return layout;
        }

        public int getLayoutRows() {
            return layout_rows;
        }

        public int getLayoutCols() {
            return layout_cols;
        }
    }
}
