package Sokoban;

import Sokoban.Interfaces.GameMap;
import Sokoban.Model.MapImpl;

public class MapFactory {

    //se podría leer de un archivo
    private static final LevelInfo[] levels = new LevelInfo[]{
            new LevelInfo(10, 15,
                    "~~~~~~###~~~~~~\n" +
                            "~~~~~~#.#~~~~~~\n" +
                            "~~#####.#####~~\n" +
                            "~##         ##~\n" +
                            "##  # # # #  ##\n" +
                            "#  ##     ##  #\n" +
                            "# ##  # #  ## #\n" +
                            "#     $@$     #\n" +
                            "####  ###  ####\n" +
                            "~~~####~####~~~\n"
            ),
            new LevelInfo(6, 6,
                    "######\n" +
                            "# .  #\n" +
                            "#    #\n" +
                            "# $  #\n" +
                            "#   @#\n" +
                            "######\n"
                    )
    };

    public GameMap loadMap(int index){
        if(index < 0 || index > levels.length) return null;
        LevelInfo li = levels[index];
        MapImpl ret = new MapImpl(li.getLayout(), li.getLayoutRows(), li.getLayoutCols());
        ret.parse_map();
        ret.precalculateDeadlocks();
        return ret;
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
