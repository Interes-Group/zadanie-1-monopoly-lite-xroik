package sk.stuba.fei.uim.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayChanceCards implements ArrayCards{
    private final ArrayList<ChanceCards> arrayList = new ArrayList<>();
    public ArrayChanceCards() throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/main/java/sk/stuba/fei/uim/oop/ChanceCards"));
        List<String[]> lines = new ArrayList<>();
        while(in.hasNextLine()) {
            String line = in.nextLine().trim();
            String[] split = line.split(" ");

            lines.add(split);
        }

        String[][] result = new String[lines.size()][];
        for(int i = 0; i<result.length; i++) {
            result[i] = lines.get(i);
        }
        for(int i=0;i<9;i++){
            arrayList.add(new ChanceCards(result[i][0],
                    Boolean.parseBoolean(result[i][1]),
                    result[i][2],
                    Integer.parseInt(result[i][3]),
                    Integer.parseInt(result[i][4]),
                    Integer.parseInt(result[i][5])));
        }

    }
    public ArrayList<ChanceCards> getArrayList(){
        return arrayList;
    }
    @Override
    public boolean checkForAvailability(){
        boolean available = false;
        for(ChanceCards card : arrayList){
            if (card.getUsed()) {
                available = true;
                break;
            }
        }
        return !available;
    }

    @Override
    public void makeAllAvailable() {
        for(ChanceCards card:arrayList){
            card.used = false;
        }
    }
}
