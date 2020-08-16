package Sokoban.Model;
import Sokoban.Interfaces.GameMap;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MapImpl implements GameMap {

    private TILES[][] board;
    private final String layout;
    private final int layout_rows;
    private final int layout_cols;
    private Position playerPosition;
    private List<Position> boxPositions;
    private List<Position> goalsPositions;
    private List<Position> deadlockPositions;


    public MapImpl(String layout, int rows, int cols){
       this.board = new TILES[rows][cols];
       this.layout = layout;
       this.layout_rows = rows;
       this.layout_cols = cols;
       this.playerPosition = new Position();
       this.boxPositions = new ArrayList<>();
       this.goalsPositions = new ArrayList<>();
       this.deadlockPositions = new ArrayList<>();
    }

    public MapImpl(MapImpl map){
        this.board = cloneBoard(map.board);
        this.layout = map.layout;
        this.layout_rows = map.layout_rows;
        this.layout_cols = map.layout_cols;
        this.playerPosition = new Position(map.playerPosition);
        this.boxPositions = new ArrayList<>(map.boxPositions);
        this.goalsPositions = new ArrayList<>(map.goalsPositions);
        this.deadlockPositions = new ArrayList<>(map.deadlockPositions);
    }

    public TILES[][] cloneBoard(TILES[][] oldMap){
        TILES[][] newMap = new TILES[oldMap.length][oldMap[0].length];
        for(int i = 0; i < oldMap.length; i++){
            System.arraycopy(oldMap[i], 0, newMap[i], 0, oldMap[0].length);
        }
        return newMap;
    }

    public Position getTargetPosition(Position p, DIRECTION dir){
        Position ret = new Position(p.getX(), p.getY());
        switch (dir){
            case UP:
                ret.setY(p.getY() - 1);
                break;
            case RIGHT:
                ret.setX(p.getX() + 1);
                break;
            case DOWN:
                ret.setY(p.getY() + 1);
                break;
            case LEFT:
                ret.setX(p.getX() - 1);
                break;
        }
        return ret;
    }

    public boolean canMoveDirection(boolean player, Position targetPosition, DIRECTION dir){
        TILES targetTile = board[targetPosition.getY()][targetPosition.getX()];
        if(targetTile == TILES.WALL || targetTile == TILES.OUT_OF_BOUNDS) return false;
        if(player){
            if(targetTile == TILES.BLOCK) return canMoveDirection(false, getTargetPosition(targetPosition, dir), dir);
        }else{ //if its a block
            return targetTile != TILES.BLOCK;
        }
        return true;
    }

    public void moveObjectOnMap(Position start, Position finish){
        if(start == null || finish == null || start.equals(finish)) return;
        board[finish.getY()][finish.getX()] = board[start.getY()][start.getX()];
        if(goalsPositions.stream().anyMatch(g -> g.equals(start))){
            board[start.getY()][start.getX()] = TILES.TARGET;
        }else{
            board[start.getY()][start.getX()] = TILES.FLOOR;
        }
    }

    //función para mover al jugador, asumimos que se puede mover en la dirección que se indica
    public boolean movePlayer(DIRECTION dir){
        Position newPosition = getTargetPosition(getPlayerPosition(), dir);
        Position collidedBox = null;
        int collisionIndex;
        //checkear si nos podemos mover en esa dirección (si hay una caja, toma en cuenta si se puede mover)
        if(!canMoveDirection(true, newPosition, dir)) return false;
        //ver si está en la misma posición que una caja
        for(collisionIndex = 0; collisionIndex < boxPositions.size(); collisionIndex++){
            if(boxPositions.get(collisionIndex).equals(newPosition)){
                collidedBox = boxPositions.get(collisionIndex);
                break;
            }
        }
        //si es así, mover esa caja (ya sabemos que es posible)
        if(collidedBox != null){
            Position boxPushedPosition = getTargetPosition(newPosition, dir);
            moveObjectOnMap(collidedBox, boxPushedPosition);
            boxPositions.set(collisionIndex, boxPushedPosition);
        }
        //mover al jugador
        moveObjectOnMap(playerPosition, newPosition);
        playerPosition = newPosition;
        return true;
    }

    @Override
    public TILES[][] getMap() {
        return board;
    }

    @Override
    public void printMap(){
        System.out.println("-----------------------------------");
        char aux = 0;
        for(int i = 0; i < this.layout_rows; i++){
            for(int j = 0; j < this.layout_cols; j++){
                switch(board[i][j]){
                    case OUT_OF_BOUNDS:
                    case FLOOR:
                        aux = ' ';
                        break;
                    case WALL:
                        aux = '#';
                    break;
                    case BLOCK:
                        aux = '$';
                        break;
                    case PLAYER:
                        aux = '@';
                        break;
                    case TARGET:
                        aux = '·';
                        break;
                }
                System.out.print(aux);
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println("-----------------------------------");
    }

    @Override
    public Boolean isWinner() {
        return goalsPositions.containsAll(boxPositions);
    }

    @Override
    public Boolean isValid() {
        //solo calcula si la caja está en alguna posición pre-calculada,
        //se podría hacer también un checkeo "dinámico" o dejarlo así y buscar
        //otra forma de podar
        return boxPositions.stream().noneMatch(b -> deadlockPositions.contains(b));
    }

    public void precalculateDeadlocks(){
        for(int i = 0; i < layout_rows; i++){
            for(int j = 0; j < layout_cols; j++){
                if(board[i][j] == TILES.FLOOR){
                    //arriba a la der
                    if(board[i-1][j] == TILES.WALL && board[i][j+1] == TILES.WALL){
                        deadlockPositions.add(new Position(j,i));
                        continue;
                    }
                    //derecha abajo
                    if(board[i+1][j] == TILES.WALL && board[i][j+1] == TILES.WALL){
                        deadlockPositions.add(new Position(j,i));
                        continue;
                    }
                    //abajo izq
                    if(board[i+1][j] == TILES.WALL && board[i][j-1] == TILES.WALL){
                        deadlockPositions.add(new Position(j,i));
                        continue;
                    }
                    //arriba izq
                    if(board[i-1][j] == TILES.WALL && board[i][j-1] == TILES.WALL){
                        deadlockPositions.add(new Position(j,i));
                    }
                }
            }
        }
    }

    public void parse_map(){
        this.board = new TILES[layout_rows][layout_cols];
        String[] lines = layout.split("\n");
        char[] aux;
        for(int i = 0; i < lines.length; i++){
            aux = lines[i].toCharArray();
            for(int j = 0; j < aux.length; j++){
                switch(aux[j]){
                    case '~':
                        board[i][j] = GameMap.TILES.OUT_OF_BOUNDS;
                        break;
                    case ' ':
                        board[i][j] = GameMap.TILES.FLOOR;
                        break;
                    case '#':
                        board[i][j] = GameMap.TILES.WALL;
                        break;
                    case '$':
                        board[i][j] = GameMap.TILES.BLOCK;
                        boxPositions.add(new Position(j, i));
                        break;
                    case '@':
                        board[i][j] = GameMap.TILES.PLAYER;
                        playerPosition = new Position(j, i);
                        break;
                    case '.':
                        board[i][j] = GameMap.TILES.TARGET;
                        goalsPositions.add(new Position(j, i));
                        break;
                }
            }
        }
    }

    @Override
    public int hashCode(){
        int player = playerPosition.hashCode();
        int aux = 0;
        for(Position b : boxPositions){
            aux ^= b.hashCode();
        }
        return player ^ aux;
    }

    public boolean equals(Object o){
        if(o == null || o.getClass() != this.getClass()) return false;
        if(o == this) return true;
        MapImpl m = (MapImpl) o;
        return playerPosition.equals(((MapImpl) o).playerPosition) && boxPositions.equals(((MapImpl) o).boxPositions);
    }
}
