package sk.stuba.fei.uim.oop;

public class BankCards extends Cards{
    private int money;
    private boolean pay; // true = pay, false = receive
    public BankCards(String name,boolean used,int money, boolean pay){
        this.name = name;
        this.used = used;
        this.money = 0;
        this.pay = pay;
    }
}
