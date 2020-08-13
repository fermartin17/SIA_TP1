package Sokoban.Interfaces;

public interface Map {

    enum TILES {
        OUT_OF_BOUNDS, FLOOR, WALL, BLOCK, PLAYER, TARGET;
    }

    TILES[][] getMap();

    String getLayout();

    void printMap();

    //Checks if goal's positions are marked
    Boolean isWinner();

    //Checks if the map is valid with player movement
    Boolean isValid();
}
