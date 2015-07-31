package model.players;

import control.SaveShotsAndShips;
import model.Sea;
import model.Ship;
import model.TypeOfField;

import java.util.Scanner;

/**
 * Created by R2 on 27.07.2015.
 */
public class Human extends Player {

    public Human(){
        super("Player " + playerCounter++);
    }

    public Human(String name) {
        super(name);
    }

    @Override
    public void set6ships(Sea sea, boolean player) {

    }

    @Override
    public void setShip(Sea sea, boolean player, Ship ship) {
        //it work idependently
    }

    @Override
    public void fire(Sea opponentSea, boolean player) {
        TypeOfField type;
        do{
            System.out.println("fire");
            int x = enter();
            int y = enter();
           // SaveShotsAndShips.addShot(x, y);    //it help when opponent is computer
           // SaveShotsAndShips shot = SaveShotsAndShips.getShot();
            type = opponentSea.shoot(x, y, true);
        }
        while (type != TypeOfField.EMPTY);


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
