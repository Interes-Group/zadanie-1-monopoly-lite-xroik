package sk.stuba.fei.uim.oop;
//12
public class Player extends Dice {
    private final String name;
    private final int[] position = new int[2];
    private final int ID;
    private int money;
    private boolean isPrisoned;
    private boolean isLost;

    public Player(String name, int x, int y, int ID) {
        this.name = name;
        this.isLost = false;
        this.position[0] = x;
        this.position[1] = y;
        this.ID = ID;
        this.money = 2000;
        this.isPrisoned = false;
    }

    public int[] getPosition() {
        return position;
    }

    public int getID() {
        return ID;
    }

    public boolean move(int move, int amountOfPlayers) {
        boolean walkedThroughStart = false;
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
            else if(position[1]>10+amountOfPlayers){ // right
                if(position[0] == 10 + amountOfPlayers){ // start position
                    walkedThroughStart = true;
                    position[1] -= (ID+1);
                    position[0] += (ID);
                }
                else{
                    position[0]++;
                }
            }
        }
        return walkedThroughStart;
    }

    public String getName() {
        return name;
    }
    public int[] getFieldLocation(int amountOfPlayers){
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
    public void increaseBalance(int money) {
        this.money += money;
    }
    public void decreaseBalance(int money){
        this.money -= money;
    }
    public void setPosition(int[] xy, int amountOfPlayers, int ID){
        this.position[0]= xy[0]+amountOfPlayers - 1 ;
        this.position[1] =xy[1] + amountOfPlayers - 1;

        if(this.position[0] == 10 + amountOfPlayers){ //bottom
            this.position[0] += ID;
        }
        else if(this.position[0] == amountOfPlayers){// top
            this.position[0] -= ID;
        }
        else if(this.position[1] == amountOfPlayers){//left
            this.position[1] -= ID;
        }
        else if(this.position[1] == 10+amountOfPlayers){//right
            this.position[1] += ID;
        }

    }

    public boolean isPrisoned() {
        return isPrisoned;
    }

    public void setPrisoned(boolean prisoned) {
        isPrisoned = prisoned;
    }

    public boolean isLost() {
        return !isLost;
    }

    public void setLost(boolean lost) {
        isLost = lost;
    }
}
