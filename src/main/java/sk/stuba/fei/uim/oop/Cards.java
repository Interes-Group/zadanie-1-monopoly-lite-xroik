package sk.stuba.fei.uim.oop;
//9
public class Cards {
    protected String name;
    protected boolean used;

    public String getName() {
        return name;
    }
    public boolean getUsed(){
        return !used;
    }
    public void setUsed(boolean used){
        this.used = used;
    }
}
