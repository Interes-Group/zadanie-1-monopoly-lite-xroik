package sk.stuba.fei.uim.oop;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StartGame {

    public static void start() throws IOException {
        Scanner in = new Scanner(System.in);
        int amountOfPlayers;
        while(true) {
            System.out.print("Enter amount of players(from 2 to 4)->");

            amountOfPlayers = in.nextInt();
            if(amountOfPlayers>=2 && amountOfPlayers <=4){
                break;
            }
            else{
                System.out.print("You entered wrong number. Please try again \n");
            }
        }

        PlayerArray playerArray = new PlayerArray(amountOfPlayers);
        ArrayBoardFields fields = new ArrayBoardFields(amountOfPlayers);
        Board board = new Board(amountOfPlayers);
        board.SetupBoard(fields.getArrayList(),amountOfPlayers,playerArray);
        board.PrintBoard();
        int move;
        char command;
        BoardFields tmpField = new BoardFields();
        int[]location = new int[2];
        int count =0;
        int sumToPayForRent = 0;
        boolean lost = false;
        ArrayList<Integer> arrayOfLostPlayers = new ArrayList<Integer>();
        while(true){
            for (Player player: playerArray.getArray()) {
                while(true) {
                    for(Integer id : arrayOfLostPlayers){
                        if(player.getID() == id){
                            lost = true;
                            break;
                        }
                    }
                    if(lost == true){
                        lost = false;
                        continue;
                    }
                    System.out.print(player.getName() + " press '1' to Roll Dices\n");
                    command = in.next().charAt(0);
                    if (command == '1') {
                        player.RollDice();
                        move = player.getFirstRoll() + player.getSecondRoll();
                        player.Move(move, amountOfPlayers);
                        location = player.GetFieldLocation(amountOfPlayers);
                        board.SetupBoard(fields.getArrayList(),amountOfPlayers,playerArray);
                        board.PrintBoard();
                        System.out.print("\n" + player.getName()+" rolled " + move +" \n");
                        tmpField = fields.getArrayList().get(fields.GetIDByLocation(location));
                        System.out.print(player.getName() +" now located at " + tmpField.getName()+"\n");
                        if(!tmpField.getName().equals("Bank") && !tmpField.getName().equals("Lucky") && !tmpField.getName().equals("GoToJail") && !tmpField.getName().equals("Start") && !tmpField.getName().equals("Jail") && !tmpField.getGroup().equals("Tax")){ // all fields other than that
                            if(tmpField.getOwnerID() == 0){
                                System.out.print(tmpField.getName() + " is available to buy.\nThis location is related to "+ tmpField.getGroup()+'\n');
                                for (BoardFields field:fields.getArrayList()) {
                                    if(player.getID() == field.getOwnerID() && field.getGroup().equals(tmpField.getGroup())){
                                        count++;
                                    }
                                }
                                System.out.print("Now you have "+count+" fields of this type\n");
                                System.out.print("Price:"+tmpField.getPrice()+" Your Balance:"+player.getMoney()+'\n');
                                if(tmpField.getPrice() > player.getMoney()){
                                    System.out.print("You dont have enough money to buy this field\n");
                                }
                                else {
                                    System.out.print("Do you want to buy this field? Press Y/y or N/n");
                                    while (true) {
                                        command = in.next().charAt(0);
                                        if (command == 'Y' || command == 'y') {
                                            tmpField.setOwnerID(player.getID()); // IF DONT CHANGE OWNER CHANGE CODE TO fields.getArrayList().get(fields.GetIDByLocation(location))
                                            player.DecreaseBalance(tmpField.getPrice());
                                            break;
                                        } else if (command == 'N' || command == 'n') {
                                            break;
                                        } else {
                                            System.out.print("Unknown command. Please, try again\n");
                                        }
                                    }
                                }
                            }
                            else if(tmpField.getOwnerID() == player.getID()){
                                System.out.print("You already own this field. You dont have to pay rent for this field");
                            }
                            else{
                                for (BoardFields field:fields.getArrayList()) {
                                    if(tmpField.getOwnerID() == field.getOwnerID() && field.getGroup().equals(tmpField.getGroup())){
                                        count++;
                                    }
                                }
                                sumToPayForRent = count*tmpField.getStartintRent();
                                System.out.print("This field is owned by " + playerArray.getArray().get(tmpField.getOwnerID()-1).getName()+" You have to pay " +sumToPayForRent + " € rent ");
                                if(player.getMoney() < sumToPayForRent){
                                    System.out.print(player.getName() +" you dont have enough money to pay rent. You lost");
                                    playerArray.getArray().get(tmpField.getOwnerID()).IncreaseBalance(player.getMoney());
                                    for(BoardFields field: fields.getArrayList()){
                                        if(field.getOwnerID() == player.getID()){
                                            field.setOwnerID(0);
                                        }
                                    }
                                    arrayOfLostPlayers.add(player.getID());
                                }
                                else{
                                    player.DecreaseBalance(sumToPayForRent);
                                    playerArray.getArray().get(tmpField.getOwnerID()).IncreaseBalance(sumToPayForRent);
                                }
                            }
                        }
                        count = 0;

                    }
                    else{
                        System.out.print("Command not found. Try again\n");
                        continue;
                    }
                  break;
                }
            }

        }
    }
}
