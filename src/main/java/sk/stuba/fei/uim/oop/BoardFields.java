package sk.stuba.fei.uim.oop;

public class BoardFields {
    private final int ID;
    private final String name;
    private final int price;
    private final String group;
    private final int[] position = new int[2];
    private final char shortName;
    private int ownerID;
    private final int startingRent;

    public BoardFields(int ID, String name, int price, int startingRent, String group, int x, int y, char shortName, int ownerID){
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.group = group;
        this.position[0] = x;
        this.position[1] = y;
        this.shortName = shortName;
        this.ownerID = ownerID;
        this.startingRent = startingRent;
    }

    public char getShortName() {
        return shortName;
    }

    public int[] getPosition(){
        return this.position;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }


    public boolean comparePosition(int x, int y){
        return x == position[0] && y == position[1];
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getGroup() {
        return group;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public int getStartingRent() {
        return startingRent;
    }
}
