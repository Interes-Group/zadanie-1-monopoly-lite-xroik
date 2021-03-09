package sk.stuba.fei.uim.oop;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayerArray {
    private ArrayList<Player> array;

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
    public int GetSize(){
        return array.size();
    }
    public int GetX(int number){
        return array.get(number).getPosition()[0];
    }
    public int GetY(int number){
        return array.get(number).getPosition()[1];
    }
    public int GetID(int number){
        return array.get(number).getID();
    }

    public ArrayList<Player> getArray() {
        return array;
    }
}
