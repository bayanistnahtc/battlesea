package control;

import model.Ship;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by R2 on 28.07.2015.
 * needs to save previous shot and all players ships
 */
public class SaveShotsAndShips {
        private int x ;
        private int y;

   public static Stack<SaveShotsAndShips> firstPlayersShots = new Stack<>();
   public static Stack<SaveShotsAndShips> secondPlayersShots = new Stack<>();

   public static ArrayList<Ship> firstPlayersShips = new ArrayList<>();
   public static ArrayList<Integer> firstPlayersShipsI = new ArrayList<>();

   public static ArrayList<Ship> secondPlayersShips = new ArrayList<>();
   public static ArrayList<Integer> secondPlayersShipsI = new ArrayList<>();


   public SaveShotsAndShips(int x, int y){
            this.x = x;
            this.y = y;
    }
    public SaveShotsAndShips(SaveShotsAndShips shot){
        this.x = shot.x;
        this.y = shot.y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
   public static void createSizeCountArrays(){
       for(int i = 0; i < firstPlayersShips.size(); i++){
           firstPlayersShipsI.add(firstPlayersShips.get(i).getSize());
           secondPlayersShipsI.add(secondPlayersShips.get(i).getSize());
       }
   }
}
