package tests;

import control.SaveShotsAndShips;
import model.Sea;
import model.Ship;
import model.players.Bot;
import model.players.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by R2 on 31.07.2015.
 */
public class SeaTest {

    @Before
    public void setUp() throws Exception {



        SaveShotsAndShips.firstPlayersShots.push(new SaveShotsAndShips(5, 5));
    }

    @Test
    public void testShoot() throws Exception {
        Player player1 = new Bot();
        Player player2 = new Bot();
        Sea sea = new Sea();
        player1.setShip(sea, true, new Ship(5, 5, 2, true));

        SaveShotsAndShips shot  = SaveShotsAndShips.firstPlayersShots.pop();
        int currentX = shot.getX();
        int currentY = shot.getY();
        boolean flag = false;
        for(int i = currentX - 1; i <= currentX + 1; i++){
            if(i < 0 || i > 9){
                continue;
            }
            for (int j = currentY - 1; j <= currentY + 1; j++){
                if(j < 0 || j > 9){
                    continue;
                }


            }

        }
      player1.fire(sea, true);


    }


}