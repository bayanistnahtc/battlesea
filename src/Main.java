import control.Game;
import view.Painting;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by R2 on 29.07.2015.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("1.save");
        System.out.println("2.load");
        Game game = null;
 //       int ch = enter();
//        if (ch == 1)
        {
            try {
                FileOutputStream fos = new FileOutputStream("temp.out");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                game = new Game();
                oos.writeObject(game);
                oos.flush();
                oos.close();
            } catch (FileNotFoundException e) {
                System.out.println("FileNotFoundException");
                //e.printStackTrace();
            }catch (IOException e) {
                System.out.println("IOException");
                //e.printStackTrace();
            }
        }

        //else if(ch == 2)
//        {
//            FileInputStream fis = null;
//            try {
//                fis = new FileInputStream("temp.out");
//                ObjectInputStream oin = new ObjectInputStream(fis);
//                game = (Game)oin.readObject();
//            }catch (IOException e) {
//                //e.printStackTrace();
//                System.out.println("IOException");
//            } catch (ClassNotFoundException e) {
//                //e.printStackTrace();
//                System.out.println("ClassNotFoundException");
//            }
//
//        }
//        else {
//            System.out.println("incorrect");
//        }



      //  new Painting();

            if (game != null) {
                game.getGame();
            }else {
                System.out.println("can not load");
            }

            System.out.println("control.Game could start \n" +
                    "please choose type of game:\n" +
                    "1. Player vs Computer \n" +
                    "2. Player vs Player \n" +
                    "3. Computer vs Computer \n");

            String choose;
            Scanner scanner = new Scanner(System.in);
            choose = "3";//scanner.nextLine();
            switch (choose){
                case "1":
                    game.start(control.TypeOfGame.Player_vs_Computer);
                    break;
                case "2":
                    game.start(control.TypeOfGame.Player_vs_Player);
                    break;
                case "3":
                    game.start(control.TypeOfGame.Computer_vs_Computer);
                    break;
                default:
                    System.out.println("Incorrect answer, please again!");
                    break;
            }
    }

    private static int enter(){
        System.out.println("enter:");
        Scanner scanner = new Scanner(System.in);
        int choose = 0;

        if(scanner.hasNextInt()){
            choose = Integer.parseInt(scanner.nextLine());
        }else {
            System.out.println("Incorrect enter, please again! ");
            enter();
        }
        return choose;
    }

}
