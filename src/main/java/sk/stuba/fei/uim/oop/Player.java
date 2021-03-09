package sk.stuba.fei.uim.oop;

public class Player extends Dice {
    private String name;
    private int[] position = new int[2];
    private int ID;
    private int money;

    public Player(String name, int x, int y, int ID) {
        this.name = name;
        this.position[0] = x;
        this.position[1] = y;
        this.ID = ID;
        this.money = 2000;
    }

    public int[] getPosition() {
        return position;
    }

    public int getID() {
        return ID;
    }

    public void Move(int move, int amountOfPlayers) {
        for (int i = 1; i <= move; i++) {
            if (position[0] > 10 + amountOfPlayers && position[1]<= 10+amountOfPlayers) { //bottom side of map
                if (position[1] == amountOfPlayers) { //bottom-left edge
                    position[0] -= (ID + 1); //moving to left side
                    position[1] = amountOfPlayers - ID;
                } else {
                    position[1]--;
                }
            }
            else if(position[1] < amountOfPlayers){ //left side
                if(position[0] == amountOfPlayers){
                    position[0] = amountOfPlayers -ID; //moving to top side
                    position[1] += (ID+1);
                }
                else{
                    position[0]--;
                }
            }
            else if(position[0]<amountOfPlayers){ //top side
                if(position[1] >= 10+amountOfPlayers){  // moving to right side
                    position[0] += (ID + 1);
                    position[1] += ID;
                }
                else{
                    position[1]++;
                }
            }
            else if(position[1]>10+amountOfPlayers){
                if(position[0] == 10 + amountOfPlayers){
                    position[1] -= (ID+1);
                    position[0] += (ID);
                }
                else{
                    position[0]++;
                }
            }
        }
    }

    public String getName() {
        return name;
    }
    public int[] GetFieldLocation(int amountOfPlayers){
        int[] location = new int[2];
        if (position[0] > 10 + amountOfPlayers && position[1]<= 10+amountOfPlayers){ //bottom side
            location[0] = position[0] - ID;
            location[1] = position[1];
        }
        else if(position[1] < amountOfPlayers){ //left side
            location[0] = position[0];
            location[1] = position[1] + ID;
        }
        else if(position[0]<amountOfPlayers){ //top side
            location[0] = position[0]  + ID;
            location[1] = position[1];
        }
        else if(position[1]>10+amountOfPlayers){ //right side
            location[0] = position[0];
            location[1] = position[1] - ID;
        }
        return location;
    }

    public int getMoney() {
        return money;
    }
    public void IncreaseBalance(int money) {
        this.money += money;
    }
    public void DecreaseBalance(int money){
        this.money -= money;
    }
}