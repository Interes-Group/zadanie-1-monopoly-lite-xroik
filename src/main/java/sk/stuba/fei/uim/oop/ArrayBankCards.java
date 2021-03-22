package sk.stuba.fei.uim.oop;
//1
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayBankCards implements ArrayCards {
    private final ArrayList<BankCards> arrayList = new ArrayList<>();
    public ArrayBankCards() throws FileNotFoundException {
        Scanner in = new Scanner(new File("src/main/java/sk/stuba/fei/uim/oop/BankCardsData.txt"));
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
        for(int i=0;i<12;i++){
            arrayList.add(new BankCards(result[i][0],
                    Boolean.parseBoolean(result[i][1]),
                    Integer.parseInt(result[i][2]),
                    Boolean.parseBoolean(result[i][3])));
        }
    }

    public ArrayList<BankCards> getArrayList() {
        return arrayList;
    }
    @Override
    public boolean checkForAvailability() {
        boolean available = false;
        for (BankCards card : arrayList) {
            if (card.getUsed()) {
                available = true;
                break;
            }
        }
        return !available;
    }
    @Override
    public void makeAllAvailable(){
        for(BankCards card:arrayList){
            card.used = false;
        }
    }
}
