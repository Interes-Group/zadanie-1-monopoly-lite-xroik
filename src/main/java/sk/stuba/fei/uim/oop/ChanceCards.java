package sk.stuba.fei.uim.oop;

public class ChanceCards extends Cards{
    private String group;
    private int[] positionToMove = new int[2];
    private int amountToGet;
    public ChanceCards(String name, boolean used, String group, int x, int y, int amountToGet){
        this.name = name;
        this.used = used;
        this.group = group;
        this.positionToMove[0] = x;
        this.positionToMove[1] = y;
        this.amountToGet = amountToGet;
    }
}
