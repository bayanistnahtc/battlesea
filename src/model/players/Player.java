package model.players;
import model.Sea;
import model.Ship;

import java.util.Random;

/**
 * Created by R2 on 27.07.2015.
 * abstract class
 */

public abstract class Player {
    protected static int playerCounter = 1;
    protected String name;
    protected Sea sea;

    public Player(String name){
        this.name = name;
        playerCounter++;
    }

    /**
     * add 6 ships at the Sea
     * @param sea own Sea
     * @param player if player == true - first player, else second
     */
    public abstract void set6ships(Sea sea, boolean player);
    public abstract void setShip(Sea sea, boolean player, Ship ship);

    /**
     * shoot at the opponent Sea
     * @param sea  - opponent Sea
     * @param  player - if player == true - first player, else second
     */
    public abstract void fire(Sea sea, boolean player);
    public Ship artificialIntelligenceInSetShip(){
        Ship ship;
        Random random = new Random();
        ship = new Ship(random.nextInt(10), random.nextInt(10), 1, true);

        return ship;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public Sea getSea() {
        return sea;
    }

}
