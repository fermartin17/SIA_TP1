package Sokoban;

import Sokoban.Interfaces.Map;
import Sokoban.Levels.Nivel1;

public class MapFactory {

    public Map loadMap(String name){
        if(name == null) return null;
        if(name.equals("nivel1")){
            Nivel1 level = new Nivel1();
            level.setMap(parse_map(level.getLayout(), level.getLayoutRows(), level.getLayoutCols()));
            return new Nivel1();
        }
        return null;
    }

    private int[][] parse_map(String layout, int rows, int cols){
        int[][] map = new int[rows][cols];
        String[] lines = layout.split("\n");
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
}
