package sk.stuba.fei.uim.oop;

import java.util.ArrayList;

public class Board {
    private final char[][] board; // Remove final if dont work
    private final int rows; // Remove final if dont work
    private final int cols; // Remove final if dont work
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
                                board[i][j] = boardFields.shortName;
                            }
                        }
                        // board[i][j] = '*';
                    } else {
                        if (j == amountOfPlayers || j == 10 + amountOfPlayers) {
                            for (BoardFields boardFields : list) {
                                if (boardFields.ComparePosition(i , j )) {
                                    board[i][j] = boardFields.shortName;
                                }
                            }
                        }
                    }
                 for(int l=0;l<playerArray.array.size();l++){
                     if(playerArray.array.get(l).position[0] == i && playerArray.array.get(l).position[1] == j){
                         board[i][j] = Character.forDigit(playerArray.array.get(l).ID,10);
                     }
                 }
                }


            }
        }

    }
    public void PrintBoard(){
        System.out.printf("%n");
        for(int i=0;i<rows;i++){
            for(int j=0;j<rows;j++){
                System.out.print(board[i][j]);
            }
            System.out.printf("%n");
        }
    }
}

