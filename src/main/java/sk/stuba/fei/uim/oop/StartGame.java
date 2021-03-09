package sk.stuba.fei.uim.oop;

import java.io.IOException;
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
        int move = 0;
        char command;
        while(true){
            for (Player player: playerArray.getArray()) {
                while(true) {
                    System.out.print("Press '1' to Roll Dices\n");
                    command = in.next().charAt(0);
                    if (command == '1') {
                        player.RollDice();
                        move = player.getFirstRoll() + player.getSecondRoll();
                        player.Move(move, amountOfPlayers);
                        board.SetupBoard(fields.getArrayList(),amountOfPlayers,playerArray);
                        board.PrintBoard();
                        System.out.print("\nYou rolled " + move +" \n");
                        break;
                    }
                    else{
                        System.out.print("Command not found. Try again");
                    }
                }
            }
        }
    }
}
