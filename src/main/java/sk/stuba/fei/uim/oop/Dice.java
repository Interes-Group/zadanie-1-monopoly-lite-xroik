package sk.stuba.fei.uim.oop;

import java.util.Random;

public class Dice {
    private int firstRoll;
    private int secondRoll;
    public Dice(){
        firstRoll = 0;
        secondRoll = 0;
    }
    public void RollDice(){
        Random rand = new Random();
        firstRoll = 1 + rand.nextInt(6);
        secondRoll = 1 + rand.nextInt(6);
    }

    public int getFirstRoll() {
        return firstRoll;
    }

    public int getSecondRoll() {
        return secondRoll;
    }
}
