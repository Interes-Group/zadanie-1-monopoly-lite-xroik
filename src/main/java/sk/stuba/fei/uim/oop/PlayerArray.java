package sk.stuba.fei.uim.oop;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayerArray {
    ArrayList<Player> array;

    {
        array = new ArrayList<>();
    }
    public PlayerArray(int amountOfPlayers){
        Scanner in  = new Scanner(System.in);
        String name;
        for(int i=1;i<=amountOfPlayers;i++){
            System.out.print("Enter name of the " + i + " player ");
            name = in.nextLine();
            array.add(new Player(name,10+i+amountOfPlayers,10+amountOfPlayers,i));
        }
    }
}
