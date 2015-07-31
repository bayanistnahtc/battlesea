package control;

import model.Sea;
import model.Ship;
import model.players.Bot;
import model.players.Human;
import model.players.Player;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by R2 on 27.07.2015.
 * There are all game process in this class
 */
public class Game implements Serializable{
    private static Game game;
    private Player firstPlayer;
    private Player secondPlayer;
    static Random random = new Random();
    public Game(){
    }

    public static Random getRandom(){
        return random;
    }
    
    public Game getGame(){
        if(game == null){
            game = new Game();
        }
        return game;
    }

    public void start(TypeOfGame typeOfGame){
        //players = new ArrayList<>();
        Sea firstPlayersSea = new Sea();
        Sea secondPlayersSea = new Sea();
        switch (typeOfGame){
            case Player_vs_Computer:
                this.firstPlayer = new Human();
                this.secondPlayer = new Bot();
                break;
            case Player_vs_Player:
                this.firstPlayer = new Human();
                this.secondPlayer = new Human();
                break;
            case Computer_vs_Computer:
                this.firstPlayer = new Bot();
                this.secondPlayer = new Bot();
                break;
        }

//here ships add to sea

        firstPlayer.set6ships(secondPlayersSea, true);
        for (int i = 0; i < 4 ; i++){
            Ship ship;
            do{
                ship = firstPlayer.artificialIntelligenceInSetShip();
            }while (!secondPlayersSea.addShip(ship));
            SaveShotsAndShips.secondPlayersShips.add(ship);
        }

        secondPlayer.set6ships(firstPlayersSea, false);
        for (int i = 0; i < 4 ; i++){
            Ship ship;
            do{
                ship = secondPlayer.artificialIntelligenceInSetShip();
            }while (!firstPlayersSea.addShip(ship));
            SaveShotsAndShips.firstPlayersShips.add(ship);
        }
        SaveShotsAndShips.createSizeCountArrays();
//--------------------------------------------------------------
//game go on until someone loses
        int iterations = 0;
        boolean flag = true;
        while (flag) {
            System.out.println("iteration " + iterations);
            iterations++;
            firstPlayersSea.draw(true);
            System.out.println();
            secondPlayersSea.draw(false);
            System.out.println();
            if(firstPlayersSea.getCounter() == 0){
                stop(firstPlayer.getName());
                flag = false;
            } else {
                if(secondPlayersSea.getCounter() == 0) {
                    stop(secondPlayer.getName());
                    flag = false;
                }
            }


            boolean whichPlayer; // need to show which player is shooting

                System.out.println("\n"+firstPlayer.getName() + " is shoot");
                whichPlayer = true;
                firstPlayer.fire(secondPlayersSea, whichPlayer);

                System.out.println("\n" + secondPlayer.getName() + " is shoot");
                secondPlayer.fire(firstPlayersSea, whichPlayer);

//            System.out.println("press any key");
//            Scanner scanner = new Scanner(System.in);
//            if (scanner.nextLine().isEmpty())
//            scanner.close();
        }
    }
    public void stop(String playerName){
        System.out.println("\nGame is end. " + playerName + " is lose");
    }

    private int enter(){
        System.out.println("enter coordinate:");
        Scanner scanner = new Scanner(System.in);
        int coordinate = 0;

        if(scanner.hasNextInt()){
            coordinate = Integer.parseInt(scanner.nextLine());
        }else {
            System.out.println("Incorrect enter, please again! ");
            enter();
        }
        return coordinate;
    }
}
