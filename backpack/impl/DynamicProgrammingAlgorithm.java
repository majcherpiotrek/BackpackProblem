package backpack.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa do rozwiązaywania problemu plecakowego za pomocą algorytmu programowania dynamicznego
 * Created by piotrek on 03.01.17.
 */
public class DynamicProgrammingAlgorithm {

    private Integer backpackSize;
    private Integer itemsNum;
    private List<Pair<Integer,Integer>> itemsToPut;
    private int[][] backpackContentValues;
    private int[][] lastPackedItems;
    private Integer bestSize;
    private Integer bestValue;

    private ArrayList<Boolean> packedItems;
    public DynamicProgrammingAlgorithm(List<Pair<Integer,Integer>> itemsToPut, Integer bpSize){
        this.itemsToPut = itemsToPut;
        this.backpackSize = bpSize;
        this.itemsNum = itemsToPut.size();
        this.bestSize = 0;
        this.bestValue = 0;
        this.packedItems = new ArrayList<>();
        for (int i = 0; i < this.itemsNum ; i++)
            this.packedItems.add(false);

        //Tworzymy macierz na wartości upakowań plecaka o rozmiarze liczba przedmiotów x rozmiar plecaka
        this.backpackContentValues = new int[this.itemsNum+1][this.backpackSize+1];

        //Tworzymy macierz, przechowującą informacje o tym, który przedmiot został zapakowany jako ostatni przy danym upakowaniu
        this.lastPackedItems = new int[this.itemsNum+1][this.backpackSize+1];
    }

    public void startDynamicProgramming(){
        /*
        PSEUDOKOD DECYZJI
        P -> backpackContentValue
        Q -> lastPackedItem

        //Jeśli przedmiot nie mieści się w plecaku to przypisujemy wartość z wiersza wyżej
        if j < weight[i] then
            P[i][j] = P[i-1][j];
            Q[i][j] = Q[i-1][j];
        else
        //W przeciwnym wypadku wpisujemy większą z wartości:
        //  wartość z wiersza wyżej dla tego samego rozmiaru plecaka
        //  wartość plecaka o rozmiarze mniejszym o rozmiar i-tego przedmiotu powiększona o wartość i-tego przemdmiotu
            P[i][j] = max(value[i] + P[i-1][j - weight[i]], P[i-1][j])
            Q[i][j] = i || Q[i-1][j] //(zależnie od tego, co zwróciło max())
        end if
         */

        //Pętla przechodząca przez wszystkie przedmioty
        for(int i = 1; i <= itemsNum; i++){

            //Pętla przechodząca przez wszystkie rozmiary placaka
            for(int j = 0; j <= backpackSize; j++)
                if(j < itemsToPut.get(i-1).getSize()){
                //jeśli przedmiot nie mieści się w plecaku
                    backpackContentValues[i][j] = backpackContentValues[i-1][j];
                    lastPackedItems[i][j] = lastPackedItems[i-1][j];
                }
                else{
                    int packed = itemsToPut.get(i-1).getValue() + backpackContentValues[i-1][j - itemsToPut.get(i-1).getSize()];
                    int notPacked = backpackContentValues[i-1][j];
                    if(packed > notPacked){
                        backpackContentValues[i][j] = packed;
                        lastPackedItems[i][j] = i;
                    }else{
                        backpackContentValues[i][j] = backpackContentValues[i-1][j];
                        lastPackedItems[i][j] = lastPackedItems[i-1][j];
                    }
                }
        }
        this.bestValue = backpackContentValues[this.itemsNum][this.backpackSize];
        backtracePackedItems();
    }

    public ArrayList<Pair<Integer,Integer>> getBestItems(){
        ArrayList<Pair<Integer, Integer>> result = new ArrayList<>();
        for (int i = 0; i < packedItems.size(); i++) {
            if (packedItems.get(i)) {
                result.add(itemsToPut.get(i));
            }
        }
        return result;
    }

    public Integer getBackpackSize() {
        return backpackSize;
    }

    public Integer getBestSize() {
        return bestSize;
    }

    public Integer getBestValue() {
        return bestValue;
    }

    private void backtracePackedItems(){
        int i = this.itemsNum;
        int j = this.backpackSize;
        int item = lastPackedItems[i][j];
        while(item >= 1){
            packedItems.set(item-1,true);
            int itemSize = itemsToPut.get(item-1).getSize();
            this.bestSize += itemSize;
            i = item-1;
            j = j - itemSize;
            item = lastPackedItems[i][j];
        }
    }

}
