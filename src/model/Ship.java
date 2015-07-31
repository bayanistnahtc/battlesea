package model;

/**
 * Created by R2 on 29.07.2015.
 *
 */

public class Ship {
    private static final int MAX_SHIP_SIZE = 4;
    private int x;
    private int y;
    private int size; //must be < 4
    private boolean direction; //HORIZONTAL & VERTICAL



    private boolean isAlive;

    public Ship(int x, int y, int size, boolean direction){
        this.x = x;
        this.y = y;
        this.size = size;
        if(size == 1){          //it important when it checking
            direction = true;
        }
        this.direction = direction;
        this.isAlive = true;
    }

    public static int getMaxShipSize() {
        return MAX_SHIP_SIZE;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public boolean isDirection() {
        return direction;
    }

    public Ship isBelong(int x, int y){
        Ship ship = null;
        if(this.direction){
            for (int i = 0; i < this.size; i++){
                if(this.x == x && this.y + i == y){
                    ship = new Ship(this.x, this.y + i, this.size, this.direction);
                }
            }
        }else {
            for (int i = 0; i < this.size; i++){
                if(this.x + i == x && this.y == y){
                    ship = new Ship(this.x + i, this.y, this.size, this.direction);
                }
            }
        }
        return ship;
    }
}
