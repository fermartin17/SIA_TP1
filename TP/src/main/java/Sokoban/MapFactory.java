package Sokoban;

import Sokoban.Interfaces.Map;

public class MapFactory {

    //se podría leer de un archivo
    private static final LevelInfo[] levels = new LevelInfo[]{
            new LevelInfo(9, 28,
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

    public Map loadMap(String name){
        if(name == null) return null;
        if(name.equals("level1")){
            LevelInfo li = levels[0];
            return new Level(li.getLayout(), li.getLayoutRows(), li.getLayoutCols());
        }
        return null;
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
