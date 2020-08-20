package Sokoban.Model;

import Sokoban.Interfaces.GameMap;
import Sokoban.Model.MapImpl;

public class MapFactory {

    //se podría leer de un archivo
    private static final LevelInfo[] levels = new LevelInfo[]{
            new LevelInfo("easy-test", 6, 6,
                      "######\n" +
                            "# .  #\n" +
                            "#    #\n" +
                            "# $  #\n" +
                            "#   @#\n" +
                            "######\n"
            ),
            new LevelInfo("demo-level1", 6,8,
                      "########\n" +
                            "#      #\n" +
                            "#@ $   #\n" +
                            "#   $  #\n" +
                            "# . .  #\n" +
                            "########\n"
            ),
            new LevelInfo("demo-level2", 6, 8,
                      "########\n"+
                            "#     .#\n" +
                            "#@ $   #\n" +
                            "#   $  #\n" +
                            "# .    #\n" +
                            "########\n"
            ),
            new LevelInfo("big-0",8, 7,
                    "#######\n" +
                            "#     #\n" +
                            "#     #\n" +
                            "#. #  #\n" +
                            "#. $$ #\n" +
                            "#.$$  #\n" +
                            "#.#  @#\n" +
                            "#######\n"
            ),
            new LevelInfo("original-tp",10, 15,
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
            new LevelInfo("big-3",11, 19,
                      "~~~~#####~~~~~~~~~~\n" +
                            "~~~~#   #~~~~~~~~~~\n" +
                            "~~~~#   #~~~~~~~~~~\n" +
                            "~~### #$##~~~~~~~~~\n" +
                            "~~#      #~~~~~~~~~\n" +
                            "### #$## #~~~######\n" +
                            "#   # ## #####   .#\n" +
                            "# $   $         ..#\n" +
                            "##### ### #@##   .#\n" +
                            "~~~~#     #########\n" +
                            "~~~~#######~~~~~~~~\n"
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
        String levelName;
        String layout;
        int layout_rows;
        int layout_cols;
        public LevelInfo(String levelName, int layout_rows, int layout_cols, String layout){
            this.levelName = levelName;
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
