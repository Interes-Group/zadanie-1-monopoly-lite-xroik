package sk.stuba.fei.uim.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ArrayBoardFields {
     private final ArrayList<BoardFields> arrayList = new ArrayList<>();
     public ArrayBoardFields(int amountOfPlayers) throws FileNotFoundException {
         Scanner in = new Scanner(new File("src/main/java/sk/stuba/fei/uim/oop/FieldData.txt"));
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


         for(int i=0;i<40;i++){
            arrayList.add(new BoardFields(Integer.parseInt(result[i][0]),
                    result[i][1],
                    Integer.parseInt(result[i][2]),
                    Integer.parseInt(result[i][3]),
                    result[i][4],
                    Integer.parseInt(result[i][5])+amountOfPlayers,
                    Integer.parseInt(result[i][6])+amountOfPlayers,
                    result[i][7].charAt(0),
                    Integer.parseInt(result[i][8])));
             }


         }

    public ArrayList<BoardFields> getArrayList() {
        return arrayList;
    }
    public int getIDByLocation(int[] location) {
        int result = 0;
        for (BoardFields field : arrayList) {
            if (Arrays.equals(field.getPosition(),location)){
                result = field.getID();
                break;
            }
        }
        return result;
    }
    public void returnFieldsAfterPlayerLost(Player player){
        for(BoardFields field: arrayList){
            if(field.getOwnerID() == player.getID()){
                field.setOwnerID(0);
            }
        }
    }
}
