package sk.stuba.fei.uim.oop;
//10
public class ChanceCards extends Cards{
    private final String group;
    private final int[] positionToMove = new int[2];
    private final int amountToGet;
    public ChanceCards(String name, boolean used, String group, int x, int y, int amountToGet){
        this.name = name;
        this.used = used;
        this.group = group;
        this.positionToMove[0] = x;
        this.positionToMove[1] = y;
        this.amountToGet = amountToGet;
    }

    public String getGroup() {
        return group;
    }


    public int getAmountToGet() {
        return amountToGet;
    }
    public int[] getPositionToMove(){
        return this.positionToMove;
    }
}
