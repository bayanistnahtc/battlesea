package model;

import control.SaveShotsAndShips;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by R2 on 29.07.2015.
 * This class stores all information about ships location and condition
 */
public class Sea {
    private static final int MAX_SEA_SIZE = 10;
    private static final int MAX_SHIP_AT_SEA = 10;
    private int counter = 20; // 20 direct hits need to win
    public TypeOfField [][] field;


    public Sea(){
        field = new TypeOfField[MAX_SEA_SIZE][MAX_SEA_SIZE];
        for (int i = 0; i < MAX_SEA_SIZE; i++){
            for (int j = 0; j < MAX_SEA_SIZE; j++){
                field[i][j] = TypeOfField.EMPTY;
            }
        }
    }

    public int getCounter() {
        return counter;
    }

    public TypeOfField[][] getField() {
        return field;
    }

    public boolean addShip(Ship ship){
        if(checkToPositioned(ship.getX(), ship.getY(), ship.getSize(), ship.isDirection())){
            setShipOnSea(ship.getX(), ship.getY(), ship.getSize(), ship.isDirection());
            return true;
        }
        return false;
    }

    private void setShipOnSea(int x, int y, int typeOfShip, boolean direction) {
        if (direction) {
            for (int i = 0; i < typeOfShip; i++) {
                field[x][y+i] = TypeOfField.OCCUPIED;
            }
        } else {
            for (int i = 0; i < typeOfShip; i++) {
                field[x+i][y] = TypeOfField.OCCUPIED;
            }
        }
    }
    /**
     * This function check an ability to positioned ship on the sea
     * @param x
     * @param y
     * @param typeOfShip size of ship
     * @param direction
     * @return
     */
    private boolean checkToPositioned(int x, int y, int typeOfShip, boolean direction) {
        boolean flag = false;
        if(x >= 0 && y >= 0){
            if(direction){
                if(y + typeOfShip > MAX_SEA_SIZE-1){
                return false;
                }
            }else {
                if(x + typeOfShip > MAX_SEA_SIZE-1){
                    return false;
                }
            }
                    for(int i = x - 1; i <= x + 1; i++){
                        if(i < 0 || i > MAX_SEA_SIZE-1){
                            continue;
                        }
                        for (int j = y - 1; j <= y + 1; j++){
                            if(j < 0 || j > MAX_SEA_SIZE-1){
                                continue;
                            }
                            if(field[i][j] == TypeOfField.EMPTY){
                                flag = true;
                            }
                            else {
                                return false;
                            }
                        }

                    }
        }
        return flag;
    }

    /**
     *
     * @param x
     * @param y
     * @param player if player == true - first player, else second
     * @return if shooting in this place was already held return true
     */
      public TypeOfField shoot(int x, int y , boolean player){
          TypeOfField tof = TypeOfField.EMPTY;
          ArrayList<Ship> playersShip;
          ArrayList<Integer> playersShipI;
          if(player){
              playersShip = SaveShotsAndShips.secondPlayersShips;
              playersShipI = SaveShotsAndShips.secondPlayersShipsI;
          }else {
              playersShip = SaveShotsAndShips.firstPlayersShips;
              playersShipI = SaveShotsAndShips.firstPlayersShipsI;
          }

        switch (field[x][y]){
            case INJURED:

            case DOUBLESHOOT:
            case MISS:
            case HIT:
                tof = TypeOfField.DOUBLESHOOT;
                break;
            case EMPTY:
                field[x][y] = TypeOfField.MISS;
                tof = TypeOfField.MISS;
                break;
            case OCCUPIED:
                field[x][y] = TypeOfField.HIT;
                for (int i = 0; i < playersShip.size(); i++){
                    if(playersShip.get(i).isBelong(x, y) != null){
                        if(playersShipI.get(i) == 0){
                            tof = TypeOfField.HIT;
                            //если лодка убита, пометить
                            //вокруг нее пустые поля как MISS
                            for(int k = x - 1; k <= x + 1; k++){
                                if(k < 0 || k > 9){
                                    continue;
                                }
                                for (int j = y - 1; j <= y + 1; j++){
                                    if(j < 0 || j > 9){
                                        continue;
                                    }
                                    if (k == x && j == y){
                                        continue;
                                    }else {
                            //            field[k][j] = TypeOfField.MISS;
                                    }

                                }
                            }
                            break;
                        }else {
                            playersShipI.set(i,playersShipI.get(i)-1);
                            tof = TypeOfField.INJURED;
                            break;
                        }
                    }
                }

                counter--;

                break;
            default:
                break;
        }
        return tof;
    }

    /**
     * draw Sea on Console
     * @param whoCall if players Sea == true - first player, else seconds
     */
    public void draw(boolean whoCall){
        char symbol ;

        for (char ch = 'a';ch <= 'j';ch++){
            if(ch == 'a'){
                System.out.print(" ");
            }
            System.out.print("  " + ch);
        }
        for (int i = 0; i < MAX_SEA_SIZE; i++){
            if(i < MAX_SEA_SIZE ){
                System.out.println();
            }
            System.out.print(i + 1);
            for(int j = 0; j < MAX_SEA_SIZE; j++){
                switch (field[i][j]){
                    case EMPTY:
                        symbol = '*';
                        break;
                    case OCCUPIED:
                        symbol = 's';
                        break;
                    case HIT:
                        symbol = 'x';
                        break;
                    case MISS:
                        symbol = '+';
                        break;
                    default:
                        symbol = '.';
                        break;
                }
                System.out.print("  " + symbol);
            }
        }
    }


   // public static void aroundField(int x, int y);
}
