package sk.stuba.fei.uim.oop;
//13
import java.util.ArrayList;
import java.util.Scanner;

public class PlayerArray {
    private final ArrayList<Player> array;

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
    public int getSize(){
        return array.size();
    }
    public int getX(int number){
        return array.get(number).getPosition()[0];
    }
    public int getY(int number){
        return array.get(number).getPosition()[1];
    }
    public int getID(int number){
        return array.get(number).getID();
    }

    public ArrayList<Player> getArray() {
        return array;
    }
}
