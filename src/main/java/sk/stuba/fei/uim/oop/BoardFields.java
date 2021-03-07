package sk.stuba.fei.uim.oop;

public class BoardFields {
    String name;
    int price;
    String group;
    int[] position = new int[2];
    char shortName;
    int ownerID;
    public BoardFields(){
        this.name = "";
        this.price = 0;
        this.group = "";
        this.position[0] = 0;
        this.position[1] = 0;
        this.shortName = ' ';
        this.ownerID = 0;
    }
    public BoardFields(String name, int price, String group, int x, int y, char shortName,int ownerID){
        this.name = name;
        this.price = price;
        this.group = group;
        this.position[0] = x;
        this.position[1] = y;
        this.shortName = shortName;
        this.ownerID = ownerID;
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

    public void setShortName(char shortName) {
        this.shortName = shortName;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public boolean ComparePosition(int x, int y){
        return x == position[0] && y == position[1];
    }
}
