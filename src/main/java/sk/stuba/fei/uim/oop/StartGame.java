package sk.stuba.fei.uim.oop;
// todo Change methods names
// todo Increase Money at Start Field

import java.io.IOException;
import java.util.ArrayList;

import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StartGame {

    public static void start() throws IOException {
        Scanner in = new Scanner(System.in);
        int amountOfPlayers = 0;
        boolean error = true;
        while (true) {

            do {
                System.out.print("Enter amount of players(from 2 to 4)->");
                try {
                    amountOfPlayers = in.nextInt();
                    error = false;
                } catch (InputMismatchException e) {
                    System.out.print("Wrong input. Try again\n");
                    in.next();
                }
            } while (error);
            if (amountOfPlayers >= 2 && amountOfPlayers <= 4) {
                break;
            } else {
                System.out.print("You entered wrong number. Please try again \n");
            }
        }
        ArrayBankCards bankCards = new ArrayBankCards();
        Collections.shuffle(bankCards.getArrayList());
        ArrayChanceCards chanceCards = new ArrayChanceCards();
        Collections.shuffle(chanceCards.getArrayList());
        PlayerArray playerArray = new PlayerArray(amountOfPlayers);
        ArrayBoardFields fields = new ArrayBoardFields(amountOfPlayers);
        Board board = new Board(amountOfPlayers);
        board.SetupBoard(fields.getArrayList(), amountOfPlayers, playerArray);
        board.PrintBoard(playerArray, fields);
        int move;
        char command;
        BoardFields tmpField;
        int[] location;
        int count = 0;
        int sumToPayForRent;
        int winner = 0;
        boolean walkedThroughStart = false;
        ArrayList<Integer> arrayOfLostPlayers = new ArrayList<>();
        while (true) {
            if (winner != 0) break;
            for (Player player : playerArray.getArray()) {
                do {
                    error = false;
                    count = 0;
                    if (!player.isLost()) {
                        System.out.print('\n' + player.getName() + " press '1' to Roll Dices\n");
                        command = in.next().charAt(0);
                        if (command == '1') {
                            if (player.isPrisoned()) {
                                System.out.print(player.getName() + " got out from prison");
                                player.setPrisoned(false);
                            } else {
                                player.RollDice();
                                move = player.getFirstRoll() + player.getSecondRoll();
                                walkedThroughStart = player.Move(move, amountOfPlayers);
                                location = player.GetFieldLocation(amountOfPlayers);
                                board.SetupBoard(fields.getArrayList(), amountOfPlayers, playerArray);
                                board.PrintBoard(playerArray, fields);
                                System.out.print("\n" + player.getName() + " rolled " + move + " \n");
                                if (walkedThroughStart) {
                                    System.out.print(player.getName() + " walked through start. " + player.getName() + " earned 200$\n");
                                }
                                tmpField = fields.getArrayList().get(fields.GetIDByLocation(location));
                                System.out.print(player.getName() + " now located at " + tmpField.getName() + "\n");

                                if (!tmpField.getName().equals("FreeParking") && !tmpField.getName().equals("Bank") && !tmpField.getName().equals("Chance") && !tmpField.getName().equals("Police") && !tmpField.getName().equals("Start") && !tmpField.getName().equals("Jail") && !tmpField.getGroup().equals("Tax")) { // all fields other than that
                                    if (tmpField.getOwnerID() == 0) {
                                        System.out.print(tmpField.getName() + " is available to buy.\nThis location is related to " + tmpField.getGroup() + '\n');
                                        for (BoardFields field : fields.getArrayList()) {
                                            if (player.getID() == field.getOwnerID() && field.getGroup().equals(tmpField.getGroup())) {
                                                count++;
                                            }
                                        }
                                        System.out.print("Now you have " + count + " fields of this type\n");
                                        System.out.print("Price:" + tmpField.getPrice() + " Your Balance:" + player.getMoney() + '\n');
                                        if (tmpField.getPrice() > player.getMoney()) {
                                            System.out.print("You dont have enough money to buy this field\n");
                                        } else {
                                            System.out.print("Do you want to buy this field? Press Y/y or N/n\n");
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
                                    } else if (tmpField.getOwnerID() == player.getID()) {
                                        System.out.print("You already own this field. You dont have to pay rent for this field");
                                    } else {
                                        if (playerArray.getArray().get(tmpField.getOwnerID() - 1).isPrisoned()) {
                                            System.out.print("This field is owned by " + playerArray.getArray().get(tmpField.getOwnerID() - 1).getName() + " but he is in prison, so you dont have to pay any rent!\n");
                                            break;
                                        }
                                        for (BoardFields field : fields.getArrayList()) {
                                            if (tmpField.getOwnerID() == field.getOwnerID() && field.getGroup().equals(tmpField.getGroup())) {
                                                count++;
                                            }
                                        }
                                        if (tmpField.getGroup().equals("utility")) {
                                            if (count == 1) {
                                                sumToPayForRent = 4 * (player.getFirstRoll() + player.getSecondRoll());
                                            } else {
                                                sumToPayForRent = 10 * (player.getFirstRoll() + player.getSecondRoll());
                                            }
                                        } else {
                                            sumToPayForRent = count * tmpField.getStartingRent();
                                        }
                                        System.out.print("This field is owned by " + playerArray.getArray().get(tmpField.getOwnerID() - 1).getName() + " You have to pay " + sumToPayForRent + " € rent \n");
                                        if (player.getMoney() < sumToPayForRent) {
                                            System.out.print(player.getName() + " you dont have enough money to pay rent. You lost");
                                            playerArray.getArray().get(tmpField.getOwnerID() - 1).IncreaseBalance(player.getMoney());
                                            fields.ReturnFieldsAfterPlayerLost(player);
                                            arrayOfLostPlayers.add(player.getID());
                                            player.setLost(true);
                                        } else {
                                            player.DecreaseBalance(sumToPayForRent);
                                            playerArray.getArray().get(tmpField.getOwnerID() - 1).IncreaseBalance(sumToPayForRent);
                                        }
                                    }
                                } else if (tmpField.getName().equals("Bank")) {
                                    System.out.print(player.getName() + " located at bank field\n");
                                    if (!bankCards.CheckForAvailability()) {
                                        bankCards.MakeAllAvailable();
                                        Collections.shuffle(bankCards.getArrayList());
                                    }
                                    for (BankCards card : bankCards.getArrayList()) {
                                        if (!card.getUsed()) {
                                            System.out.print("Your bank card action is: " + card.getName() + '\n');
                                            if (!card.isPay()) {
                                                System.out.print("You received " + card.getMoney() + "$\n");
                                                player.IncreaseBalance(card.getMoney());
                                            } else {
                                                System.out.print("You have to pay " + card.getMoney() + "$\n");
                                                if (player.getMoney() >= card.getMoney()) {
                                                    player.DecreaseBalance(card.getMoney());
                                                } else {
                                                    System.out.print("You dont have enough money to pay. You lost");
                                                    fields.ReturnFieldsAfterPlayerLost(player);
                                                    arrayOfLostPlayers.add(player.getID());
                                                    player.setLost(true);
                                                }
                                            }
                                            card.setUsed(true);
                                            break;
                                        }
                                    }
                                } else if (tmpField.getName().equals("Chance")) {
                                    if (!chanceCards.CheckForAvailabilty()) {
                                        chanceCards.MakeAllAvailable();
                                        Collections.shuffle(chanceCards.getArrayList());  // to do chance cards
                                    }

                                    for (ChanceCards card : chanceCards.getArrayList()) {
                                        if (!card.getUsed()) {
                                            System.out.print("Your chance card is : " + card.getName());
                                            if (card.getGroup().equals("reward")) {
                                                player.IncreaseBalance(card.getAmountToGet());
                                            } else if (card.getGroup().equals("move")) {
                                                player.SetPosition(card.getPositionToMove(), amountOfPlayers, player.getID());
                                                board.SetupBoard(fields.getArrayList(), amountOfPlayers, playerArray);
                                                board.PrintBoard(playerArray, fields);
                                            }
                                            card.setUsed(true);
                                            break;

                                        }
                                    }
                                } else if (tmpField.getName().equals("Police")) {
                                    System.out.print(player.getName() + " got caught by the Police. You have to go to the Jail.\n");
                                    if (player.getMoney() >= 150) {
                                        System.out.print("\nOr you can bribe them for 150$\n");
                                        System.out.print("Do you want to bribe? Press Y/y or N/n\n");
                                        command = in.next().charAt(0);
                                        if (command == 'Y' || command == 'y') {
                                            player.DecreaseBalance(150);
                                            break;
                                        }
                                    }
                                    System.out.print(player.getName() + " is prisoned. " + player.getName() + "  will skip his next turn\n"); // add free rent for anyone else if player is prisoned
                                    player.setPrisoned(true);
                                    player.SetPosition(new int[]{11, 1}, amountOfPlayers, player.getID());
                                } else if (tmpField.getName().equals("Start")) {
                                    System.out.print(player.getName() + " visited Start field. Nothing happens\n");
                                } else if (tmpField.getGroup().equals("Tax")) {
                                    System.out.print(player.getName() + " have to pay " + tmpField.getStartingRent() + "$\n");
                                    if (player.getMoney() >= tmpField.getStartingRent()) {
                                        player.DecreaseBalance(tmpField.getStartingRent());
                                    } else {
                                        System.out.print("You dont have enough money to pay. You lost\n");
                                        fields.ReturnFieldsAfterPlayerLost(player);
                                        arrayOfLostPlayers.add(player.getID());
                                        player.setLost(true);
                                    }
                                } else if (tmpField.getName().equals("Jail")) {
                                    System.out.print(player.getName() + " visited Jail. But you arent arrested, so nothing happens\n");
                                } else if (tmpField.getName().equals("FreeParking")) {
                                    System.out.print(player.getName() + " visited Free Parking. You dont have to pay any rent\n");
                                }


                            }
                        } else {
                            System.out.print("Command not found. Try again\n");
                            error = true;
                        }
                    }
                    if (arrayOfLostPlayers.size() + 1 == playerArray.GetSize()) {
                        for (Player player2 : playerArray.getArray()) {
                            if (!arrayOfLostPlayers.contains(player2.getID())) {
                                winner = player2.getID();
                            }
                        }
                    }
                    if (winner != 0) {
                        System.out.print("\n\n" + playerArray.getArray().get(winner - 1).getName() + " wins. Game ended");
                        break;
                    }
                } while (error);

            }
        }
    }
}
