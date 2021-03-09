package sk.stuba.fei.uim.oop;

public class BoardFields {
    private int ID;
    private String name;
    private int price;
    private String group;
    private int[] position = new int[2];
    private char shortName;
    private int ownerID;
    private int startintRent;
    public BoardFields(){
        this.name = "";
        this.ID = 0;
        this.price = 0;
        this.group = "";
        this.position[0] = 0;
        this.position[1] = 0;
        this.shortName = ' ';
        this.ownerID = 0;
    }
    public BoardFields(int ID, String name, int price, int startintRent, String group, int x, int y, char shortName,int ownerID){
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.group = group;
        this.position[0] = x;
        this.position[1] = y;
        this.shortName = shortName;
        this.ownerID = ownerID;
        this.startintRent = startintRent;
    }

    public char getShortName() {
        return shortName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setPosition(int x, int y) {
        this.position[0] = x;
        this.position[1] = y;
    }
    public int[] getPosition(){
        return this.position;
    }

    public void setShortName(char shortName) {
        this.shortName = shortName;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }


    public boolean ComparePosition(int x, int y){
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

    public int getStartintRent() {
        return startintRent;
    }
}
