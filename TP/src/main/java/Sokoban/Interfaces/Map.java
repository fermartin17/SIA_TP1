package Sokoban.Interfaces;

public interface Map {

    enum TILES {
        OUT_OF_BOUNDS, FLOOR, WALL, BLOCK, PLAYER, TARGET;
    }

    TILES[][] getMap();

    String getLayout();

    void printMap();
}
