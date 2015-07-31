package model.players;

import control.Game;
import control.SaveShotsAndShips;
import model.Sea;
import model.Ship;
import model.TypeOfField;

import java.util.ArrayList;
import java.util.Stack;


/**
 * Created by R2 on 27.07.2015.
 */
public class Bot extends Player {

    public Bot(){
        this("Computer " + playerCounter++);
    }
    public Bot(String name){
        super(name);
    }
    @Override
    public void fire( Sea opponentSea, boolean player) {
        Stack<SaveShotsAndShips> PlayersShots;
        if (player){
            PlayersShots = SaveShotsAndShips.firstPlayersShots;
        }else {
            PlayersShots = SaveShotsAndShips.secondPlayersShots;
        }

        if(PlayersShots.size() == 0){
            int x;
            int y;
            do{
                x = Game.getRandom().nextInt(10);
                y = Game.getRandom().nextInt(10);
            }while (opponentSea.field[x][y] == TypeOfField.MISS ||
                    opponentSea.field[x][y] == TypeOfField.HIT ||
                    opponentSea.field[x][y] == TypeOfField.INJURED);

           if( opponentSea.shoot(x, y, player) == TypeOfField.INJURED){
               PlayersShots.push(new SaveShotsAndShips(x, y));
           }
        }else {
            SaveShotsAndShips previousShot = PlayersShots.pop();

            int previousX = previousShot.getX();
            int previousY = previousShot.getY();
            int choose = Game.getRandom().nextInt(4);
           // PlayersShots.push(new SaveShotsAndShips(previousX, previousY));
            boolean flag = true;

            int currentX = 0;
            int currentY = 0;
            do{
                switch (choose){
                    case 0:
                        if(previousX == 9){
                            choose = Game.getRandom().nextInt(4);
                            break;
                        }else {
                            currentX = previousX + 1;
                            currentY = previousY;
                        }
                        break;

                    case 1:
                        if(previousX == 0){
                            choose = Game.getRandom().nextInt(4);
                            break;
                        }else {
                            currentX = previousX - 1;
                            currentY = previousY;
                        }
                        break;

                    case 2:
                        if(previousY == 9){
                             choose = Game.getRandom().nextInt(4);
                            break;
                        }else {
                            currentX = previousX ;
                            currentY = previousY + 1;
                        }
                        break;

                    case 3:
                        if (previousY == 0){
                            choose = Game.getRandom().nextInt(4);
                            break;
                        }else {
                            currentX = previousX ;
                            currentY = previousY - 1;
                        }

                        break;

                    default:
                        flag = true;
                        break;
                }
                //если корабль ранен, проверить последний выстрел, если вокруг прошлого удачного попадания нет
                //поля, которое не ранено, нужно вернуться к прошлому высрелу

                TypeOfField currentTypeOfField = opponentSea.shoot(currentX, currentY, player);
                if(currentTypeOfField == TypeOfField.INJURED ){
                    boolean isHave  = false;
                    PlayersShots.push(new SaveShotsAndShips(previousX, previousY));
                    PlayersShots.push(new SaveShotsAndShips(currentX, currentY));
                    for(int i = currentX - 1; i <= currentX + 1; i++){
                        if(i < 0 || i > 9){
                            continue;
                        }
                        for (int j = currentY - 1; j <= currentY + 1; j++){
                            if(j < 0 || j > 9){
                                continue;
                            }
                            if(opponentSea.field[i][j] != TypeOfField.OCCUPIED){
                                isHave = true;
                            }
                        }
                    }
                    if(isHave){
                        PlayersShots.pop();
                    }

                    break;
                }
                if (currentTypeOfField == TypeOfField.HIT){
                    PlayersShots.clear();
                    flag = false;
                }
                if(currentTypeOfField == TypeOfField.DOUBLESHOOT){
                    choose = Game.getRandom().nextInt(4);
                }else {
                    flag = false;
                }
            }while (flag);
        }
    }

    @Override
    public void set6ships(Sea playersSea, boolean player){
        setShip(playersSea, player, new Ship(0, 0, 4, true));
        setShip(playersSea, player, new Ship(2, 1, 3, true));
        setShip(playersSea, player, new Ship(4, 1, 3, true));
        setShip(playersSea, player, new Ship(6, 1, 2, true));
        setShip(playersSea, player, new Ship(8, 1, 2, true));
        setShip(playersSea, player, new Ship(1, 7, 2, true));
        setShip(playersSea, player, new Ship(0, 0, 4, true));
        setShip(playersSea, player, new Ship(0, 0, 4, true));
    }

    @Override
    public void setShip(Sea sea, boolean player, Ship ship) {
        ArrayList<Ship> playersShips;
        if (player) {
            playersShips = SaveShotsAndShips.firstPlayersShips;
        } else {
            playersShips = SaveShotsAndShips.secondPlayersShips;
        }

        if (sea.addShip(ship)) {
            playersShips.add(ship);

        }
    }

}
