package sk.stuba.fei.uim.oop;

public class Player {
    String name;
    int[] position = new int[2];
    int ID;

    public Player(String name, int x, int y, int ID) {
        this.name = name;
        this.position[0] = x;
        this.position[1] = y;
        this.ID = ID;
    }
}
