package Sokoban.Interfaces;

public interface GameMap {

    enum TILES {
        OUT_OF_BOUNDS, FLOOR, WALL, BLOCK, PLAYER, TARGET
    };

    enum DIRECTION {
        UP, RIGHT, DOWN, LEFT
    };

    TILES[][] getMap();

    String getLayout();

    void printMap();

    //Checks if goal's positions are marked
    Boolean isWinner();

    //Checks if the map is valid with player movement
    Boolean isValid();
}
