package sk.stuba.fei.uim.oop;
//7
import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private final char[][] board; // Remove final if dont work
    private final int rows; // Remove final if dont work
    private final int cols; // Remove final if dont work

    public Board(int amountOfPlayers) {
        rows = 11 + (amountOfPlayers * 2);
        cols = 11 + (amountOfPlayers * 2);
        board = new char[rows][cols];
    }

    public void setupBoard(ArrayList<BoardFields> list, int amountOfPlayers, PlayerArray playerArray) {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
                if (i >= amountOfPlayers && j >= amountOfPlayers) {
                    if (i == amountOfPlayers || i == 10 + amountOfPlayers) {
                        for (BoardFields boardFields : list) {
                            if (boardFields.comparePosition(i, j)) {
                                board[i][j] = boardFields.getShortName();
                            }
                        }
                        // board[i][j] = '*';
                    } else {
                        if (j == amountOfPlayers || j == 10 + amountOfPlayers) {
                            for (BoardFields boardFields : list) {
                                if (boardFields.comparePosition(i, j)) {
                                    board[i][j] = boardFields.getShortName();
                                }
                            }
                        }
                    }

                }
                for (int l = 0; l < playerArray.getSize(); l++) {
                    if (playerArray.getX(l) == i && playerArray.getY(l) == j) {
                        board[i][j] = Character.forDigit(playerArray.getID(l), 10);
                    }
                }

            }
        }

    }

    public void printBoard(PlayerArray playerArray, ArrayBoardFields fieldsArray) {
        System.out.printf("%n");
        boolean alreadyPrinted;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {
                alreadyPrinted = false;
                String ANSI_RESET = "\u001B[0m";
                String ANSI_RED = "\u001B[31m";
                String ANSI_YELLOW = "\u001B[33m";
                String ANSI_BLUE = "\u001B[34m";
                String ANSI_PURPLE = "\u001B[35m";
                for (Player player : playerArray.getArray()) {
                    if (player.isLost()) {
                        if (Arrays.equals(player.getPosition(), new int[]{i, j})) {
                            switch (player.getID()) {
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
                }
                    for (BoardFields field : fieldsArray.getArrayList()) {
                        if (Arrays.equals(field.getPosition(), new int[]{i, j})) {
                            switch (field.getOwnerID()) {
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
                    if (!alreadyPrinted) {
                        System.out.print(board[i][j]);
                    }
                }
                System.out.printf("%n");
            }
        }
    }

