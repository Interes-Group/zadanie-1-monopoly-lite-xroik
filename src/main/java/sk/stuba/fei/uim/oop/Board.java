package sk.stuba.fei.uim.oop;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private final char[][] board; // Remove final if dont work
    private final int rows; // Remove final if dont work
    private final int cols; // Remove final if dont work
    private  final String ANSI_RESET = "\u001B[0m";
    private  final String ANSI_BLACK = "\u001B[30m";
    private  final String ANSI_RED = "\u001B[31m";
    private  final String ANSI_GREEN = "\u001B[32m";
    private  final String ANSI_YELLOW = "\u001B[33m";
    private  final String ANSI_BLUE = "\u001B[34m";
    private  final String ANSI_PURPLE = "\u001B[35m";
    private  final String ANSI_CYAN = "\u001B[36m";
    private  final String ANSI_WHITE = "\u001B[37m";

    public Board(int amountOfPlayers){
        rows = 11+(amountOfPlayers*2);
        cols = 11+(amountOfPlayers*2);
        board = new char[rows][cols];
    }
    public void SetupBoard(ArrayList<BoardFields> list,int amountOfPlayers,PlayerArray playerArray){

        for(int i=0;i<rows;i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
                if (i >= amountOfPlayers && j >= amountOfPlayers) {
                    if (i == amountOfPlayers || i == 10 + amountOfPlayers) {
                        for (BoardFields boardFields : list) {
                            if (boardFields.ComparePosition(i , j )) {
                                board[i][j] = boardFields.getShortName();
                            }
                        }
                        // board[i][j] = '*';
                    } else {
                        if (j == amountOfPlayers || j == 10 + amountOfPlayers) {
                            for (BoardFields boardFields : list) {
                                if (boardFields.ComparePosition(i , j )) {
                                    board[i][j] = boardFields.getShortName();
                                }
                            }
                        }
                    }

                }
                for(int l=0;l<playerArray.GetSize();l++){
                    if(playerArray.GetX(l) == i && playerArray.GetY(l) == j){
                        board[i][j] = Character.forDigit(playerArray.GetID(l),10);
                    }
                }

            }
        }

    }
    public void PrintBoard(PlayerArray playerArray, ArrayBoardFields fieldsArray){
        System.out.printf("%n");
        boolean alreadyPrinted = false;
        for(int i=0;i<rows;i++){
            for(int j=0;j<rows;j++){
                alreadyPrinted = false;
                for (Player player: playerArray.getArray()) {
                    if (Arrays.equals(player.getPosition(), new int[]{i, j})) {
                        switch (player.getID()){
                            case 1:
                                System.out.print(ANSI_RED + board[i][j] + ANSI_RESET);
                                alreadyPrinted = true;
                                break;
                            case 2:
                                System.out.print(ANSI_BLUE + board[i][j] + ANSI_RESET);
                                alreadyPrinted = true;
                                break;
                            case 3:
                                System.out.print(ANSI_PURPLE + board[i][j] + ANSI_RESET);
                                alreadyPrinted = true;
                                break;
                            case 4:
                                System.out.print(ANSI_YELLOW + board[i][j] + ANSI_RESET);
                                alreadyPrinted = true;
                                break;
                        }
                    }
                }
                for(BoardFields field : fieldsArray.getArrayList()){
                    if(Arrays.equals(field.getPosition(), new int[]{i, j})){
                        switch (field.getOwnerID()){
                            case 0:
                                System.out.print(board[i][j]);
                                alreadyPrinted = true;
                                break;
                            case 1:
                                System.out.print(ANSI_RED + board[i][j] + ANSI_RESET);
                                alreadyPrinted = true;
                                break;
                            case 2:
                                System.out.print(ANSI_BLUE + board[i][j] + ANSI_RESET);
                                alreadyPrinted = true;
                                break;
                            case 3:
                                System.out.print(ANSI_PURPLE + board[i][j] + ANSI_RESET);
                                alreadyPrinted = true;
                                break;
                            case 4:
                                System.out.print(ANSI_YELLOW + board[i][j] + ANSI_RESET);
                                alreadyPrinted = true;
                                break;
                        }
                    }
                }
                if(!alreadyPrinted) {
                    System.out.print(board[i][j]);
                }
            }
            System.out.printf("%n");
        }
    }
}

