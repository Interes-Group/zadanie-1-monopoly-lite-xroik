package sk.stuba.fei.uim.oop;

public class BankCards extends Cards{
    private final int money;
    private final boolean pay; // true = pay, false = receive
    public BankCards(String name,boolean used,int money, boolean pay){
        this.name = name;
        this.used = used;
        this.money = money;
        this.pay = pay;
    }

    public boolean isPay() {
        return pay;
    }

    public int getMoney() {
        return money;
    }
}
