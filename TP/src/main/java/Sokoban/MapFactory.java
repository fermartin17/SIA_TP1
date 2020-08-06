package Sokoban;

import Sokoban.Interfaces.Map;

public class MapFactory {

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

    public Map loadMap(int index){
        if(index < 0 || index > levels.length) return null;
        LevelInfo li = levels[index];
        int[][] map = parse_map(li);
        return new Level(li.getLayout(), li.getLayoutRows(), li.getLayoutCols(), map);
    }

    private int[][] parse_map(LevelInfo li){
        int[][] map = new int[li.layout_rows][li.layout_cols];
        String[] lines = li.layout.split("\n");
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
